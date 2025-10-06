package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.CLRegistrationPage;
import utilities.Driver;

public class C08_CLRegistration {

    private CLRegistrationPage registrationPage;

    @Test
    void happyPath() {
        registrationPage = new CLRegistrationPage();
        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addUser");
        registrationPage
                .enterFirstname(Faker.instance().name().firstName())
                .enterLastname(Faker.instance().name().lastName())
                .enterEmail(Faker.instance().internet().emailAddress())
                .enterPassword("Password.123")
                .clickSubmit()
                .assertAddContactButton();
    }

    @Test
    void emptyFirstnameTest() {
        registrationPage = new CLRegistrationPage();
        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addUser");
        registrationPage
                .enterLastname(Faker.instance().name().lastName())
                .enterEmail(Faker.instance().internet().emailAddress())
                .enterPassword("Password.123")
                .clickSubmit()
                .assertErrorMessage("`firstName` is required.");
    }


    @Test
    void firstnameWithSpecialCharTest() {
        registrationPage = new CLRegistrationPage();
        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addUser");
        registrationPage
                .enterFirstname("J@hn")
                .enterLastname(Faker.instance().name().lastName())
                .enterEmail(Faker.instance().internet().emailAddress())
                .enterPassword("Password.123")
                .clickSubmit()
                .assertErrorMessage("firstName");
    }


    @Test
    void firstnameWithNumbersTest() {
        registrationPage = new CLRegistrationPage();
        Driver.getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addUser");
        registrationPage
                .enterFirstname("John123")
                .enterLastname(Faker.instance().name().lastName())
                .enterEmail(Faker.instance().internet().emailAddress())
                .enterPassword("Password.123")
                .clickSubmit()
                .assertErrorMessage("firstName");
    }

    //Rest is homework!


}
