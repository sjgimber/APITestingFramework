package uk.co.qualityscape.APITestingFramework.testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.qualityscape.APITestingFramework.APIs.CreateCustomerAPI;
import uk.co.qualityscape.APITestingFramework.setup.BaseTest;
import uk.co.qualityscape.APITestingFramework.utilities.DataUtil;

import java.util.Arrays;
import java.util.Hashtable;

import static io.restassured.RestAssured.*;

public class CreateCustomerTest extends BaseTest {

    @Test (dataProviderClass=DataUtil.class, dataProvider="data")
    public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

        // extentTest = extentReports.createTest("Validate CreateCustomerAPI (valid key)\n" + data.toString());
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test (dataProviderClass = DataUtil.class, dataProvider = "data")
    public void validateCreateCustomerAPIWithInvalidSecretKey(Hashtable<String, String> data) {

        // extentTest = extentReports.createTest("Validate CreateCustomerAPI (invalid key)");
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInvalidAuthKey(data);
        Assert.assertEquals(response.statusCode(), 401);
    }
}
