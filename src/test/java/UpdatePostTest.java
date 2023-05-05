import io.restassured.http.ContentType;
import models.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdatePostTest extends TestBase {

    String body = """
            {
                "title": "testTitle",
                "body": "update patch"
            }
            """;

    @Test
    public void shouldUpdatePostWithPut() {
        Post post = new Post("testTitle", "update put");
        given()
                .body(post)
                .contentType(ContentType.JSON)
                .pathParams("id", 6).
                when()
                .put(BASE_URL + "{id}").
                then().statusCode(200);

    }

    @Test
    public void shouldUpdatePostWithPatch() {
        given()
                .body(body)
                .contentType(ContentType.JSON)
                .pathParams("id", 7)
                .when()
                .patch(BASE_URL + "{id}")
                .then().statusCode(200);

    }

}
