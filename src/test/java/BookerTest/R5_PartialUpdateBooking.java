package BookerTest;

import Pojo.BookingPojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static BookerTest.R2_Create.bookingId;
import static Utiliti.ObjectMapperUtils.convertJsonToJava;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class R5_PartialUpdateBooking  extends BookerBaseUrl {
    @Test
    void updateMethod() throws JsonProcessingException {
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String strJson = """
                        {
                            "firstname" : "Mary",
                            "lastname" : "Star"
                        }
                """;

        Map expectedData = ObjectMapperUtils.convertJsonToJava(strJson, Map.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();
//        //Do assertion
        Map actualData = convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));


    }
}
