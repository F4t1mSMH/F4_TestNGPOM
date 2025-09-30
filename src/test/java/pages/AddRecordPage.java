package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddRecordPage {
    private By nameInput = By.id("nameInput");
    private By ageInput = By.id("ageInput");
    private By countrySelect = By.id("countrySelect");
    private By addRecordButton = By.xpath("//button[@onclick='addRecord()']");
    private String deleteButtonByNameXPath = "//tr[contains(.,'%s')]//button[@class='delete-button']";
    private By tableBody = By.id("tableBody");


    public AddRecordPage enterName(String name) {
        Driver.getDriver().findElement(nameInput).sendKeys(name);
        return this;
    }


    public AddRecordPage enterAge(String age) {
        Driver.getDriver().findElement(ageInput).sendKeys(age);
        return this;
    }


    public AddRecordPage selectCountry(String visibleText) {
        new Select(Driver.getDriver().findElement(countrySelect)).selectByVisibleText(visibleText);
        return this;
    }

    public AddRecordPage clickOnAddRecord() {
        Driver.getDriver().findElement(addRecordButton).click();
        return this;
    }

    public AddRecordPage deleteRecordByName(String recordName) {
        Driver.getDriver().findElement(By.xpath(String.format(deleteButtonByNameXPath, recordName))).click();
        return this;
    }

    public AddRecordPage assertTableContainsText(String text) {
        assertTrue(Driver.getDriver().findElement(tableBody).getText().contains(text));
        return this;
    }

    public AddRecordPage assertTableNotContainsText(String text) {
        assertFalse(Driver.getDriver().findElement(tableBody).getText().contains(text));
        return this;
    }
    public AddRecordPage handleAlertIfPresent() {
        try {
            Driver.getDriver().switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
        }
        return this;
    }


}
