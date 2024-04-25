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
        private static final String BASE_URL = "https://petstore.swagger.io/v2";
        private static final String API_KEY = "special-key"; // Replace with your API key if needed

        private Long petId;
        private Pet pet;

        @BeforeClass
        public void setUp() {
            RestAssured.baseURI = BASE_URL;
            RestAssured.basePath = "/pet";

            // Create a sample pet object
            pet = new Pet();
            pet.setName("Test Dog");
            pet.setStatus("available");
        }

        @Test
        public void testCreatePet() {
            // Create a pet
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .post();

            response.then()
                    .statusCode(200)
                    .body("id", equalTo(pet.getId())); // Assuming the response includes the generated ID

            petId = response.body().as(Pet.class).getId(); // Store the generated ID
        }

        @Test(dependsOnMethods = "testCreatePet")
        public void testReadPet() {
            // Read the created pet
            Response response = RestAssured.given()
                    .pathParam("petId", petId)
                    .get("/{petId}");

            response.then()
                    .statusCode(200)
                    .body("name", equalTo(pet.getName()))
                    .body("status", equalTo(pet.getStatus()));
        }

        @Test(dependsOnMethods = "testReadPet")
        public void testUpdatePet() {
            // Update the pet's status
            pet.setStatus("sold");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .pathParam("petId", petId)
                    .body(pet)
                    .put("/{petId}");

            response.then()
                    .statusCode(200)
                    .body("name", equalTo(pet.getName()))
                    .body("status", equalTo(pet.getStatus()));
        }

        @Test(dependsOnMethods = "testUpdatePet")
        public void testDeletePet() {
            // Delete the pet
            Response response = RestAssured.given()
                    .pathParam("petId", petId)
                    .delete("/{petId}");

            response.then().statusCode(200);
        }
    }

    class Pet {
        private Long id;
        private String name;
        private String status;

        // Getters and setters (omitted for brevity)
    }
}