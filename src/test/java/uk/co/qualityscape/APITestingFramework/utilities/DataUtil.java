package uk.co.qualityscape.APITestingFramework.utilities;

import org.testng.annotations.DataProvider;
import uk.co.qualityscape.APITestingFramework.setup.BaseTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    // My comment 2
    @DataProvider (name = "data")
    public Object[][] getData(Method m) {

        int rows = excel.getRowCount(config.getProperty("testDataSheet"));

        // Find the test case start row.
        String testName = m.getName();
        System.out.println("Test name is: " + testName);

        int testCaseRowNum = 1;
        for (testCaseRowNum = 1; testCaseRowNum < rows; testCaseRowNum++) {

            String testCaseName = excel.getCellData(config.getProperty("testDataSheet"), 0, testCaseRowNum);
            if (testCaseName.equalsIgnoreCase(testName)) {
                break;
            }
        }
        System.out.println("Test case starts from row: "+ testCaseRowNum);
        int dataStartRowNum = testCaseRowNum + 2;

        // Calculate how many rows of data are present for this test case.
        int testRows = 0;
        while (!excel.getCellData(config.getProperty("testDataSheet"), 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }
        System.out.println("Total data rows: " + testRows);

        // Get the total columns in the test case.
        int colStartColNum = testCaseRowNum + 1;
        int testCols = 0;
        while (!excel.getCellData(config.getProperty("testDataSheet"), testCols, colStartColNum).equals("")) {
            testCols++;
        }
        System.out.println("Total columns in test case: " + testCols);

        // Print out the test data.
        Object[][] data = new Object[testRows][1];
        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

            Hashtable<String, String> table = new Hashtable<String, String>();

            for (int cNum = 0; cNum < testCols; cNum++) {
                String testData = excel.getCellData(config.getProperty("testDataSheet"), cNum, rNum);
                String colName = excel.getCellData(config.getProperty("testDataSheet"), cNum, colStartColNum);
                table.put(colName, testData);
            }
            data[i][0] = table;
            i++;
        }
        System.out.println(Arrays.deepToString(data));
        return data;
    }
}
