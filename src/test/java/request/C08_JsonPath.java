package request;

import baseURL.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C08_JsonPath extends BookerBaseUrl {
    @Test
    public void jsonPath() {
        //Set the Url
        spec.pathParams("first", "booking", "second", "32");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st way: then() method with hamcrest matchers
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("midnight snack")
                );

        //2nd way: By extracting data outside the body with JSONPath
        //Convert Response to JsonPath object
        JsonPath jsonPath = response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        String firstname = jsonPath.getString("firstname");
        String lastname = jsonPath.getString("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        String checkin = jsonPath.getString("bookingdates.checkin");
        String checkout = jsonPath.getString("bookingdates.checkout");
        String additionalneeds = jsonPath.getString("additionalneeds");
        assertEquals(firstname, "Josh");//If this assertion fails, the subsequent lines won't execute. Because this is Hard Assertion.
        assertEquals(lastname, "Allen");
        assertEquals(totalprice, 111);
        assertTrue(depositpaid);
        assertEquals(checkin, "2018-01-01");
        assertEquals(checkout, "2019-01-01");
        assertEquals(additionalneeds, "midnight snack");

        //How to use Soft Assertion
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        //2nd step: Do assertion
        softAssert.assertEquals(firstname, "Josh");//If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.
        softAssert.assertEquals(lastname, "Allen");
        softAssert.assertEquals(totalprice, 111);
        softAssert.assertTrue(depositpaid);
        softAssert.assertEquals(checkin, "2018-01-01");
        softAssert.assertEquals(checkout, "2019-01-01");
        softAssert.assertEquals(additionalneeds, "midnight snack");

        //3rd step: Use assertAll() method.
        softAssert.assertAll();
    }
}
