package PUT_PATCH;

import com.github.javafaker.Faker;
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
        Faker faker = new Faker();

        String fakeName = faker.name().fullName();
        String fakeUsername = faker.name().username();
        String fakeEmail = faker.internet().emailAddress();
        String fakePhone = faker.phoneNumber().phoneNumber();
        String fakeWebsite = faker.internet().url();

        JSONObject user = new JSONObject();
        user.put("name", fakeName);
        user.put("username", fakeUsername);
        user.put("email", fakeEmail);
        user.put("phone", fakePhone);
        user.put("website", fakeWebsite);

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

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeUsername, json.get("username"));
        assertEquals(fakeEmail, json.get("email"));
        assertEquals(fakePhone, json.get("phone"));
        assertEquals(fakeWebsite, json.get("website"));
    }

    @Test
    public void jsonPlaceholderUpdateUserPATCHTest() {
        Faker faker = new Faker();

        String fakeStreet = faker.address().streetName();
        String fakeCity = faker.address().city();

        JSONObject addressDetails = new JSONObject();
        addressDetails.put("street", fakeStreet);
        addressDetails.put("city", fakeCity);

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

        assertEquals(fakeStreet, json.get("address.street"));
        assertEquals(fakeCity, json.get("address.city"));
        assertEquals("Sincere@april.biz", json.get("email")); //sanity check if old value remains the same
    }
}