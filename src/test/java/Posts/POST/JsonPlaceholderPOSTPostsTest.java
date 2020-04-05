package Posts.POST;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderPOSTPostsTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "/posts";

    private static Faker faker;

    private int randomUserId;
    private String fakeTitle;
    private String fakeBody;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        randomUserId = faker.number().numberBetween(1, 10);
        fakeTitle = faker.lorem().sentence();
        fakeBody = faker.lorem().paragraph(50);
    }

    @Test
    public void jsonPlaceholderCreateNewPost() {
        JSONObject post = new JSONObject();
        post.put("userId", randomUserId);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(post.toString())
                .request(Method.POST, BASE_URL + POSTS)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(101, (Integer) json.get("id"));
        assertEquals(randomUserId, (Integer) json.get("userId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));
    }
}