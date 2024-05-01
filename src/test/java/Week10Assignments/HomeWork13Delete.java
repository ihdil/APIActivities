package Week10Assignments;

import HomeWork_BaseURL.HomeWork13BaseURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWork13Delete extends HomeWork13BaseURL {
    @Test
    void deleteBookingTest() {
        //Set the url
        spec.pathParam("first", "user").pathParam("second", "alaa");

        // Send the request and get the response
        Response response = given(spec).delete("/{first}/{second}");
        response.prettyPrint();

        // Verify status code
        assertEquals(response.getStatusCode(), 200);

        // Verify response body
        assertTrue(response.asString().contains("alaa"));
    }
}
