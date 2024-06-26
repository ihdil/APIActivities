package Week09Assignments;

import Pojo.HomeWork12Pojo;
import Utiliti.ObjectMapperUtils;
import HomeWork_BaseURL.HomeWork12BaseURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Week09Assignments.HomeWork12Create.Petid;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HomeWork12Read extends HomeWork12BaseURL {

    /*
    {
    "id": 226567,
    "category": {
        "id": 0,
        "name": "string"
    },
    "name": "Kitty",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "Smooth"
        }
    ],
    "status": "available"
}
     */

    @Test
    public void postTest() throws JsonProcessingException {
        //set URL
        spec.pathParams("first", "pet", "second", Petid);
        //set the expectedData
        String strJson = """
                    {
                    "id": 0,
                    "category": {
                        "id": 0,
                        "name": "string"
                    },
                    "name": "Kitty",
                    "photoUrls": [
                        "string"
                    ],
                    "tags": [
                        {
                            "id": 0,
                            "name": "Smooth"
                        }
                    ],
                    "status": "available"
                }
                """;
        HomeWork12Pojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson, HomeWork12Pojo.class);
        expectedData.setId(Petid);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec).body(expectedData).get("{first}/{second}");
        response.prettyPrint();
        HomeWork12Pojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HomeWork12Pojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getCategory().getId(), actualData.getCategory().getId());
        assertEquals(expectedData.getCategory().getName(), actualData.getCategory().getName());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getPhotoUrls(), actualData.getPhotoUrls());
        assertEquals(expectedData.getTags().getFirst().getId(), actualData.getTags().getFirst().getId());
    }
}