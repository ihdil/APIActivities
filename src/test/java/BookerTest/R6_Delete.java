package BookerTest;

import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static BookerTest.R2_Create.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

 class R06_Delete extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send DELETE request to the url
    Then
        Status code should be 201
    And
        Response body should be: "Created"
     */

    @Test
    void deleteBookingTest(){
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        assertEquals(response.statusCode(), 201);
        assertEquals(response.asString(), expectedData);

    }
}
