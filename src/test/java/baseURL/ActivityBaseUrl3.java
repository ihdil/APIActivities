package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class ActivityBaseUrl3 {
    protected RequestSpecification spec;
    @BeforeMethod

    public void setUp() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("X-RapidAPI-Key","193fe7b932mshd018473dd984abbp13ba61jsn7f8835a09401")
                .addHeader("X-RapidAPI-Host","community-zippopotamus.p.rapidapi.com")
                .setBaseUri("https://community-zippopotamus.p.rapidapi.com")
                .build();
    }
}
