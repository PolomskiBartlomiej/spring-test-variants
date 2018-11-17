# spring-test-variants
Exploring variants of spring boot test 

# variants of Spring Boot Test

**@SpringBootTest**

Loads configuration from main configuration class ( annotated with @SpringBootApplication) and use thats to create application Context, injects all Bean

**@WebMvcTest**

Testing the controller layer, to get remaining dependencies required using Mock Objects.

**@JsonTest**

Testing the JSON marshalling and unmarshalling

**@RestClientTests**

Testing REST clients, Clients which using RestTemplate

**@DataJpaTest**
Testing the repository layer

Spring provides also annotation to test NoSQL database like : **@DataMongoTest**, **@DataNeo4jTest**, **@DataRedisTest**

**@WebFluxTest**
Testing the functional controller layer

# configuration beetwens test frameworks

In `Junit 4` is required:

**@RunWith** where we can provide Runner class like SpringRunner.class or SpringJUnit4ClassRunner.class

In `Junit 5` :

**@ExtendWith(SpringExtension.class)**  only for SpringBootTest in cause of  test annotations based on MockBean have declare it :   
  ```
  ***
  @ExtendWith(SpringExtension.class)
  ***
  public @interface DataJpaTest {
  ***
  }
  ```
  ```
  ***
  @ExtendWith(SpringExtension.class)
  ***
  public @interface WebMvcTest {
  ***
  }
  ```

`Spock FrameWork` dont required any Runner   
  
# project description
 
 Project is make up in hexagonal architecture and includes:
 
  1. controller VariantController with UserInterceptor which adds user attribute to request
 
  2. service VariantService with aspect ValidVariantAspect which validates Variant
  
  3. reposity VariantRepository which saves Variant to Database
  
  And now we want functional test each layers.
  
  Tests are runnig by Junit 5 
  
 # testing web layer 
 
 To test web layer includes controller and interceptor spring provides `@WebMvcTest` that configure MvcMock and beans : 
 ```
 i.e. @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer and HandlerMethodArgumentResolver beans
 ```
 **Example**:
 ```
  @Test
    void check_is_user_interceptor_add_user_to_request() throws Exception {
        //given:
        final val variant = Variant.builder()
                .id(1)
                .name("firstVariant")
                .create();

        final val interceptorUser = new User("user");

        //when:
        when(variantService.getVariant("firstVariant")).thenReturn(variant);

        val request = get("/variants/{name}","firstVariant");

        final val result = mockMvc.perform(request)
                                   .andDo(print())
                                   .andExpect(status().isOk())
                                   .andReturn() ;

        //then:
        assertThat(result.getRequest().getAttribute("user"), is(interceptorUser));
    }
    
 ```
 
 # testing aop 
 
  To testing aop in service we need to configure TestContext manauly :
  ```
  ...
  @ExtendWith(SpringExtension.class)
  @ContextConfiguration(classes = {
        VariantService.class,
        AopConfig.class,
        ValidVariantAspect.class
  })
  class VariantServiceTest {
 ...
 ```
 when `@ExtendWith(SpringExtension.class)` is Junit 5 annotation which start Spring TestContext
 
 and  `@ContextConfiguration` which has only class we want to configure and get by `@Autowired`
 
 **Example**:
 ```
 class VariantServiceTest {

    @Autowired VariantService classUnderTest;

    @MockBean VariantRepository repository;

    @Test
    void validation_aspect_thrown_exception_when_name_is_empty() {
        //when:
        final val notValidVariant = Variant.builder()
                .id(1)
                .name("")
                .create();

        // then :
        assertThatExceptionOfType(IllegalVariantException.class)
                .isThrownBy(() -> classUnderTest.createVariant(notValidVariant));
    }
 }
 ```
 
 # testing database layer 
 To test relational database we neeed `@DataJpaTest` that configure all beans to manage repository
 and database. Project include dependecy to `'com.h2database:h2:1.4.197'` which create memory relation database
 
 **Note**
 ```
   To test no relational databases for example mongodb spring provides unique annotation
   to configure their repository i.e DataMongoTest.
   Example : https://github.com/PolomskiBartlomiej/spring-test-mongodb
  
  ```
  
  **Example**
  ```
  @DataJpaTest
class VariantRepositoryTest {

    @Autowired
    private VariantRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void variant_should_be_save_to_database() {
        //given
        val _name = "Variant";
        val variant = createVariant().name(_name).create();
        //when:
        repository.saveAndFlush(variant);
        //then
        assertEquals(
          repository.findByName(_name).orElseThrow(EntityNotFoundException::new), variant
        );
    }
   ``` 
  
 
 
 


 
 
   


