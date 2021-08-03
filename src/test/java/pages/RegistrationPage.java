package pages;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationPage {
    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private SelenideElement modal = $("[role=dialog]");

    private Calendar calendar = new Calendar();

    public void openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }

    public RegistrationPage typeFirstName(String firstName){
        $("#firstName").setValue(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName){
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email){
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender){
        $(format("[name=gender][value=%s]", gender)).parent().click();
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year){
        calendar.setDate(day,month,year);
        return this;
    }

    public RegistrationPage typePhone(String phone){
        $("#userNumber").setValue(phone);
        return this;
    }

    public RegistrationPage typeSubjects(String subjects){
        $("#subjectsInput").setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationPage typeHobbies(String hobbies){
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage uploudFile(String picture){
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
        return this;
    }

    public RegistrationPage typeAdress(String adress){
        $("#currentAddress").setValue(adress);
        return this;
    }

    public RegistrationPage selectState(String state){
        $("#react-select-3-input").setValue(state).pressEnter();
        return this;
    }

    public RegistrationPage selectCity(String city){
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    public void clickButton(){
        $("#submit").click();
    }

    public void checkResultsTitle(){
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
    }

    public RegistrationPage checkResultsValue(String value) {
        modal.$(".table-responsive").shouldHave(text(value));
        return this;
    }

}
