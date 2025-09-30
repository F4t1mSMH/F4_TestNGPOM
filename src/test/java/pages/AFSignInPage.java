package pages;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.Driver;

public class AFSignInPage {
    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By signInButton = By.xpath("//input[@type='submit']");
    private By employeeHeader = By.id("employeeHeader");
    private By usernameError = By.id("usernameError");
    private By passwordError = By.id("passwordError");
    private By invalidCredentials = By.xpath("//p[.='Invalid credentials']");


    public AFSignInPage enterUser(String username) {
        Driver.getDriver().findElement(this.username).sendKeys(username);
        return this;
    }

    public  AFSignInPage enterPass(String password) {
        Driver.getDriver().findElement(this.password).sendKeys(password);
        return this;
    }

    public AFLogInPage clickSignIn() {
        Driver.getDriver().findElement(signInButton).click();
        return new AFLogInPage();
    }

    public AFSignInPage handleAlertAndSoftAssertError(SoftAssert softAssert, String expectedAlertText, String errorMessage) {
        String actualAlertText = Driver.getDriver().switchTo().alert().getText();
        softAssert.assertEquals(actualAlertText, expectedAlertText, errorMessage);

        Driver.getDriver().switchTo().alert().accept();
        return this;
    }

    public AFSignInPage assertUserError() {
        Assert.assertEquals(Driver.getDriver().findElement(usernameError).getText(), "Incorrect username or password");
        return this;
    }


    public AFSignInPage assertPassError() {
        Assert.assertEquals(Driver.getDriver().findElement(passwordError).getText(), "Incorrect username or password");
        return this;
    }


    public AFSignInPage assertInvalidCred() {
        Assert.assertEquals(Driver.getDriver().findElement(invalidCredentials).getText(), "Incorrect username or password");
        return this;
    }


}