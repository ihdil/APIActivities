package request;

import baseURL.JsonPlaceHolderBaseUrl;
import Test.JsonPlaceHolderTestData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C14_PatchRequest extends JsonPlaceHolderBaseUrl {
    @Test
    public void patchRequestMethod() {
        //set the url
        spec.pathParams("first", "todos", "second", "198");
        //set expected data
        Map<String, Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(88, null, null);
        System.out.println("expectedData = " + expectedData);

        //send the request to the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("title"),expectedData.get("title"));

        //i fwe want to assert a whole body we will assert it by the Id
        assertEquals(actualData.get("userId"),10);
    }
}
