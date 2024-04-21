package request;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import Test.JsonPlaceHolderTestData;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C13_PutRequest extends JsonPlaceHolderBaseUrl {
    @Test
    public void putRequestMethod(){
        spec.pathParams("first", "todos", "second", "198");
        Map<String, Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(21, "Read Books", false);

        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();


        Map<String, Object> actualData = response.as(Map.class);//De-Serialization --> We convert Json response into Java Object(Map)
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("completed"), expectedData.get("completed"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("userId"), expectedData.get("userId"));expectedData.get("title");
    }
}
