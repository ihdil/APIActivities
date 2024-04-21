package request;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C09_Groovy extends JsonPlaceHolderBaseUrl {
    @Test
    public void groovyTest() {

        //Set the Url
        spec.pathParams("first", "todos");
        //Set the expected data
        //Send the request and get the response
        Response response = given(spec).get("{first}");
//        response.prettyPrint();
        //Do assertion
        JsonPath jsonPath = response.jsonPath();
//        1)Status code is 200
        response.then().statusCode(200);
//        2)Print all ids greater than 190 on the console
//        Assert that there are 10 ids greater than 190
        jsonPath.getList("findAll{it.id<190}.id").forEach(System.out::println);
        List<Boolean> completedlist = jsonPath.getList("findAll{it.id<5}.completed");
        System.out.println("completedlist = " + completedlist);
        List<Integer> idList = jsonPath.getList("findAll{it.id<190}.id");
        for (int w : idList) {

            System.out.println(w);


        }


//        3)Print all userIds whose ids are less than 5 on the console
//        Assert that the number of userIds whose ids are less than 5 is 4
        List<Integer> idList2 = jsonPath.getList("findAll{it.id<5}.id");
        for (int w : idList2) {
            System.out.println(w);
        }
        assertEquals(idList2.size(), 4);
//        4)Print all titles whose ids are greater than 195
//        Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
        List<String> Titles = jsonPath.getList("findAll{it.id>195}.title");
        System.out.println("Titles = " + Titles);
        assertTrue(Titles.contains("quis eius est sint explicabo"));
//        5)Print id whose title is "quo adipisci enim quam ut ab"
//        Assert that id is 8
        List<Integer> idListTitle = jsonPath.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id");
        System.out.println("idListTitle = " + idListTitle);

    }

}