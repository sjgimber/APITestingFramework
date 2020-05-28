package uk.co.qualityscape.APITestingFramework.setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.*;
import uk.co.qualityscape.APITestingFramework.utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    public static Properties config = new Properties();
    private FileInputStream fis;
    public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    @BeforeSuite
    public void setUp() {

        try {
            fis = new FileInputStream("./src/test/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseURI = config.getProperty("baseURI");
        basePath = config.getProperty("basePath");
    }

    /*
    @AfterSuite
    public void tearDown() {

    }
*/

    @BeforeTest
    public void setupReport() {

        htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Qualityscape Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Automation Tester", "John Gimber");
        extentReports.setSystemInfo("Organisation", "Qualityscape");
        extentReports.setSystemInfo("Build No.", "1.2.3");
    }

    @AfterTest
    public void endReport() {
        extentReports.flush();
    }

    /*
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            extentTest.fail("<details><summary><b><font color=red>" +
                    "Exception occurred: click to see" +
                    "</font></b></summary>" +
                    exceptionMessage.replaceAll(",", "<br>") +
                    "</details>\n");

            String failureLog = "TEST CASE FAILED";
            Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
            extentTest.log(Status.FAIL, m);

        } else if (result.getStatus() == ITestResult.SKIP) {

            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "TEST CASE: " + methodName + " SKIPPED" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
            extentTest.skip(m);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "TEST CASE: " + methodName + " PASSED" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentTest.pass(m);
        }
    }

     */
}
