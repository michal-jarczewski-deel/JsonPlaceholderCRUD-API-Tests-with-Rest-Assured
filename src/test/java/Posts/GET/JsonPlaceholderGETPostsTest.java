package Posts.GET;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderGETPostsTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "/posts";

    @Test
    public void jsonPlaceholderReadAllPosts() {
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
    public void jsonPlaceholderReadOnePost() {
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
        assertEquals("eveniet quod temporibus", json.get("title"));
        assertEquals("reprehenderit quos placeat\nvelit minima officia dolores impedit repudiandae " +
                "molestiae nam\nvoluptas recusandae quis delectus\nofficiis harum fugiat vitae", json.get("body"));
    }
}