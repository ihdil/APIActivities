package Week10Assignments;

import HomeWork_BaseURL.HomeWork13BaseURL;
import Pojo.BookingPojo;
import Pojo.HomeWork13POJO;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utiliti.ObjectMapperUtils.convertJsonToJava;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork13Update extends HomeWork13BaseURL {
    @Test
    void updateMethod() throws JsonProcessingException {
        spec.pathParams("first", "user", "second", "Hdil");
        //Set the expected data
        String strJson = """
                            {
                              "id": 99878966,
                              "username": "alaa",
                              "firstName": "Alaa",
                              "lastName": "Ali",
                              "email": "alaa@gmail.com",
                              "password": "alaa1234",
                              "phone": "987654321",
                              "userStatus": 1
                            }
                """;

        HomeWork13POJO expectedData = ObjectMapperUtils.convertJsonToJava(strJson, HomeWork13POJO.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
    }
}
