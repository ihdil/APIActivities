package Week09Assignments;

import Pojo.HomeWork12Pojo;
import Utiliti.ObjectMapperUtils;
import baseURL.HomeWork12BaseURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWork12Create extends HomeWork12BaseURL {
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
    public static Integer Petid;
    @Test
    public void postTest() throws JsonProcessingException {
        //set URL
        spec.pathParams("first","pet");
        //set the expectedData
        String strJson = """
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
                """;
        HomeWork12Pojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson,HomeWork12Pojo.class);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
        HomeWork12Pojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),HomeWork12Pojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(expectedData.getId(),actualData.getId());
        assertEquals(expectedData.getCategory().getId(),actualData.getCategory().getId());
        assertEquals(expectedData.getCategory().getName(),actualData.getCategory().getName());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getPhotoUrls(),actualData.getPhotoUrls());
        assertEquals(expectedData.getTags().getFirst().getId(),actualData.getTags().getFirst().getId());
        Petid = actualData.getId();

    }
}
