package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BATransactionPage {
    public BATransactionPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//button[@ng-click='deposit()']")
    public WebElement deposit;

    @FindBy(xpath = "//input[@ng-model='amount']")
    public WebElement amount;

    @FindBy(xpath = "//form//button[@type='submit']")
    public WebElement depositSubmitButton;

    public void submitTransaction(WebElement submitButtonElement, int amountValue) {
        this.amount.clear();
        this.amount.sendKeys(String.valueOf(amountValue));
        submitButtonElement.click();
    }

    @FindBy(xpath = "//div[@ng-hide='noAccount']//strong[2]")
    public WebElement balanceDisplay;

    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    public WebElement withdrawlButton;

    @FindBy(xpath = "//button[@type='submit' and text()='Withdraw']")
    public WebElement submitWithdrawl;





}

