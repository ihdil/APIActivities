package request;

import Pojo.BookingDatesPojo;
import Pojo.BookingPojo;
import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C19_getRequestPOJO extends BookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Jane",
                "lastname": "Doe",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Extra pillows please"
            }
*/
    @Test
    public void getRequestPojoTest() {

        //set URL
        spec.pathParams("first", "booking", "second", "880");
        //set expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane", "Doe", 111, true, bookingDatesPojo, "Extra pillows please");
        System.out.println("bookingPojo = " + expectedData);

        //send request and get response
        Response response = given(spec).get("{first}/{second}");//the parameters can be declared here
        response.prettyPrint();

        //do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
    }
}
