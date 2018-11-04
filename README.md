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
  



