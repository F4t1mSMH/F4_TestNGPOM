package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BACustomerManagementPage {
    public BACustomerManagementPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@ng-click='deleteCust(cust)']")
    public WebElement deleteCust;

}