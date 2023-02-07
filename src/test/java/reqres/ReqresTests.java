package reqres;

import reqres.models.DataUserModel;
import reqres.models.NameJobModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static reqres.specs.Specs.*;

public class ReqresTests {

    @Test
    @DisplayName("Проверка имени, фамилии и почты второго пользователя")
    void checkSingleUserData(){

        DataUserModel dataUser= given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec(200))
                .log().body()
                .extract().as(DataUserModel.class);

        Assertions.assertEquals("Janet",dataUser.getData().getFirstName());

    }


    @Test
    @DisplayName("Создание нового пользователя")
    void createUsersTestName(){
        String body = "{ \"name\": \"akodele\", \"job\": \"QA engineer\"}";

        NameJobModel putUpdate= given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec(201))
                .extract().as(NameJobModel.class);
        Assertions.assertEquals("akodele",putUpdate.getName());
        Assertions.assertEquals("QA engineer",putUpdate.getJob());

    }

    @Test
    @DisplayName("Проверка изменения данных второго пользователя")
    void changeUserDataTest(){
        String body = "{ \"name\": \"akodele\", \"job\": \"QA engineer\"}";

        NameJobModel putUpdate= given()
                .spec(requestSpec)
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec(200))
                .extract().as(NameJobModel.class);
                //.body("name", is("akodele"))
                //.body("job", is("QA engineer"));
        Assertions.assertEquals("akodele",putUpdate.getName());
        Assertions.assertEquals("QA engineer",putUpdate.getJob());
    }

    @Test
    @DisplayName("Проверка удаления второго пользователя из системы")
    void deleteUser(){

        given()
                .spec(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .spec(responseSpec(204));

    }

    @Test
    @DisplayName("Проверка авторизации с некорректными данными")
    void checkUncorrectAuthorizationTest(){
        String body = "{ \"email\": \"akodele@jmail.co\", \"password\": \"QA engineer\"}";

        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec(400));

    }

    @Test
    @DisplayName("Проверка авторизации с некорректными данными")
    void checkListUserTest(){

        given()
                .spec(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("data.findAll{it.avatar.startsWith('https://reqres.in')}.avatar.flatten()",
                        hasItem("https://reqres.in/img/faces/11-image.jpg"));

    }
}
