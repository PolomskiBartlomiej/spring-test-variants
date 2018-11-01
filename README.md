# spring-test-variants
Exploring variants of spring boot test 

# variants of Spring Boot Test

**@SpringBootTest**

Loads configuration from main configuration class ( annotated with @SpringBootApplication) and use thats to create application Context, injects all Bean

**@WebMvcTest**

Testing the controller layer, to get remaining dependencies required using Mock Objects.

**@JsonTest**

Testing the JSON marshalling and unmarshalling

**@DataJpaTest**

Testing the repository layer

**@RestClientTests**

Testing REST clients, Clients which using RestTemplate



