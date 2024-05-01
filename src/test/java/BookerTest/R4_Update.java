package BookerTest;

import Pojo.BookingPojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static BookerTest.R2_Create.bookingId;
import static Utiliti.ObjectMapperUtils.convertJsonToJava;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R4_Update extends BookerBaseUrl {
    @Test
    void updateMethod() throws JsonProcessingException {
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String strJson = """
                        {
                            "firstname" : "James",
                            "lastname" : "Brown",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }\
                """;

        BookingPojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");

        //Do assertion
        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());
        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());


    }
}
