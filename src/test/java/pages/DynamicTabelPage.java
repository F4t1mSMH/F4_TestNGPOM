package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.Driver;

import java.util.List;

public class DynamicTabelPage {

        private  By nameInput = By.id("nameInput");
        private  By ageInput = By.id("ageInput");
        private  By countrySelect = By.id("options");
        private  By addRecordButton = By.id("submitButton");
        private  By tableRows = By.xpath("//table[@id='webTable']//tbody/tr");
        private  By deleteButton = By.xpath("//button[text()='Delete']");

        public DynamicTabelPage enterName(String name) {
            Driver.getDriver().findElement(nameInput).sendKeys(name);
            return this;
        }

        public DynamicTabelPage enterAge(String age) {
            Driver.getDriver().findElement(ageInput).sendKeys(age);
            return this;
        }

        public DynamicTabelPage selectCountry(String country) {
            new Select(Driver.getDriver().findElement(countrySelect)).selectByVisibleText(country);
            return this;
        }

        public DynamicTabelPage addRecord() {
            Driver.getDriver().findElement(addRecordButton).click();
            return this;
        }

        public DynamicTabelPage assertRecordExists(String name, String age, String country) {
            String expectedRowContent = name + age + country;
            boolean recordFound = false;

            List<WebElement> rows = Driver.getDriver().findElements(tableRows);

            for (WebElement row : rows) {
                String actualRowText = row.getText().replace("Delete", "").replaceAll("\\s+", "");
                if (actualRowText.contains(expectedRowContent.replaceAll("\\s+", ""))) {
                    recordFound = true;
                    break;
                }
            }
            Assert.assertTrue(recordFound, "record not found" + name);
            return this;
        }

        public DynamicTabelPage deleteRecord(String name) {
            List<WebElement> rows = Driver.getDriver().findElements(tableRows);
            for (WebElement row : rows) {
                if (row.getText().contains(name)) {
                    row.findElement(deleteButton).click();
                    return this;
                }
            }
            return this;
        }
    }