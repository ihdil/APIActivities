package Week08Assignments;

import baseURL.HomeWork08BaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HomeWork08 extends HomeWork08BaseURL {
    /*
          Given
              1) https://reqres.in/api/users
              2) {
                  "name": "morpheus",
                  "job": "leader"
                  }
          When
              I send POST Request to the Url
          Then
              Status code is 201
              And response body should be like {
                                                  "name": "morpheus",
                                                  "job": "leader",
                                                  "id": "496",
                                                  "createdAt": "2022-10-04T15:18:56.372Z"
                                                }
       */
    @Test
    public void restAssuredMethod() {
        spec.pathParams("first", "users");

        String expectedData = """
                                
                        {
                                        "name": "morpheus",
                                        "job": "leader"
                                        }
                        
                """;
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .post("{first}");
        response.prettyPrint();
    }
}
