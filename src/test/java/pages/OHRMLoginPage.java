package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.Driver;

public class OHRMLoginPage {

    private By username = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By submit = By.xpath("//button[@type='submit']");
    private By errorForUsername = By.xpath("//div[@class='oxd-form-row' and contains(., 'Username')]//span");
    private By errorForPassword = By.xpath("//div[@class='oxd-form-row' and contains(., 'Password')]//span");
    private By invalidCredentials = By.xpath("//p[.='Invalid credentials']");

    public OHRMLoginPage enterUsername(String username) {
        Driver.getDriver().findElement(this.username).sendKeys(username);
        return this;
    }

    public OHRMLoginPage enterPassword(String password) {
        Driver.getDriver().findElement(this.password).sendKeys(password);
        return this;
    }

    public OHRMDashboardPage clickLogin() {
        Driver.getDriver().findElement(submit).click();
        return new OHRMDashboardPage();
    }

    public OHRMLoginPage assertUsernameError() {
        Assert.assertEquals(Driver.getDriver().findElement(errorForUsername).getText(), "Required");
        return this;
    }


    public OHRMLoginPage assertPasswordError() {
        Assert.assertEquals(Driver.getDriver().findElement(errorForPassword).getText(), "Required");
        return this;
    }


    public OHRMLoginPage assertInvalidCredentials() {
        Assert.assertEquals(Driver.getDriver().findElement(invalidCredentials).getText(), "Invalid credentials");
        return this;
    }


}
