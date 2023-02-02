import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresTests {

    @Test
    @DisplayName("Проверка имени, фамилии и почты второго пользователя")
    void checkSingleUserData(){

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));

    }

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

    @Test
    @DisplayName("Проверка изменения данных второго пользователя")
    void changeUserDataTest(){
        String body = "{ \"name\": \"akodele\", \"job\": \"QA engineer\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("akodele"))
                .body("job", is("QA engineer"));

    }

    @Test
    @DisplayName("Проверка удаления второго пользователя из системы")
    void deleteUser(){

        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);

    }

    @Test
    @DisplayName("Проверка авторизации с некорректными данными")
    void checkUncorrectAuthorizationTest(){
        String body = "{ \"email\": \"akodele@jmail.co\", \"password\": \"QA engineer\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400);

    }
}
