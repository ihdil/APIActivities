package request;

import baseURL.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class C29_GroovyGetRequest extends GoRestBaseUrl {

        /*
            Given
                https://gorest.co.in/public/v1/users
            When
                User send GET Request
            Then
                The value of "pagination limit" is 10
            And
                The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
            And
                The number of users should  be 10
            And
                We have at least one "active" status
            And
                "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> This may change
            And
                The female users are less than or equals to male users -> This may change
    */

    @Test
    void groovyTest() {
        //Set the url
        spec.pathParams("first", "users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body(
                        "meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("inactive"),
                        "data.name", hasItems("Vasudev Mehra", "Kanishka Banerjee", "Girindra Pilla")
                );


        List<String> femaleUsers = response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        List<String> maleUsers = response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("femaleUsers = " + femaleUsers);
        System.out.println("maleUsers = " + maleUsers);
        assertTrue(femaleUsers.size()<=maleUsers.size());

    }


}
