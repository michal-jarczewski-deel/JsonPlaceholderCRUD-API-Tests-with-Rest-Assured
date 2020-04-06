package Users;

import Utils.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsersTest extends BaseTest {

    @Test
    public void deleteUser() {
        Response response = given()
                .when()
                .delete(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}