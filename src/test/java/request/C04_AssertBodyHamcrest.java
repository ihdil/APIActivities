package request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class C04_AssertBodyHamcrest {
    @Test
    public void assertBodyMethod() {

/*
    Steps to follow in API Testing:
    1. Set the URL
    2. Set the expected data
    3. Send the request and get the response
    4. Perform Assertion
*/
//        https://jsonplaceholder.typicode.com/todos/23
//        1. Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";
//        2. Set the expected data

//        3. Send the request and get the response
//        User sends a GET request to the URL
        Response response = given().get(url);
        response.prettyPrint();
//        HTTP Status Code should be 200

//        Response format should be "application/json"

//        "title" is "et itaque necessitatibus maxime molestiae qui quas velit"

//        "completed" is false

//        "userId" is 2

//        "userId": 2,
//                "id": 23,
//                "title": "et itaque necessitatibus maxime molestiae qui quas velit",
//                "completed": false

        //FirstWay
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));
        //SecondWay
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        , "completed", equalTo(false)
                        , "userId", equalTo(2));


    }

}


