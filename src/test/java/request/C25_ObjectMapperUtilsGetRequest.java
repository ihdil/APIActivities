package request;

import Pojo.BookingPojo;
import Utiliti.ObjectMapperUtils;
import baseURL.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C25_ObjectMapperUtilsGetRequest extends BookerBaseUrl {
    @Test
    void objectMapperUntilsTest() throws JsonProcessingException {
        spec.pathParams("first", "booking", "second", "92");

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
        Map expected = ObjectMapperUtils.convertJsonToJava(strJson,Map.class);
        System.out.println("expected = " + expected);

        Response response = given(spec).body(expected).get("{first}/{second}");
        response.prettyPrint();

        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),Map.class);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("firstname"),expected.get("firstname"));
        assertEquals(actualData.get("lastname"),expected.get("lastname"));
        assertEquals(actualData.get("totalprice"),expected.get("totalprice"));
        assertEquals(actualData.get("depositpaid"),expected.get("depositpaid"));
        assertEquals(((Map)actualData.get("bookingdates")).get("checkin"),(((Map)expected.get("bookingdates")).get("checkin")));
        assertEquals(((Map)actualData.get("bookingdates")).get("checkout"),(((Map)expected.get("bookingdates")).get("checkout")));
        assertEquals(actualData.get("additionalneeds"),expected.get("additionalneeds"));

    }


}
