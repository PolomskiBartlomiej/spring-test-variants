package spring.test.variants;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@Sql(value = "insert_variants.sql")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ApplicationITests {

	@LocalServerPort
	int randomServerPort;

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = randomServerPort;
	}


	@Test
	public void get_variant_by_id() {
		get("/variants/{id}",1)
				.then()
				.log().body()
				.assertThat()
				.body("name", is("firstVariant"));
	}

}
