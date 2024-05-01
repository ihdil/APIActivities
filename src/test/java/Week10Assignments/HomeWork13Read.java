package Week10Assignments;

import HomeWork_BaseURL.HomeWork13BaseURL;
import Pojo.HomeWork12Pojo;
import Pojo.HomeWork13POJO;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork13Read extends HomeWork13BaseURL {
    @Test
    public void readMethod() throws JsonProcessingException {
        spec.pathParams("first", "user", "second", "Hdil");
        //set the expectedData
        String strJson = """
                {
                  "id": 99878966,
                  "username": "Hdil",
                  "firstName": "Hadeel",
                  "lastName": "Alshahrani",
                  "email": "H@gmail.com",
                  "password": "1234",
                  "phone": "123456789",
                  "userStatus": 1
                }
                """;
        HomeWork13POJO expectedData = ObjectMapperUtils.convertJsonToJava(strJson, HomeWork13POJO.class);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec).body(expectedData).get("{first}/{second}");
        response.prettyPrint();
        HomeWork13POJO actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HomeWork13POJO.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(),200);
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getUsername(),actualData.getUsername());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getPassword(),actualData.getPassword());
        assertEquals(expectedData.getPhone(),actualData.getPhone());
        assertEquals(expectedData.getUserStatus(),actualData.getUserStatus());
    }
}