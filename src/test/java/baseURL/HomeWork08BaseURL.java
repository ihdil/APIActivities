package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class HomeWork08BaseURL {
    protected RequestSpecification spec;

    @BeforeMethod
    public void method() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .build();
    }
}
