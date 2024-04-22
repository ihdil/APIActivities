package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class ActivityBaseURL2 {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://dummy.restapiexample.com/api")
                .setContentType(ContentType.JSON)
                .build();

    }
}

