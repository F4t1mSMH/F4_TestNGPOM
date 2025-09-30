package tests;

import com.aventstack.extentreports.Status;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ExtentReportManager;
import utilities.MyTestListener;

//@Listeners(MyTestListener.class)
public class C06_ExtentReport {

    @Test
    void test01() {
        System.out.println("PASS");
        Driver.getDriver().get("https://google.com");
        ExtentReportManager.log(Status.INFO, "We are on Google.");
        Driver.closeDriver();
    }

    @Test
    void test02() {
        System.out.println("FAIL");
        Driver.getDriver().get("https://instagram.com");
        ExtentReportManager.log(Status.INFO, "We are on Instagram.");
        assert false;
    }

    @Test
    void test03() {
        System.out.println("SKIP");
        throw new SkipException("");
    }
}
