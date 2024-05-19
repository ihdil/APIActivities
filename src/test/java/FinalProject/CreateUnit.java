package FinalProject;

import Pojo.SDAProject;
import Pojo.SDAProject2;
import Utiliti.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static FinalProject.BaseURL.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class CreateUnit {
    public static int Unit_id;
    public static long organization_id;

    @Test
    public void createTestMethod() throws JsonProcessingException {
        spec.pathParams("first", "user-group");
        String StrJson = """
                {
                "name": "Created By RestAssured ",
                "group_type_id": 2,
                "roles": []
                }
                """;
        SDAProject2 expectedData = ObjectMapperUtils.convertJsonToJava(StrJson, SDAProject2.class);
        System.out.println("expectedData = " + expectedData);

        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(201);
        // Extract the id from the response
        Unit_id = response.jsonPath().getInt("id");
        System.out.println("Extracted ID: " + Unit_id);

        organization_id = response.jsonPath().getLong("organization_id");
        System.out.println("Extracted organization_id: " + organization_id);


        // Assertions
        SoftAssert softAssert = new SoftAssert();
        int id = response.jsonPath().getInt("id");
        String name = response.jsonPath().getString("name");
        int groupTypeIdd = response.jsonPath().getInt("group_type_id");
        long organization_idTest = response.jsonPath().getLong("organization_id");

        softAssert.assertEquals(id, Unit_id);
        softAssert.assertEquals(name, "Created By RestAssured ");
        softAssert.assertEquals(groupTypeIdd, 2);
        softAssert.assertEquals(organization_idTest, organization_id);
        softAssert.assertAll();

    }
}

