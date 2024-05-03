package Week10Assignments;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HomeWork16 {

    private static String baseUrl = "https://api.postman.com";
    private static String apiKey = "YOUR_API_KEY"; // Update with your actual API key

    @Test
    public void testCRUDContact() {
        // Add Contact
        String contactId = addContact();
        Assert.assertNotNull(contactId, "Failed to add contact");

        // Read Contact
        String retrievedName = readContact(contactId);
        Assert.assertNotNull(retrievedName, "Failed to retrieve contact");

        // Update Contact
        String updatedName = "Updated Name";
        updateContact(contactId, updatedName);
        String updatedNameRetrieved = readContact(contactId);
        Assert.assertEquals(updatedNameRetrieved, updatedName, "Failed to update contact");

        // Delete Contact
        deleteContact(contactId);
        String deletedName = readContact(contactId);
        Assert.assertNull(deletedName, "Failed to delete contact");

        // Negative Assertion
        Assert.assertNotEquals(deletedName, updatedName, "Deleted contact still exists");
    }

    private String addContact() {
        RequestSpecification request = RestAssured.given()
                .header("X-Api-Key", apiKey)
                .contentType("application/json")
                .body("{\"name\": \"Test Contact\"}");

        Response response = request.post(baseUrl + "/contacts");

        if (response.getStatusCode() == 201) {
            return response.jsonPath().getString("id");
        } else {
            return null;
        }
    }

    private String readContact(String contactId) {
        RequestSpecification request = RestAssured.given()
                .header("X-Api-Key", apiKey);

        Response response = request.get(baseUrl + "/contacts/" + contactId);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("name");
        } else {
            return null;
        }
    }

    private void updateContact(String contactId, String newName) {
        RequestSpecification request = RestAssured.given()
                .header("X-Api-Key", apiKey)
                .contentType("application/json")
                .body("{\"name\": \"" + newName + "\"}");

        request.put(baseUrl + "/contacts/" + contactId);
    }

    private void deleteContact(String contactId) {
        RequestSpecification request = RestAssured.given()
                .header("X-Api-Key", apiKey);

        request.delete(baseUrl + "/contacts/" + contactId);
    }
}