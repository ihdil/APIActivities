package FinalProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static FinalProject.BaseURL.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ReadAllUnit {
    @Test
    public void readMethod() throws JsonProcessingException {
        spec.pathParams("first", "user-group");
        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
        SoftAssert softAssert = new SoftAssert();
        int id = response.jsonPath().getInt("[0].id");
        String name = response.jsonPath().getString("[0].name");
        String organization_idTest = String.valueOf(response.jsonPath().getLong("[0].organization_id"));
        String organizationName = response.jsonPath().getString("[0].organization.name");

        softAssert.assertEquals(id, 1371);
        softAssert.assertEquals(name, " Quality assurance Quality assurance");
        softAssert.assertEquals(organization_idTest, "1715291982442324");
        softAssert.assertEquals(organizationName, "My Company");
        softAssert.assertAll();
    }
}
