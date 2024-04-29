package request;

import Pojo.JsonPlaceHolderPojo;
import baseURL.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

//public class C24_ObjectMapperPostPojo extends JsonPlaceHolderBaseUrl {
//    @Test
//    public void jsonPlaceHolderPojoTestMethod() throws JsonProcessingException {
//        {
//            spec.pathParams("first", "todos");
//            String strJson = """
//                     {
//                                    "userId": 55,
//                                    "title": "Tidy your room",
//                                    "completed": false
//                                    }
//                    """;
//            JsonPlaceHolderPojo expectedData = new ObjectMapper().readValue(strJson, JsonPlaceHolderPojo.class);
//            Response response = given(spec).body(expectedData).post("{first}");
//            response.prettyPrint();
//
//            JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
//            System.out.println("actualData = " + actualData);
//
//            assert response.statusCode() == 201 : "Status code did not match";//This is Java assertion
//            assert actualData.getUserId() == expectedData.getUserId();
//            assert Objects.equals(actualData.getTitle() , expectedData.getTitle());
//            assert actualData.getCompleted() == expectedData.getCompleted();
//
//        }
//    }
//}
