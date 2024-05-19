package Utiliti;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SDA_Autho {
    public static String generateToken() {

        Response response = given()
                .formParam("grant_type", "password")
                .formParam("username", "1715783108_PZxr8HXHcGOjFro")
                .formParam("password", "IMjuv6ajlYBntToTuMdbZchY9sR3okuDkSjhXv0z7klVTF-jpbFSq7K0EfdmGY2A")
                .post("https://qa-gm3.quaspareparts.com/token");
        //response.prettyPrint();

        return response.jsonPath().getString("access_token");
    }
}
