package tasks;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AFSignInPage;
import utilities.Driver;


public class T03_AFSignInPageTest {

    private AFSignInPage SignInPage = new AFSignInPage();
    private final String expectedAlertText = "Incorrect username or password";
    SoftAssert softAssert = new SoftAssert();

    @Test
    void AFSignInPageTest() {//Happy Path
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
                .enterUser("admin")
                .enterPass("123")
                .clickSignIn()
                .assertSingUp();
        Driver.closeDriver();
    }
    @Test
    void AFSignInPageTest1() {
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
                .enterUser("Admin")//wrong user
                .enterPass("123")
                .clickSignIn()
                .handleAlertAndSoftAssertError(softAssert, expectedAlertText, "Validation Error: Empty Username failed to trigger correct alert.");
        softAssert.assertAll();
        Driver.closeDriver();
    }

    @Test
    void AFSignInPageTest2()  {
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
                //.enterUsername("admin") no user
                .enterPass("123")
                .clickSignIn()
                .handleAlertAndSoftAssertError(softAssert, expectedAlertText, "Validation Error: Empty Username failed to trigger correct alert.");
        softAssert.assertAll();
        Driver.closeDriver();
    }

    @Test
    void AFSignInPageTest3() {
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
                .enterUser("admin")
                //.enterPassword("123") no pass
                .clickSignIn()
                .handleAlertAndSoftAssertError(softAssert, expectedAlertText, "Validation Error: Empty Username failed to trigger correct alert.");
        softAssert.assertAll();
        Driver.closeDriver();
    }

    @Test
    void AFSignInPageTest4() {
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
//                .enterUsername("admin") no user and pass
//                .enterPassword("123")
                .clickSignIn()
                .handleAlertAndSoftAssertError(softAssert, expectedAlertText, "Validation Error: Empty Username failed to trigger correct alert.");
        softAssert.assertAll();
        Driver.closeDriver();
    }
    @Test
    void AFSignInPageWeakPassword() {
        AFSignInPage signInPage = new AFSignInPage();
        Driver.getDriver().get("https://claruswaysda.github.io/signIn.html");
        signInPage
                .enterUser("admin")
                .enterPass("password")
                .clickSignIn()
                .handleAlertAndSoftAssertError(softAssert, expectedAlertText, "Validation Error: Weak password did not trigger an expected error.");

        softAssert.assertAll();
        Driver.closeDriver();
    }
}