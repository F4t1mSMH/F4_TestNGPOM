package tasks;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ExtentReportManager;

public class T04_FailedTestCapture {

    @Test
    void testFail() {
        System.out.println("FAIL");
        Driver.getDriver().get("https://Yahoo.com");
        ExtentReportManager.log(Status.INFO, "We are on yahoo.");
        assert false;
    }
}
