package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BAManagerLoginPage {
    public BAManagerLoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    //(manager interface elements)
    @FindBy(xpath = "//button[@ng-click='manager()']")
    public WebElement mangerButton;
    @FindBy(xpath = "//button[@ng-click='customer()']")
    public WebElement userButton;
    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    public WebElement openButton;
    @FindBy(xpath = "//button[@ng-click='showCust()']")
    public WebElement showCustButton;
}
