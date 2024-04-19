package request;

import baseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C10_PostRequest extends JsonPlaceHolderBaseURL {
    /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/
    @Test
    public void testMethod() {
        spec.pathParams("first", "todos");
        String expectedData = """
                {
                "userId": 5,
                "title": "Tidy your room",
                "completed": false
                }""";
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .post("{first}");
        response.prettyPrint();
    }
}

