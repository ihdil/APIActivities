package request;

import baseURL.ActivityBaseURL2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Acitivity2 extends ActivityBaseURL2 {
    // Given https://dummy.restapiexample.com/api/v1/employees
// When user send Request via GET Method
// Then Assert that Status Code is "200"
// And Print employee_age greater than 55 to the console. Assert that there are 8.
// And Print the ones with id's greater than 17 to the console and assert that there are 7.
// And Print the ones with salary is less than 100,000 to the console.Assert that
// Doris Wilder is one of them.
    @Test
    public void activityTestMethod() {
        //set URL
        spec.pathParams("first", "v1", "second", "employees");
        //send request and get method
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
        //assertion
        JsonPath jsonPath = response.jsonPath();
//        1)Status code is 200
        response.then().statusCode(200);
        // And Print employee_age greater than 55 to the console. Assert that there are 8.
        List<Boolean> employee_age = jsonPath.getList("data.findAll{it.employee_age > 55}.employee_age");
        System.out.println("employee_age ->" + employee_age);
        assertEquals(employee_age.size(), 8);
        // And Print the ones with id's greater than 17 to the console and assert that there are 7.
        List<Boolean> Ids = jsonPath.getList("data.findAll{it.id > 17}.id");
        System.out.println("Ids -> " + Ids);
        assertEquals(Ids.size(), 7);
        // And Print the ones with salary is less than 100,000 to the console.Assert that
        List<String> employee_name = jsonPath.getList("data.findAll{it.employee_salary < 100000}.employee_name");
        System.out.println("employee_name = " + employee_name);
        assertTrue(employee_name.contains("Doris Wilder"));

    }
}
