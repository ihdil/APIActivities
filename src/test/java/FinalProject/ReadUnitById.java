package FinalProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static FinalProject.BaseURL.spec;
import static FinalProject.CreateUnit.Unit_id;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ReadUnitById {
    @Test
    public void readMethod() throws JsonProcessingException {
        spec.pathParams("first", "user-group","second",Unit_id);
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
        SoftAssert softAssert = new SoftAssert();
        int id = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        String shortName = response.jsonPath().getString("short_name");
        int groupTypeId = response.jsonPath().getInt("group_type_id");
        int groupTypeId_id = response.jsonPath().getInt("group_type.id");
        String groupTypeId_name = response.jsonPath().getString("group_type.name");
        String groupTypeId_description = response.jsonPath().getString("group_type.description");

        String organizationId = String.valueOf(response.jsonPath().getLong("organization_id"));
        String organization_id = String.valueOf(response.jsonPath().getLong("organization.id"));
        String organization_name = response.jsonPath().getString("organization.name");
        int organization_founder_id = response.jsonPath().getInt("organization.founder_id");
        String organization_country_id = response.jsonPath().getString("organization.country_id");
        String organization_email = response.jsonPath().getString("organization.email");

        softAssert.assertEquals(id, Unit_id);
        softAssert.assertEquals(name,  "Updated Unit By RestAssured");
        softAssert.assertEquals(shortName,  "updated-04");
        softAssert.assertEquals(groupTypeId,  3);
        softAssert.assertEquals(groupTypeId_id, 3);
        softAssert.assertEquals(groupTypeId_name, "Team");
        softAssert.assertEquals(groupTypeId_description, "Group of users of the company, like project teams or work groups");
        softAssert.assertEquals(organizationId, "1715253779444611");
        softAssert.assertEquals(organization_id, "1715253779444611");
        softAssert.assertEquals(organization_name, "XYZ Inc");
        softAssert.assertEquals(organization_founder_id, 1406);
        softAssert.assertEquals(organization_country_id, "SA");
        softAssert.assertEquals(organization_email, "xyz@example.com");
        softAssert.assertAll();
    }
}
