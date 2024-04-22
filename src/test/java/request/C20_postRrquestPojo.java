package request;

import Pojo.BookingDatesPojo;
import Pojo.BookingPojo;
import Pojo.BookingResponsePojo;
import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_postRrquestPojo extends BookerBaseUrl {
    @Test
    public void postRequestNestedPojo() {
        //Set the url
        spec.pathParams("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane", "Doe", 111, true, bookingDatesPojo, "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getBooking().getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getBooking().getLastname(), expectedData.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(), expectedData.getAdditionalneeds());


    }

}
