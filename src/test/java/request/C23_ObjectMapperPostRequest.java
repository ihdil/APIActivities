package request;

import baseURL.JsonPlaceHolderBaseUrl;
import baseURL.JsonPlaceHolderPojo;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class C23_ObjectMapperPostRequest extends JsonPlaceHolderBaseUrl {
    @Test
    public void objectMapperTestMethod(){
        spec.pathParams("first","todos");
        String strJson="""
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
                """;

//        JsonPlaceHolderPojo expectedData = new ObjectMapper().readValue(strJson, Map.class);
//        System.out.println("expectedData = " + expectedData);
//        Response response = given(spec).body(expectedData).post("{first}");
//        response.prettyPrint();

    }
}
