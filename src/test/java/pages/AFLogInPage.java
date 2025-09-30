package pages;

import org.openqa.selenium.By;
import utilities.Driver;

public class AFLogInPage extends AFSignInPage{
    private By employeeHeader = By.xpath("//h1");

    public AFLogInPage assertSingUp() {
        assert Driver.getDriver().findElement(employeeHeader).isDisplayed();
        return this;
    }
   }