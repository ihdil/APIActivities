package HomeWork_BaseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class HomeWork09BaseURL {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
//baseURL of class HomeWork09
        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/user")
                .setContentType(ContentType.JSON)
                .build();

    }
}
