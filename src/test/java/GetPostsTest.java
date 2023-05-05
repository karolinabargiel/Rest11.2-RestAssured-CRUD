import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetPostsTest extends TestBase {

    @Test
    public void shouldGetAllPosts() {
        when().
                get(BASE_URL).
                then().
                statusCode(200);
    }

    @Test
    public void shouldGetPostWithId() {
        Response response =
                given().pathParams("id", 1).
                        when().
                        get(BASE_URL + "{id}").
                        then().
                        statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertThat(jsonPath.get("id").toString()).isEqualTo("1");
    }

    @Test
    public void shouldGetPostWithIdSchema() {
        given().pathParams("id", 1).
                when().
                get(BASE_URL + "{id}").
                then().
                statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/postSchema.json")));

    }

    @Test
    public void shouldGetPostsOfUser() {
        Response response =
                given().queryParam("userId", 3).
                        when().
                        get(BASE_URL).
                        then().
                        statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertThat(jsonPath.get("[0].userId").toString()).isEqualTo("3");
    }
}
