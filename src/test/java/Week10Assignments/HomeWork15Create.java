package Week10Assignments;

import HomeWork_BaseURL.HomeWork15BaseURL;
import Pojo.HomeWork15Pojo;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.github.javafaker.Faker;

public class HomeWork15Create extends HomeWork15BaseURL {
    public static String createdEmail;
    public static String authToken;
    public static String Id;

    @Test
    public void createTestMethod() throws JsonProcessingException {
        spec.pathParams("first", "users");
        Faker faker = new Faker();
        String fakeEmail = faker.internet().emailAddress();
        createdEmail = fakeEmail; // Store the generated email
        String strJson = """
                {
                "firstName": "Test",
                "lastName": "User",
                "email": "%s",
                "password": "myPassword"
                }
                """.formatted(fakeEmail);
        HomeWork15Pojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson, HomeWork15Pojo.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given(spec)
                .contentType(ContentType.JSON) // Specify content type as JSON
                .body(strJson)
                .post("{first}");
        authToken = response.getBody().jsonPath().getString("token");
        Id = response.getBody().jsonPath().getString("_id");

        response.prettyPrint();
        response.then()
                .statusCode(201);// Assuming 201 Created status is expected for successful user creation
        HomeWork15Pojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HomeWork15Pojo.class);
        System.out.println("actualData = " + response.getBody().jsonPath().getString("user"));

    }
}
