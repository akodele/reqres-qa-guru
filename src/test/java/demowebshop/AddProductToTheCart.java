package demowebshop;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddProductToTheCart {
    @Test
    void addNewProductToCart(){
        given()
                .contentType(ContentType.JSON)
                .cookie("NOPCOMMERCE.AUTH", "F747121D62E5EA3EF489A4C553FDEB9BB63AB8FA7F10FBB885A1D54BCFD19F39AE3F32B102D6F52163CD9E268435EDCA027FD783879EE51E1C0791B93E11936BBD98CD312DAE69F97A63CE523C7F2E6135359649C9F89B6CE32D10C3A98F221FC82BA49E059835DA1D238CDB49DC6F63246A697E42B1E09C2D256BBCF6A9CE7B")
                //.body("addtocart_13.EnteredQuantity=1")
                .log().uri()
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/13/1")
                .then()
                .statusCode(200)
                .log().all();

    }

}
