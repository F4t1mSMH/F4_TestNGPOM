package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CLAddUserPage;
import pages.CLContactListPage;
import pages.CLContactPage;
import pages.CLHomePage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.time.ZoneId;

import static utilities.Driver.getDriver;

public class C01_ContactList {

    /*
    Target: https://thinking-tester-contact-list.herokuapp.com/
    Test Scenario:
    1. Navigate to the application
    2. Create a new user account
    3. Login with the created user
    4. Add 5 different contacts
    5. Assert that all contacts are properly added and displayed
     */

    @Test
    void contactListTest() {
        CLHomePage homePage = new CLHomePage();
        CLAddUserPage adduserPage = new CLAddUserPage();
        CLContactListPage contactList = new CLContactListPage();
        CLContactPage contact = new CLContactPage();
        getDriver().get(ConfigReader.getProperty("cl_url"));
        homePage.signUp.click();

        //adding user using faker on sign up page
        adduserPage.firstname.sendKeys(Faker.instance().name().firstName());
        adduserPage.lastname.sendKeys(Faker.instance().name().lastName());
        adduserPage.email.sendKeys(Faker.instance().internet().emailAddress());
        adduserPage.password.sendKeys(Faker.instance().internet().password());
        adduserPage.submit.click();
        // contactList.addContact.click();

        for (int i = 0 ; i < 5 ; i++){
            contactList.addContact.click();
            contact.firstname.sendKeys(Faker.instance().name().firstName());
            contact.lastname.sendKeys(Faker.instance().name().lastName());
            contact.birthdate.sendKeys(Faker.instance().date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
            contact.email.sendKeys(Faker.instance().internet().emailAddress());
            contact.phone.sendKeys(Faker.instance().phoneNumber().subscriberNumber(10));
            contact.street1.sendKeys(Faker.instance().address().streetAddress());
            contact.street2.sendKeys(Faker.instance().address().streetAddress());
            contact.city.sendKeys(Faker.instance().address().city());
            contact.stateProvince.sendKeys(Faker.instance().address().state());
            contact.postalCode.sendKeys(Faker.instance().address().zipCode());
            contact.country.sendKeys(Faker.instance().address().country());

            contact.submit.click();
            Driver.wait.until(ExpectedConditions.urlContains("contactList"));
        }

        Assert.assertEquals(contactList.contactRows.size(), 5);
        Driver.closeDriver();
    }
}