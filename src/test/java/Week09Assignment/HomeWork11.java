package Week09Assignment;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomeWork11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
    @Test
    public void homeWork11TestMethod() {
        //User send Get Request
        Response response = RestAssured.get("https://automationexercise.com/api/productsList");
        response.prettyPrint();
        response.then().statusCode(200);
        //using JsonPath to do De-serilaztion
        JsonPath jsonPath = response.jsonPath();
        int women = response.jsonPath().getList("products.findAll { it.category.usertype.usertype == 'Women' }").size();
        //Do softAssert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(women, 12);
        softAssert.assertAll();
    }
}
