package request;

import Pojo.GoRestPojo;
import Utiliti.ObjectMapperUtils;
import baseURL.GoRestBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C30_PojoGenerator extends GoRestBaseUrl {
    @Test // since it's not creating constructors so we will use ObjectMapper
    public void pojoGenerator() throws JsonProcessingException {
        //set the url
        spec.pathParams("first", "users")
                .queryParam("id", "6880236");

        String strJson = """
                  {
                            "meta": {
                                "pagination": {
                                    "total": 1,
                                    "pages": 1,
                                    "page": 1,
                                    "limit": 10,
                                    "links": {
                                        "previous": null,
                                        "current": "https://gorest.co.in/public/v1/users?page=1",
                                        "next": null
                                    }
                                }
                            },
                            "data": [
                                {
                                    "id": 6880236,
                                    "name": "Pres. Abhaya Sinha",
                                    "email": "pres_sinha_abhaya@kovacek.test",
                                    "gender": "male",
                                    "status": "inactive"
                                }
                              
                            ]
                       }
                               
                """;
        GoRestPojo expectedData = ObjectMapperUtils.convertJsonToJava(strJson, GoRestPojo.class);
        System.out.println("expectedData = " + expectedData);
        Response response = given(spec)
                .get("{first}");
        response.prettyPrint();

        GoRestPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.getMeta().getPagination().getLimit(),expectedData.getMeta().getPagination().getLimit());
        assertEquals(actualData.getMeta().getPagination().getLinks().getCurrent(),expectedData.getMeta().getPagination().getLinks().getCurrent());
        assertEquals(actualData.getData().getFirst().getId(),expectedData.getData().getFirst().getId());
        assertEquals(actualData.getData().getFirst().getName(),expectedData.getData().getFirst().getName());
        assertEquals(actualData.getData().getFirst().getEmail(),expectedData.getData().getFirst().getEmail());
        assertEquals(actualData.getData().getFirst().getGender(),expectedData.getData().getFirst().getGender());
        assertEquals(actualData.getData().getFirst().getStatus(),expectedData.getData().getFirst().getStatus());
    }
}