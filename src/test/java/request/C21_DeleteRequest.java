package request;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {
    @Test
    public void deleteRequestTest() {
        spec.pathParams("first", "todos", "second", "198");
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();
        Map<Object,Object>expectedData = new HashMap<>();
        Map<Object, Object> atucaldata = response.as(Map.class);
        System.out.println("atucaldata = " + atucaldata);
        assertEquals(response.statusCode(),200);
//        assertTrue(atucaldata,expectedData);
        assertTrue(atucaldata.isEmpty());

    }
}
