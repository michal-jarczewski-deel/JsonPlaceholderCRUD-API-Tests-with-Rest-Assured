package Users.DELETE;

import Utils.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderDELETEUsersTest extends BaseTest {

    @Test
    public void jsonPlaceholderDeleteUser() {
        Response response = given()
                .when()
                .delete(BASE_URL + USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}