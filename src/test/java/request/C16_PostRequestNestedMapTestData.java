package request;

import baseURL.BookerBaseUrl;
import Test.BookerTestData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C16_PostRequestNestedMapTestData extends BookerBaseUrl {
    @Test
    public void nestedMapTest(){
        //Set the url
        spec.pathParams("first","booking");

        //Set the expected data
        //First we need to prepare inner json as map
        Map<String, String > bookingdatesMap = BookerTestData.bookingdatesMethod("2023-03-07", "2024-09-25");
        System.out.println("bookingdatesMap = " + bookingdatesMap);

        //Now we prepare outer map
        Map<String, Object > expectedData = BookerTestData.expectedDataMethod("John","Doe",471,true, bookingdatesMap, "Lunch");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        //Since all the values inside Object container, we need to cast them when we need to use them as their original data types. Like Map, int, String..
        assertEquals(   ((Map)actualData.get("booking")).get("firstname"),  expectedData.get("firstname")  );
        assertEquals(   ((Map)actualData.get("booking")).get("lastname"),  expectedData.get("lastname")  );
        assertEquals(   ((Map)actualData.get("booking")).get("totalprice"),  expectedData.get("totalprice")  );
        assertEquals(   ((Map)actualData.get("booking")).get("depositpaid"),  expectedData.get("depositpaid")  );
        assertEquals(   ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"),  bookingdatesMap.get("checkin")  );
        assertEquals(   ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"),  bookingdatesMap.get("checkout")  );
        assertEquals(   ((Map)actualData.get("booking")).get("additionalneeds"),  expectedData.get("additionalneeds")  );

    }

}