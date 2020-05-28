package uk.co.qualityscape.APITestingFramework.APIs;

import io.restassured.response.Response;
import uk.co.qualityscape.APITestingFramework.extentlisteners.ExtentListeners;
import uk.co.qualityscape.APITestingFramework.setup.BaseTest;
import java.util.Hashtable;
import static io.restassured.RestAssured.given;

public class DeleteCustomerAPI extends BaseTest {

    public static Response sendDeleteRequestToDeleteCustomerAPIWithValidID(Hashtable<String, String> data) {

        String message = "Deleting customer with ID: " + data.get("id");
        System.out.println(message);
        ExtentListeners.testReport.get().info(message);

        Response response = given()
                .auth().basic(config.getProperty("validSecretKey"), "")
                .log().all()
                .delete(config.getProperty("customerAPIEndpoint") + "/" + data.get("id"));

        response.prettyPrint();
        ExtentListeners.testReport.get().info(response.prettyPrint());

        message = "Response code: " + response.statusCode();
        System.out.println(message);
        ExtentListeners.testReport.get().info(message);

        return response;
    }
}
