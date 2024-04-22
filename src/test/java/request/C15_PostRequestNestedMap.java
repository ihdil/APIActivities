package request;

import baseURL.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C15_PostRequestNestedMap extends BookerBaseUrl {
    @Test
    public void postRequestMapMethod(){
        spec.pathParams("first","Booking");


        //prepare first inner json as map
        Map<String,String> BookingDateMap = new HashMap<>();
        BookingDateMap.put("checkin","2023-03-07");
        System.out.println("BookingDateMap = " + BookingDateMap);
        //prepare the outer json as map
        Map<String , Object> expectedData = new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Doe");
        expectedData.put("totalprice",15);
        expectedData.put("depositpaid",true);
        expectedData.put("BookingDateMap",BookingDateMap);
        expectedData.put("additionalneeds","Lunch");
        System.out.println("expectedData = " + expectedData);

        //send the request and get response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(   ((Map)actualData.get("booking")).get("firstname"),  expectedData.get("firstname")  );
    }}
