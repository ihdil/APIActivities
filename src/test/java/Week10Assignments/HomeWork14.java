package Week10Assignments;

import HomeWork_BaseURL.HomeWork14BaseURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;
public class HomeWork14 extends HomeWork14BaseURL {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */
    int maxAge = 0;
    int minAge = 0;
    String employeeWithMinAge = "";
    int totalSalary = 0;

    @Test
    public void groovyTestMethod() {
        //set URL
        spec.pathParams("first", "employees");
        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(200) //Status code is 200
                .body(
                        "data.id", hasSize(24),//There are 24 employees
                        "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters")//"Tiger Nixon" and "Garrett Winters" are among the employees
                );

        List<Integer> employeesAges = response.jsonPath().getList("data.findAll{it.employee_age}.employee_age");
        maxAge = employeesAges.get(0);
        for (int age : employeesAges) {
            if (age > maxAge) {
                maxAge = age; // Update maxAge if a higher age is found
            }
        }
        System.out.println("The greatest age is = " + maxAge);//The greatest age is 66
        assertEquals(maxAge, 66, "The greatest age should be 66");

        List<String> employeeNames = response.jsonPath().getList("data.findAll{it.employee_name}.employee_name");
        minAge = employeesAges.get(0);
        employeeWithMinAge = employeeNames.get(0);
        for (int i = 1; i < employeesAges.size(); i++) {
            int age = employeesAges.get(i);
            String name = employeeNames.get(i);
            if (age < minAge) {
                minAge = age;
                employeeWithMinAge = name;
            }
        }
        System.out.println("The name of the lowest age is = " + employeeWithMinAge);//The name of the lowest age is "Tatyana Fitzpatrick"
        assertEquals(employeeWithMinAge, "Tatyana Fitzpatrick", "The name of the lowest age should be 'Tatyana Fitzpatrick'");
        List<Integer> TotalSalary = response.jsonPath().getList("data.findAll{it.employee_salary}.employee_salary");
        for (int total : TotalSalary) {
            total += totalSalary;
            totalSalary = total;
        }
        System.out.println("totalSalary = " + totalSalary);
        assertEquals(totalSalary, 6644770, " Total salary of all employees should be 6,644,770");// Total salary of all employees is 6,644,770
    }
}
