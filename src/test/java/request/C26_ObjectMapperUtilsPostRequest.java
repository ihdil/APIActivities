package request;

import Pojo.BookingPojo;
import Pojo.BookingResponsePojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C26_ObjectMapperUtilsPostRequest extends BookerBaseUrl {
       /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
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
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 2243,
                                           "booking":{
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
                                            }
*/

    @Test
    public void postRequest() throws JsonProcessingException {
        spec.pathParams("first", "booking");
        String strJson = """
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
                """;
        BookingPojo expected = ObjectMapperUtils.convertJsonToJava(strJson, BookingPojo.class);
        System.out.println("expected = " + expected);

        Response response = given(spec).body(expected).post("{first}");
        response.prettyPrint();

        BookingResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingResponsePojo.class);
        System.out.println("ActualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(expected.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expected.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expected.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expected.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expected.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expected.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expected.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


    }
}
