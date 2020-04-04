package PUT_PATCH;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderPUTPATCH_Test {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "/users";

    @Test
    public void jsonPlaceholderUpdateUserPUTTest() {
        String jsonBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Michael Test PUT\",\n" +
                "  \"username\": \"bohato\",\n" +
                "  \"email\": \"professional@email.biz\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Kulas Light\",\n" +
                "    \"suite\": \"Apt. 556\",\n" +
                "    \"city\": \"Gwenborough\",\n" +
                "    \"zipcode\": \"92998-3874\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-37.3159\",\n" +
                "      \"lng\": \"81.1496\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"1-770-736-8031 x56442\",\n" +
                "  \"website\": \"hildegard.org\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"Romaguera-Crona\",\n" +
                "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "    \"bs\": \"harness real-time e-markets\"\n" +
                "  }\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Michael Test PUT", json.get("name"));
        assertEquals("bohato", json.get("username"));
        assertEquals("professional@email.biz", json.get("email"));
    }

    @Test
    public void jsonPlaceholderUpdateUserPATCHTest() {
        String jsonBody = "{\n" +
                "  \"address\": {\n" +
                "    \"street\": \"New street address PATCH\",\n" +
                "    \"suite\": \"Apt. 556\",\n" +
                "    \"city\": \"New York City\",\n" +
                "    \"zipcode\": \"92998-3874\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-37.3159\",\n" +
                "      \"lng\": \"81.1496\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .patch(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("New street address PATCH", json.get("address.street"));
        assertEquals("New York City", json.get("address.city"));
        assertEquals("Sincere@april.biz", json.get("email")); //sanity check if old value remains
    }
}