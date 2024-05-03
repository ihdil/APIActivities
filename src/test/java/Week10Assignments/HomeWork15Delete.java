package Week10Assignments;

import HomeWork_BaseURL.HomeWork15BaseURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Week10Assignments.HomeWork15Create.authToken;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork15Delete extends HomeWork15BaseURL {
    @Test
    public void deleteMethod(){
        spec.pathParams("first", "users", "second", "me");
        Response response = given(spec)
                .header("Authorization", "Bearer " + authToken)
                .delete("{first}/{second}");
        response.prettyPrint();
        // Verify status code
        assertEquals(response.getStatusCode(), 200);
    }
}
