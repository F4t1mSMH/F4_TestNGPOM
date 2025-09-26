package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BAAccountManagementPage {
    public BAAccountManagementPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@ng-click='addCust()']")
    public WebElement customer;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement fName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lName;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    public WebElement postCd;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submit;
}
