package Week09Assignments;

import HomeWork_BaseURL.HomeWork09BaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class HomeWork09 extends HomeWork09BaseURL {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
*/

        @Test
        public void postRequestTestMethod() {
            //set URL
            spec.pathParams("first", "createWithList");
            //set the expected Data
            String expectedData = """
                [
                  {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                  }
                ]
                  """;
            //send the request and get the response
            Response response = given(spec)
                    .contentType(ContentType.JSON)
                    .body(expectedData)
                    .post("{first}");
            response.prettyPrint();

            //do the assertion
            JsonPath jsonPath = response.jsonPath();
            int code = jsonPath.getInt("code");
            String type = jsonPath.getString("type");
            String message = jsonPath.getString("message");
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(code,200);
            softAssert.assertEquals(type,"unknown");
            softAssert.assertEquals(message,"ok");
            softAssert.assertAll();
        }
    }
