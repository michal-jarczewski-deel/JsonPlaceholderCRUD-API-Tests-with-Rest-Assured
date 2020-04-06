package Posts;

import Utils.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadPostsTest extends BaseTest {

    @Test
    public void readAllPosts() {
        Response response = when()
                .request(Method.GET, BASE_URL + POSTS)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(100, json.getList("$").size());
    }

    @Test
    public void readOnePost() {
        String postTitle = "eveniet quod temporibus";
        String postBody = "reprehenderit quos placeat\nvelit minima officia dolores impedit repudiandae " +
                "molestiae nam\nvoluptas recusandae quis delectus\nofficiis harum fugiat vitae";

        Response response = when()
                .request(Method.GET, BASE_URL + POSTS + "/15")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(2, (Integer) json.get("userId"));
        assertEquals(15, (Integer) json.get("id"));
        assertEquals(postTitle, json.get("title"));
        assertEquals(postBody, json.get("body"));
    }

    @Test
    public void readOnePostWithPathVariable() {
        String postTitle = "qui est esse";
        String postBody = "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea " +
                "dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\n" +
                "qui aperiam non debitis possimus qui neque nisi nulla";

        Response response = given()
                .pathParam("postId", 2)
                .when()
                .request(Method.GET, BASE_URL + POSTS + "/{postId}")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(1, (Integer) json.get("userId"));
        assertEquals(2, (Integer) json.get("id"));
        assertEquals(postTitle, json.get("title"));
        assertEquals(postBody, json.get("body"));
    }

    @Test
    public void readOneUserWithQueryParams() {
        String postTitle = "delectus ullam et corporis nulla voluptas sequi";
        String postBody = "non et quaerat ex quae ad maiores\nmaiores recusandae totam aut " +
                "blanditiis mollitia quas illo\nut voluptatibus voluptatem\nsimilique nostrum eum";

        Response response = given()
                .queryParam("title", postTitle)
                .when()
                .request(Method.GET, BASE_URL + POSTS)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(response, notNullValue());
        assertEquals(Arrays.asList(3), json.get("userId"));
        assertEquals(Arrays.asList(28), json.get("id"));
        assertEquals(Arrays.asList(postTitle), json.get("title"));
        assertEquals(Arrays.asList(postBody), json.get("body"));
    }
}