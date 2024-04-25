package Week08Assignment;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class HomeWork06 {
    /*
   Given
     https://reqres.in/api/unknown/3
   When
       User send a GET request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response content type is "application/json; charset=utf-8"
   And
       Response body should be like;(Soft Assertion)
   {
   "data": {
       "id": 3,
       "name": "true red",
       "year": 2002,
       "color": "#BF1932",
       "pantone_value": "19-1664"
   },
   "support": {
       "url": "https://reqres.in/#support-heading",
       "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
   }
}
 */
    @Test
    public void testMethod() {
        String url = "https://reqres.in/api/unknown/3";
        Response response = given().get(url);
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

        SoftAssert softAssert = new SoftAssert();

        int id = response.jsonPath().getInt("data.id");
        String name = response.jsonPath().getString("data.name");
        int year = response.jsonPath().getInt("data.year");
        String color = response.jsonPath().getString("data.color");
        String pantoneValue = response.jsonPath().getString("data.pantone_value");
        String supportUrl = response.jsonPath().getString("support.url");
        String supportText = response.jsonPath().getString("support.text");

        softAssert.assertEquals(id, 3);
        softAssert.assertEquals(name, "true red");
        softAssert.assertEquals(year, 2002);
        softAssert.assertEquals(color, "#BF1932");
        softAssert.assertEquals(pantoneValue, "19-1664");
        softAssert.assertEquals(supportUrl, "https://reqres.in/#support-heading");
        softAssert.assertEquals(supportText, "To keep ReqRes free, contributions towards server costs are appreciated!");

        softAssert.assertAll();

    }
}
