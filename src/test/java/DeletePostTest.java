import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePostTest extends TestBase {

    @Test
    public void shouldDeletePost() {
        given()
                .pathParams("id", 4)
                .when()
                .delete(BASE_URL + "{id}")
                .then().statusCode(200);

    }

}
