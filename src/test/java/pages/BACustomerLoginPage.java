package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class BACustomerLoginPage {
    public BACustomerLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//button[@ng-click='customer()']")
    public WebElement LogButton;

    @FindBy(xpath = "//select[@id='userSelect']")
    public WebElement userDropdown;

    @FindBy(xpath = "//button[@type='submit' and text()='Login']")
    public WebElement submitButton;

    public void loginAsCustomerByValue(String userValue) {
        new Select(userDropdown).selectByValue(userValue);
        submitButton.click();
    }

    @FindBy(xpath = "//button[@ng-click='byebye()']")
    public WebElement logOut;
}