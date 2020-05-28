package uk.co.qualityscape.APITestingFramework.utilities;

import org.json.JSONObject;
import org.testng.Assert;
import uk.co.qualityscape.APITestingFramework.extentlisteners.ExtentListeners;

public class TestUtil {

    public static boolean jsonHasKey(String json, String key) {

        System.out.println("Checking for key in JSON: " + key);
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the presence of: " + key);
        return jsonObject.has("id");
    }

    public static String getJsonKeyValue(String json, String key) {

        System.out.println("Returning value for key: " + key);
        ExtentListeners.testReport.get().info("Validating the value of key: " + key);
        JSONObject jsonObject = new JSONObject(json);
        String value = jsonObject.get(key).toString();
        System.out.println("= " + value);
        return value;
    }
}
