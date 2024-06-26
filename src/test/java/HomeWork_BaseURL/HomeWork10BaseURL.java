package HomeWork_BaseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class HomeWork10BaseURL {
    protected RequestSpecification spec;
//baseURL of class HomeWork10
    @BeforeMethod
    public void setSpec() {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType("application/json")
                .build();

    }
}
