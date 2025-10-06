package tasks;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import utilities.Driver;

public class T02_DynamicTableManage {
    AddRecordPage webTablePage = new AddRecordPage();

    @Test(dataProvider = "addFakeRecords", dataProviderClass = utilities.MyDataProvider.class)
    public void dynamicTableTest(String name, String age, String country) {
        Driver.getDriver().get("https://claruswaysda.github.io/addRecordWebTable.html");
        {
            webTablePage.enterName(name)
                    .enterAge(age)
                    .selectCountry(country)
                    .clickOnAddRecord()
                    .assertTableContainsText(name)
                    .assertTableContainsText(country);
        }
        webTablePage.deleteJohnRecord()
                .handleAlertIfPresent();
    }
}