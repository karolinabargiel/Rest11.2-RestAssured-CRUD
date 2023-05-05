import io.restassured.http.ContentType;
import models.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreatePostTest extends TestBase {

    @Test
    public void shouldCreateNewPost() {
        Post post = new Post("testTitle", "testBody");
        given()
                .body(post)
                .contentType(ContentType.JSON).
        when()
                .post(BASE_URL)
                .then().statusCode(201);
    }

}
