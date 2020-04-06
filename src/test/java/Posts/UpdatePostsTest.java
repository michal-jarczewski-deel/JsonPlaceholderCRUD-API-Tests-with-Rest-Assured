package Posts;

import Utils.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatePostsTest extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        randomUserId = faker.number().numberBetween(1, 10);
        randomId = faker.number().numberBetween(1, 100);
        fakeTitle = faker.lorem().sentence();
        fakeBody = faker.lorem().paragraph(50);
    }

    @Test
    public void updatePostByPUTMethodTest() {
        JSONObject post = new JSONObject();
        post.put("userId", randomUserId);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(post.toString())
                .request(Method.PUT, BASE_URL + POSTS + "/" + randomId)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(randomId, (Integer) json.get("id"));
        assertEquals(randomUserId, (Integer) json.get("userId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));
    }

    @Test
    public void updatePostByPATCHMethodTest() {
        JSONObject post = new JSONObject();
        post.put("userId", randomUserId);
        post.put("body", fakeBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(post.toString())
                .request(Method.PATCH, BASE_URL + POSTS + "/" + randomId)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(randomId, (Integer) json.get("id"));
        assertEquals(randomUserId, (Integer) json.get("userId"));
        assertEquals(fakeBody, json.get("body"));
    }
}