package BookerTest;

import Pojo.BookingPojo;
import Pojo.BookingResponsePojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static BookerTest.R2_Create.bookingId;
import static io.restassured.RestAssured.given;

public class R3_ReadId extends BookerBaseUrl {
    @Test
    void getBookingTest() throws JsonProcessingException {
        spec.pathParams("first","booking","second",bookingId);
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
        Response response = given(spec).body(expectedData).get("{first}/{second}");
        response.prettyPrint();
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);
    }
}
