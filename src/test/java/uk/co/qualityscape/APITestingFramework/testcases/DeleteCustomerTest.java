package uk.co.qualityscape.APITestingFramework.testcases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import uk.co.qualityscape.APITestingFramework.APIs.CreateCustomerAPI;
import uk.co.qualityscape.APITestingFramework.APIs.DeleteCustomerAPI;
import uk.co.qualityscape.APITestingFramework.extentlisteners.ExtentListeners;
import uk.co.qualityscape.APITestingFramework.setup.BaseTest;
import uk.co.qualityscape.APITestingFramework.utilities.DataUtil;

import java.util.Hashtable;

import static uk.co.qualityscape.APITestingFramework.utilities.TestUtil.*;

public class DeleteCustomerTest extends BaseTest {

    @Test (dataProviderClass=DataUtil.class, dataProvider="data")
    public void deleteCustomer(Hashtable<String, String> data) {

        System.out.println(data.toString());
        ExtentListeners.testReport.get().info(data.toString());

        Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);
        Assert.assertEquals(response.statusCode(), 200);

        /*
        String actualID = response.jsonPath().get("id").toString();
        System.out.println("Getting ID from jsonpath: " + actualID);
        Assert.assertEquals(actualID, data.get("id"), "ID not matching.");
         */

        /*
        JSONObject jsonObject = new JSONObject(response.asString());
        Assert.assertTrue(jsonObject.has("id"), "ID is not present.");
        String actualID = jsonObject.get("id").toString();
        Assert.assertEquals(actualID, data.get("id"), "ID not matching.");
         */

        Assert.assertTrue(jsonHasKey(response.asString(), "id"));
        Assert.assertEquals(getJsonKeyValue(response.asString(), "id"), data.get("id"));

        Assert.assertTrue(jsonHasKey(response.asString(), "object"));
        Assert.assertEquals(getJsonKeyValue(response.asString(), "object"), "customer");

        Assert.assertTrue(jsonHasKey(response.asString(), "deleted"));
        Assert.assertEquals(getJsonKeyValue(response.asString(), "deleted"), "true");
    }
}
