package Week10Assignments;

import HomeWork_BaseURL.HomeWork13BaseURL;
import Pojo.HomeWork13POJO;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HomeWork13Create extends HomeWork13BaseURL {
    @Test
    public void createTestMethod() throws JsonProcessingException {
        spec.pathParams("first", "user");
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

        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(200);
    }
}