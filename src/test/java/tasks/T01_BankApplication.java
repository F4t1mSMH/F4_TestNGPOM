package tasks;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import java.awt.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import static utilities.Driver.getDriver;

public class T01_BankApplication {
    BAAccountManagementPage accountManagement = new BAAccountManagementPage();
    BACustomerManagementPage customerManagement = new BACustomerManagementPage();
    BACustomerLoginPage customerLogin = new BACustomerLoginPage();
    BAManagerLoginPage managerLogin = new BAManagerLoginPage();
    BATransactionPage transactionPage = new BATransactionPage();

    public static final List<String> CUSTOMER_VALUES = Arrays.asList(
            "1", // Hermione
            "2", // Harry
            "3", // Ron
            "4", // Albus
            "5"  // Neville
    );
    private final int DEPOSIT_AMOUNT = 100;
    private final int WITHDRAW_AMOUNT = 100;
//Test Scenario:
// 1. Open 5 new customer accounts /done!
// 2. Deposit 100 USD to each account
// 3. Withdraw 100 USD from any one account
// 4. Delete all created accounts
// 5. Verify account operations
    @Test(priority = 1)
    void accountCreateTest() throws AWTException {
        getDriver().get(ConfigReader.getProperty("ba_url"));
        // Manager Login
        managerLogin.mangerButton.click();
        accountManagement.customer.click();
        for (int i = 0; i < 5; i++) {
            // Fill in customer details
            accountManagement.fName.sendKeys(Faker.instance().name().firstName());
            accountManagement.lName.sendKeys(Faker.instance().name().lastName());
            accountManagement.postCd.sendKeys(Faker.instance().address().buildingNumber());
            accountManagement.submit.click();

            try {
                WebDriver driver;
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.alertIsPresent());
                String alertText = Driver.getDriver().switchTo().alert().getText();
                System.out.println("Alert text: " + alertText);
                Driver.getDriver().switchTo().alert().accept();
                System.out.println("Alert handled successfully.");
            } catch (TimeoutException e) {
                System.out.println("No alert was present after form submission.");
            }
        }

    }

    @Test(priority = 2)
    void depositToAllCustomersTest() {
        getDriver().get(ConfigReader.getProperty("ba_url"));
        managerLogin.userButton.click();
        for (String userValue : CUSTOMER_VALUES) {
            customerLogin.loginAsCustomerByValue(userValue);
            transactionPage.deposit.click();
            transactionPage.submitTransaction(transactionPage.depositSubmitButton, DEPOSIT_AMOUNT);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@ng-show='message']")));
            Assert.assertTrue(successMessage.getText().contains("Deposit Successful"), "Deposit success message verification failed.");

            customerLogin.logOut.click();
        }
    }


    @Test(priority = 3)
    void withdrawTest() {
        getDriver().get(ConfigReader.getProperty("ba_url"));
        managerLogin.userButton.click();
        String targetCustomerValue = CUSTOMER_VALUES.get(0);
        customerLogin.loginAsCustomerByValue(targetCustomerValue);

        WebDriverWait balanceWait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        balanceWait.until(ExpectedConditions.visibilityOf(transactionPage.balanceDisplay));

        String initialBalanceText = transactionPage.balanceDisplay.getText();
        int initialBalance;
        try {
            initialBalance = Integer.parseInt(initialBalanceText.trim());
        } catch (NumberFormatException e) {
            initialBalance = 0;
        }

        final int EXPECTED_FINAL_BALANCE = initialBalance - WITHDRAW_AMOUNT;
        String expectedFinalBalanceText = String.valueOf(EXPECTED_FINAL_BALANCE);


        transactionPage.withdrawlButton.click();
        transactionPage.submitTransaction(transactionPage.submitWithdrawl, WITHDRAW_AMOUNT);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@ng-show='message']")));
        Assert.assertTrue(successMessage.getText().contains("Transaction successful"), "Withdrawal success message verification failed.");


        balanceWait.until(ExpectedConditions.textToBePresentInElement(transactionPage.balanceDisplay, expectedFinalBalanceText));
        String actualBalanceText = transactionPage.balanceDisplay.getText();
        Assert.assertTrue(actualBalanceText.contains(expectedFinalBalanceText), "Final balance verification failed! Expected balance to contain '" + expectedFinalBalanceText + "', but found: '" + actualBalanceText + "'");
        customerLogin.logOut.click();
    }

    @Test(priority = 4)
    void finalCleanupTest() {
        getDriver().get(ConfigReader.getProperty("ba_url"));
        managerLogin.mangerButton.click();
        managerLogin.showCustButton.click();

        By allDeleteButtons = By.xpath("//button[text()='Delete']");

        while (!getDriver().findElements(allDeleteButtons).isEmpty()) {
            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(getDriver().findElement(allDeleteButtons))).click();
        }

        Assert.assertTrue(getDriver().findElements(allDeleteButtons).isEmpty());
        System.out.println("All customers cleared!");
    }
}

