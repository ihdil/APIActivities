package Week10Assignments;

import HomeWork_BaseURL.HomeWork15BaseURL;
import Pojo.HomeWork15Pojo;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Week10Assignments.HomeWork15Create.authToken;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork15Update extends HomeWork15BaseURL {
    public static String createdUpdatedEmail;

    @Test
    public void updateTestMethod() throws JsonProcessingException {
        Faker faker = new Faker();
        String fakeEmail1 = faker.internet().emailAddress();
        createdUpdatedEmail = fakeEmail1;

        spec.pathParams("first", "users", "second", "me");

        // Construct the request payload with updated data
        String strJson = """
            {
                "firstName": "Updated",
                "lastName": "Username",
                "email": "%s",
                "password": "myNewPassword"
            }
            """.formatted(createdUpdatedEmail);

        // Send the PATCH request with the updated data
        Response response = given(spec)
                .header("Authorization", "Bearer " + authToken) // Include the extracted authentication token
                .contentType(ContentType.JSON) // Specify content type as JSON
                .body(strJson)
                .patch("{first}/{second}");

        response.prettyPrint();

        // Parse the JSON response directly into HomeWork15Pojo
        HomeWork15Pojo actualData = response.as(HomeWork15Pojo.class);
        System.out.println("actualData = " + actualData);
        // Construct the expected data with updated values
        HomeWork15Pojo expectedData = new HomeWork15Pojo();
        expectedData.setFirstName("Updated");
        expectedData.setLastName("Username");
        expectedData.setEmail(createdUpdatedEmail);
        expectedData.setPassword("myNewPassword");

        // Do assertion
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
    }
}