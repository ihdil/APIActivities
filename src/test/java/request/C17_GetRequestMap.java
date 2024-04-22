package request;

import baseURL.BookerBaseUrl;
import Test.BookerTestData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C17_GetRequestMap extends BookerBaseUrl {
//    @Test
//    public void getRequestMap() {
//        spec.pathParams("first", "Booking", "second", "827");
//        Map<String, String> bookngDates = BookerTestData.bookingdatesMethod("2023-03-07", "2024-09-25");
//        Map<String, Object > expectedData = BookerTestData.expectedDataMethod("John","Doe",471,true, bookingdatesMap, "Lunch");
//        System.out.println("expectedData = " + expectedData);
//        Response response = given(spec).get("{first}/{second}");
//        response.prettyPrint();
//
//        //Do assertion
//
//    }
}
