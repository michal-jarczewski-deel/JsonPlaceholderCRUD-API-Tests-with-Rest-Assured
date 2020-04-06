package Posts.DELETE;

import com.github.javafaker.Faker;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class JsonPlaceholderDELETEPostsTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "/posts";

    private static Faker faker;

    private int randomId;

    @BeforeEach
    public void beforeEach() {
        faker = new Faker();
        randomId = faker.number().numberBetween(1, 100);
    }

    @Test
    public void jsonPlaceholderDeleteUser() {
        when()
                .request(Method.POST, BASE_URL + POSTS + "/" + randomId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}