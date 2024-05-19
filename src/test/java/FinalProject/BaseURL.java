package FinalProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static Utiliti.SDA_Autho.generateToken;

public class BaseURL {
    public static RequestSpecification spec;

    static {
        System.out.println("Static Block : ");
        spec = new RequestSpecBuilder()
                .setBaseUri("https://a3m-qa-gm3.quaspareparts.com/auth/api")
                .addHeader("Authorization", "Bearer " + generateToken())
//                .addHeader("Cookie", "GSESSIONID=" + generateGSessionID())
                .setContentType(ContentType.JSON)
                .build();
    }

    @BeforeClass
    void BeforeClasses() {

    }
}
