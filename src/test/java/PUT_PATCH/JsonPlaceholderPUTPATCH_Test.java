package PUT_PATCH;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderPUTPATCH_Test {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "/users";

    @Test
    public void jsonPlaceholderUpdateUserPUTTest() {
        JSONObject user = new JSONObject();
        user.put("name", "Michael Update PUT");
        user.put("username", "bohato");
        user.put("email", "email@PUTtesting.com");
        user.put("phone", "999666333");
        user.put("website", "www.google.com");

        JSONObject geo = new JSONObject();
        geo.put("lat", "-37.3159");
        geo.put("lng", "81.1496");

        JSONObject address = new JSONObject();
        address.put("street", "Kulas Light");
        address.put("suite", "Apt. 556");
        address.put("city", "Gwenborough");
        address.put("zipcode", "92998-3874");
        address.put("geo", geo);

        user.put("address", address);

        JSONObject company = new JSONObject();
        company.put("name", "Romaguera-Crona");
        company.put("catchPhrase", "Multi-layered client-server neural-net");
        company.put("bs", "Multi-layered client-server neural-net");

        user.put("company", company);

        Response response = given()
                .contentType("application/json")
                .body(user.toString())
                .when()
                .put(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Michael Update PUT", json.get("name"));
        assertEquals("bohato", json.get("username"));
        assertEquals("email@PUTtesting.com", json.get("email"));
    }

    @Test
    public void jsonPlaceholderUpdateUserPATCHTest() {
        JSONObject addressDetails = new JSONObject();
        addressDetails.put("street", "New street address PATCH");
        addressDetails.put("city", "New York City");

        JSONObject addressUpdate = new JSONObject();
        addressUpdate.put("address", addressDetails);

        Response response = given()
                .contentType("application/json")
                .body(addressUpdate.toString())
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