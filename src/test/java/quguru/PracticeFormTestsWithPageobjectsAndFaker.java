package guru.qa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class PracticeFormTestsWithPageobjectsAndFaker {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setup() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {

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
        String adress = faker.address().fullAddress();
        String state = "NCR";
        String city = "Noida";


        registrationPage.openPage();
        registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(gender)
                .typePhone(phone)
                .setDateOfBirth(day, month, year)
                .typeSubjects(subjects)
                .typeHobbies(hobbies)
                .uploudFile(picture)
                .typeAdress(adress)
                .selectState(state)
                .selectCity(city);
        registrationPage.clickButton();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(firstName + " " + lastName)
                .checkResultsValue(email)
                .checkResultsValue(gender)
                .checkResultsValue(phone)
                .checkResultsValue(day + " " + month + "," + year)
                .checkResultsValue(subjects)
                .checkResultsValue(hobbies)
                .checkResultsValue(picture)
                .checkResultsValue(adress)
                .checkResultsValue(state + " " + city);
    }

}
