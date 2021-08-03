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


//        step("Open students registration form", () -> {
//        registrationPage.openPage();});
//        step("Fill students registration form", () -> {
//        registrationPage.typeFirstName(firstName)
//                .typeLastName(lastName)
//                .typeEmail(email)
//                .selectGender(gender)
//                .typePhone(phone)
//                .setDateOfBirth(day, month, year)
//                .typeSubjects(subjects)
//                .typeHobbies(hobbies)
//                .uploudFile(picture)
//                .typeAdress(adress)
//                .selectState(state)
//                .selectCity(city);
//        registrationPage.clickButton();});
//        step("Verify successful form submit", () -> {
//        registrationPage.checkResultsTitle();
//        registrationPage.checkResultsValue(firstName + " " + lastName)
//                .checkResultsValue(email)
//                .checkResultsValue(gender)
//                .checkResultsValue(phone)
//                .checkResultsValue(day + " " + month + "," + year)
//                .checkResultsValue(subjects)
//                .checkResultsValue(hobbies)
//                .checkResultsValue(picture)
//                .checkResultsValue(adress)
//                .checkResultsValue(state + " " + city);});
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                $("#firstName").val(firstName);
                $("#lastName").val(lastName);
                $("#userEmail").val(email);
                $("#genterWrapper").$(byText(gender)).click();
                $("#userNumber").val(phone);
            });
            step("Set date", () -> {
                $("#dateOfBirthInput").clear();
                $(".react-datepicker__month-select").selectOption(month);
                $(".react-datepicker__year-select").selectOption(year);
                $(".react-datepicker__day--0" + day).click();
            });
            step("Set subjects", () -> {
                $("#subjectsInput").val(subjects);
                $("#subjectsInput").setValue(subjects).pressEnter();
            });
            step("Set hobbies", () -> {
                $("#hobbiesWrapper").$(byText(hobbies)).click();
            });
            step("Upload image", () ->
                    $("#uploadPicture").uploadFromClasspath(picture));
            step("Set address", () -> {
                $("#currentAddress").val(address);
                $("#state").scrollTo().click();
                $("#stateCity-wrapper").$(byText(state)).click();
                $("#city").click();
                $("#stateCity-wrapper").$(byText(city)).click();
            });
            step("Submit form", () ->
                    $("#submit").click());
        });

        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']").parent().shouldHave(text(email));
            $x("//td[text()='Gender']").parent().shouldHave(text(gender));
            $x("//td[text()='Mobile']").parent().shouldHave(text(phone));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(day + " " + month + "," + year));
            $x("//td[text()='Subjects']").parent().shouldHave(text(subjects));
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobbies));
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']").parent().shouldHave(text(address));
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });



    }

}
