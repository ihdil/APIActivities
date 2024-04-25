package Week09Assignment;

import HomeWork_BaseURL.HomeWork10BaseURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HomeWork10 extends HomeWork10BaseURL {
    /* Using the https://petstore.swagger.io/ document,
    write an automation test that finds the number of "pets" with the status "available"
    and asserts that there are more than 100.
     */
    @Test
    public void getRequestTestMethod() {
        Response response = given()
                .spec(spec)
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus");
        //Status code is 200
        response.then().statusCode(200);
        //send request
        response.prettyPrint();
        // Assert that the number of available pets is greater than 100
        List<Object> pets = response.as(List.class);
        assert pets.size() > 100;


    }
}
