package request;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class C01_RequestAndResponse {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    And
    print all the conncetion and 
*/
    public static void main(String [] args){


//        https://restful-booker.herokuapp.com/booking

//        User sends a GET Request to the url
        //RestAssured used to send a request and get response
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking"); //get() will get the Resoponed
        response.prettyPrint();
//        HTTP Status Code should be 200
        //  *everything we need is in response*
        int statuseCode = response.statusCode();
        System.out.println("statuseCode = " + statuseCode);
//        Content Type should be JSON
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);
//        Status Line should be HTTP/1.1 200 OK
        String StatusLine = response.statusLine();
        System.out.println("StatusLine = " + StatusLine);
//        print conncetion and Date header in the console
       String conncetion =  response.header("Connection");
        String date = response.header("Date");
        System.out.println("\nConncertion = " + conncetion);
        System.out.println("\ndate = " + date);
//        print all the headers in console
        Headers headers = response.headers();
        System.out.println("\nheaders = " + headers);


    }
}
