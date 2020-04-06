package Posts;

import Utils.BaseTest;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class DeletePostsTest extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        randomId = faker.number().numberBetween(1, 100);
    }

    @Test
    public void deletePost() {
        when()
                .request(Method.POST, BASE_URL + POSTS + "/" + randomId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}