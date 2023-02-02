import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresTests {
    @Test
    @DisplayName("Создание нового пользователя")
    void createUsersTestName(){
        String body = "{ \"name\": \"akodele\", \"job\": \"QA engineer\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("akodele"))
                .body("job", is("QA engineer"));

    }
}
