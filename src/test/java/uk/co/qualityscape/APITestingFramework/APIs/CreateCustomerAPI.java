package uk.co.qualityscape.APITestingFramework.APIs;

import io.restassured.response.Response;
import uk.co.qualityscape.APITestingFramework.setup.BaseTest;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {

    public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) {

        Response response = given()
                .auth().basic(config.getProperty("validSecretKey"), "")
                .formParam("name", data.get("name"))
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .log().all()
                .post(config.getProperty("customerAPIEndpoint"));
        response.prettyPrint();
        System.out.println("Status: " + response.statusCode());
        return response;
    }

    public static Response sendPostRequestToCreateCustomerAPIWithInvalidAuthKey(Hashtable<String, String> data) {

        Response response = given()
                .auth().basic(config.getProperty("invalidSecretKey"), "")
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .log().all()
                .post(config.getProperty("customerAPIEndpoint"));
        response.prettyPrint();
        System.out.println("Status: " + response.statusCode());
        return response;
    }
}
