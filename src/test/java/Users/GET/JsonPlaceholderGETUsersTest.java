package Users.GET;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonPlaceholderGETUsersTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "/users";

    @Test
    public void jsonPlaceholderReadAllUsers() {

        Response response = given()
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");

        assertEquals(10, names.size());
    }

    @Test
    public void jsonPlaceholderReadOneUser() {
        Response response = given()
                .when()
                .get(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    public void jsonPlaceholderReadOneUserWithPathVariable() {
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(BASE_URL + USERS + "/{userId}")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    public void jsonPlaceholderReadOneUserWithQueryParams() {
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
    }

    @Test
    public void readAllUsersAndVerifyEndingOfEmail() {
        Response response = given()
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");

        boolean ifAnyUserWithPlAsEmailDomainExists = emails.stream().allMatch(email -> !email.endsWith("pl"));

        assertTrue(ifAnyUserWithPlAsEmailDomainExists);
    }
}