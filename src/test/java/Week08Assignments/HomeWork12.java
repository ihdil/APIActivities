package Week08Assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
// (All actions must be done on same pet)
// (Use Pojo)
public class HomeWork12 {
    private String baseUrl = "https://petstore.swagger.io/v2/pet";

    // Create a new pet
    @Test(priority = 1)
    public void createPet() {
        // Define the request body
        Pet pet = new Pet(0, "Max", "dog");

        // Send POST request to create the pet
        Response response = given()
                .contentType("application/json")
                .body(pet)
                .when()
                .post(baseUrl + "/pet")
                .then()
                .statusCode(200)
                .extract().response();

        // Assert that the response contains the created pet
        response.then().body("id", equalTo(0));
    }

    // Read the pet
    @Test(priority = 2)
    public void readPet() {
        // Send GET request to retrieve the pet
        given()
                .when()
                .get(baseUrl + "/pet/0")
                .then()
                .statusCode(200)
                .body("id", equalTo(0))
                .body("name", equalTo("Max"))
                .body("status", equalTo("available"));
    }

    // Update the pet
    @Test(priority = 3)
    public void updatePet() {
        // Define the updated request body
        Pet updatedPet = new Pet(12345, "Maximus", "dog");

        // Send PUT request to update the pet
        Response response = given()
                .contentType("application/json")
                .body(updatedPet)
                .when()
                .put(baseUrl + "/pet")
                .then()
                .statusCode(200)
                .extract().response();

        // Assert that the response contains the updated pet
        response.then().body("name", equalTo("Maximus"));
    }

    // Delete the pet
    @Test(priority = 4)
    public void deletePet() {
        // Send DELETE request to delete the pet
        given()
                .when()
                .delete(baseUrl + "/pet/12345")
                .then()
                .statusCode(200);

        // Send GET request to verify that the pet is deleted
        given()
                .when()
                .get(baseUrl + "/pet/12345")
                .then()
                .statusCode(404);
    }

    // POJO class for defining the Pet object
    static class Pet {
        long id;
        String name;
        String status;

        public Pet(long id, String name, String status) {
            this.id = id;
            this.name = name;
            this.status = status;
        }
    }
}