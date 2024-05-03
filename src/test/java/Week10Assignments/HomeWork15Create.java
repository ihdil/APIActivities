package Week10Assignments;

import HomeWork_BaseURL.HomeWork15BaseURL;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;

public class HomeWork15Create extends HomeWork15BaseURL {
    public static String createdEmail;
    public static String authToken;
    public static String Id;

    @Test
    public void createTestMethod() {
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

        Response response = given(spec)
                .contentType(ContentType.JSON) // Specify content type as JSON
                .body(strJson)
                .post("{first}");
        authToken = response.getBody().jsonPath().getString("token");
        Id = response.getBody().jsonPath().getString("_id");

        response.prettyPrint();
        response.then()
                .statusCode(201); // Assuming 201 Created status is expected for successful user creation
    }
}
