package Utiliti;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authenttication {
    public static String generateToken() {
        String strJson = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        Response response = given()
                .contentType(ContentType.JSON)
                .body(strJson)
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();
        return response.jsonPath().getString("token");
    }

    public static void main(String[] args) {
        String token = generateToken();
        System.out.println("token = " + token);
    }
}
