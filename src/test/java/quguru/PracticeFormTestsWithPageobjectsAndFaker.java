package quguru;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class PracticeFormTestsWithPageobjectsAndFaker extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    @BeforeAll
    static void setup() {
        //   baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {

       // startMaximized = true;
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = "Other";
        String phone = faker.numerify("##########");
        String day = "10";
        String month = "May";
        String year = "1993";
        String subjects = "Math";
        String hobbies = "Music";
        String picture = "1.png";
        String address = faker.address().fullAddress();
        String state = "NCR";
        String city = "Noida";


        step("Open students registration form", () -> {
            registrationPage.openPage();
        });
        step("Fill students registration form", () -> {
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeEmail(email)
                    .selectGender(gender)
                    .typePhone(phone)
                    .setDateOfBirth(day, month, year)
                    .typeSubjects(subjects)
                    .typeHobbies(hobbies)
                    .uploudFile(picture)
                    .typeAdress(address)
                    .selectState(state)
                    .selectCity(city);
            registrationPage.clickButton();
        });
        step("Verify successful form submit", () -> {
            registrationPage.checkResultsTitle();
            registrationPage.checkResultsValue(firstName + " " + lastName)
                    .checkResultsValue(email)
                    .checkResultsValue(gender)
                    .checkResultsValue(phone)
                    .checkResultsValue(day + " " + month + "," + year)
                    .checkResultsValue(subjects)
                    .checkResultsValue(hobbies)
                    .checkResultsValue(picture)
                    .checkResultsValue(address)
                    .checkResultsValue(state + " " + city);
        });
    }
}
