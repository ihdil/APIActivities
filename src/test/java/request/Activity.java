package request;

import baseURL.ActivityBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Activity extends ActivityBaseURL {
    // Base URL should be used as Spec
// Given https://gorest.co.in/public/v2/todos/47900
// When user send Request via GET Method
// Then Assert that Status Code is "200"
// And Assert that Content Type is "application/json"
// And Assert that Response Body is as follows:
//    {
//        "id": 47900,
//            "user_id": 6861183,
//            "title": "Et minus libero aegrotatio teres quia.",
//            "due_on": "2024-04-25T00:00:00.000+05:30",
//            "status": "pending"
//    }
    @Test
    public void activityTestMethod() {
        //set URL
        spec.pathParams("first", "todos", "second", "47900");
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do an Assertion
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        JsonPath jsonPath = response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        int user_id = jsonPath.getInt("user_id");
        String title = jsonPath.getString("title");
        String due_on = jsonPath.getString("due_on");
        String status = jsonPath.getString("status");
        assertEquals(user_id, 6861183);
        assertEquals(title, "Et minus libero aegrotatio teres quia.");
        assertEquals(due_on, "2024-04-25T00:00:00.000+05:30");
        assertEquals(status, "pending");
    }

}
