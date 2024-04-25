package Week09Assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HomeWork12 {

    private String petId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void createPet() {
        // Create a new pet
        Pet pet = new Pet();
        pet.setName("doggie");
        pet.setStatus("available");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .response();

        petId = response.jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "createPet")
    public void readPet() {
        // Read the created pet
        given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "readPet")
    public void updatePet() {
        // Update the created pet's name
        Pet updatedPet = new Pet();
        updatedPet.setName("Max");

        given()
                .contentType(ContentType.JSON)
                .body(updatedPet)
                .pathParam("petId", petId)
                .when()
                .put("/pet/{petId}")
                .then()
                .statusCode(405);
    }

    @Test(dependsOnMethods = "updatePet")
    public void deletePet() {
        // Delete the created pet
        given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    // POJO class representing a pet
    public class Pet {
        private String id;
        private String name;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
