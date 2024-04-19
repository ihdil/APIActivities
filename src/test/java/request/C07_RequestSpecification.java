package request;
import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class C07_RequestSpecification extends BookerBaseUrl {
    @Test
    public void requestSpecificationTest() {

//        1. Set the URL
        spec.pathParams("first", "booking")
                .queryParams("firstname", "John", "lastname", "Doe");

//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

//        4. Do Assertion
        response.then().body("", hasSize(greaterThan(0)));//This means to check if it has at least one booking id


    }
}
