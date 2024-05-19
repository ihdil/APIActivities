package Week10Assignments;

import HomeWork_BaseURL.HomeWork15BaseURL;
import Pojo.HomeWork15Pojo;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Week10Assignments.HomeWork15Create.authToken;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork15Read extends HomeWork15BaseURL {
    @Test
    public void readMethod() throws JsonProcessingException {
        spec.pathParams("first", "users", "second", "me");
        String createdEmail = HomeWork15Create.createdEmail;
//        String Id = HomeWork15Create.Id;
        // Set the expectedData
        String strJson = """
            {
                "firstName": "Test",
                "lastName": "User",
                "email": "%s",
                "password": "myPassword"
                }
                """.formatted(createdEmail);
        HomeWork15Pojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson, HomeWork15Pojo.class);
        System.out.println("expectedData = " + expectedData);

        // Add authentication token to the request headers
        Response response = given(spec)
                .header("Authorization", "Bearer " + authToken) // Include the extracted authentication token
                .get("{first}/{second}");

        response.prettyPrint();
        HomeWork15Pojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HomeWork15Pojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
    }
}
