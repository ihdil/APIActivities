package FinalProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static FinalProject.BaseURL.spec;
import static FinalProject.CreateUnit.Unit_id;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteUnitById {
    @Test
    public void deleteMethod() throws JsonProcessingException {
        spec.pathParams("first", "user-group","second",Unit_id);
        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
    }
}
