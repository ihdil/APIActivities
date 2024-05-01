package BookerTest;

import Pojo.BookingPojo;
import Pojo.BookingResponsePojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R2_Create extends BookerBaseUrl {
    static int bookingId;
    @Test
    void createBookingTest() throws JsonProcessingException {
        spec.pathParams("first","booking");
        String strJson = """
                {
                    "firstname" : "Jim",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;
        BookingPojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson,BookingPojo.class);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
        BookingResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        bookingId= actualData.getBookingid();

    }
}
