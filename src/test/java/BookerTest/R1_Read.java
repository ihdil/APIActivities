package BookerTest;

import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class R1_Read extends BookerBaseUrl {
       /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        Send GET request to the url
    Then
        Assert that status code is 200
    And
        Response body should have more than 0 elements
        */
    @Test
    public void getBookingIdsTest(){
        spec.pathParams("first","booking");
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        response.then().statusCode(200).body(
                "",hasSize(greaterThan(0))
        );
    }
}
