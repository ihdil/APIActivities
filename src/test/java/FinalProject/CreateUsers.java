package FinalProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static FinalProject.BaseURL.spec;
import static FinalProject.CreateUnit.Unit_id;
import static FinalProject.CreateUnit.organization_id;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUsers {
    @Test
    public void createUser() throws JsonProcessingException {
        spec.pathParams(
                "first", "organization",
                "second", CreateUnit.organization_id,
                "third", "user-group",
                "fourth", CreateUnit.Unit_id,
                "fifth", "user",
                "sixth", "1404"
        ).queryParam("isHead", "false");

        // Send the request and get the response
        Response response = given(spec).post("{first}/{second}/{third}/{fourth}/{fifth}/{sixth}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 201);
    }
}
