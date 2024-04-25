package Week08Assignment;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork07 {
    /*
     Given
            https://reqres.in/api/unknown/
     When
          I send GET Request to the URL
     Then

          1)Status code is 200
          2)Print all pantone_values
          3)Print all ids greater than 3 on the console
            Assert that there are 3 ids greater than 3
          4)Print all names whose ids are less than 3 on the console
            Assert that the number of names whose ids are less than 3 is 2
  */
    @Test
    public void testMetho() {
        String url = "https://reqres.in/api/unknown/";
        Response response = given().get(url);
        response.prettyPrint();

//        1)Status code is 200
        response.then().statusCode(200);
//        2)Print all pantone_values
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println("Pantone Values:");
        jsonPath.getList("data.pantone_value").forEach(System.out::println);
//        3)Print all ids greater than 3 on the console
//        Assert that there are 3 ids greater than 3
        List<Integer> GidList = jsonPath.getList("data.findAll{it.id > 3}.id");
        System.out.println("IDs greater than 3:");
        GidList.forEach(System.out::println);
        assertEquals(GidList.size(),3);
//        4)Print all names whose ids are less than 3 on the console
//        Assert that the number of names whose ids are less than 3 is 2
        List<String> LidList = jsonPath.getList("data.findAll{it.id < 3}.name");
        System.out.println("IDs names that Less than 3:");
        LidList.forEach(System.out::println);
        assertEquals(LidList.size(),2);
    }
}
