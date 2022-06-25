package pageObject.pages;

import com.codeborne.selenide.SelenideElement;
import pageObject.component.CalendarComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();

    // locators
    SelenideElement practiceForm = $(".practice-form-wrapper");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement genderInput = $("#genterWrapper");
    SelenideElement phoneInput = $("#userNumber");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement dateOfBirthdayInput = $("#dateOfBirthInput");
    SelenideElement pictureInput = $("#uploadPicture");
    SelenideElement cityInput = $("#city");
    SelenideElement stateInput = $("#state");
    SelenideElement stateCityWrapperInput = $("#stateCity-wrapper");
    SelenideElement buttonSubmit = $("#submit");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement tableResponse = $(".table-responsive");


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        practiceForm.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        dateOfBirthdayInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage setUserPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public RegistrationFormPage setUserAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationFormPage uploadPictures(String imgPath) {
        pictureInput.uploadFromClasspath(imgPath);
        return this;
    }

    public RegistrationFormPage setHobbies(int hobbies) {
        $("[for=hobbies-checkbox-" + hobbies + "]").click();
        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateInput.click();
        stateCityWrapperInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityInput.click();
        stateCityWrapperInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage submitClick() {
        buttonSubmit.click();
        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setSubject(List<String> value) {
        for (String s: value) {
            subjectsInput.setValue(s).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        tableResponse.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }
}
