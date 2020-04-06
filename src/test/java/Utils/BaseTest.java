package Utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    protected final String BASE_URL = "https://jsonplaceholder.typicode.com";
    protected final String USERS = "/users";
    protected final String POSTS = "/posts";

    protected static Faker faker;

    protected String fakeName;
    protected String fakeUsername;
    protected String fakeEmail;
    protected String fakePhone;
    protected String fakeWebsite;
    protected String fakeStreet;
    protected String fakeCity;

    protected int randomId;

    protected int randomUserId;
    protected String fakeTitle;
    protected String fakeBody;

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }
}
