package reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static reqres.helpers.CustomApiListener.withCustomTemplates;

public class Specs {
    public static RequestSpecification requestSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().uri()
            .log().headers()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec(int status) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(status)
                .build();
    }
}
