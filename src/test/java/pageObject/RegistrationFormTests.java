package pageObject;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObject.component.Hobbies;
import pageObject.pages.RegistrationFormPage;

import static pageObject.component.Hobbies.Music;

public class RegistrationFormTests {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.holdBrowserOpen = true;
    }


    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String fullName = String.format("%s %s", firstName, lastName);
    String email = faker.internet().emailAddress();
    String phone = "1111111111";
    String day = "20";
    String month = "July";
    String year = "2000";
    String date = String.format("%s %s,%s", day, month, year);
    String address = faker.address().fullAddress();
    String gender = "Male";
    String subjects = "Math";
    Hobbies hobbies = Music;
    String imgPath = "dog.jpeg";
    String state = "NCR";
    String city = "Delhi";
    String stateCity = String.format("%s %s", state, city);

    @Test
    public void firstTest() {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setBirthDate(day, month, year)
                .setUserPhone(phone)
                .setUserAddress(address)
                .uploadPictures(imgPath)
                .setHobbies(hobbies.getHobbies())
                .setState(state)
                .setCity(city)
                .setSubject(subjects)
                .submitClick();


        registrationFormPage
                .checkResult("Student Name", fullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", date)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies.name())
                .checkResult("Picture", imgPath)
                .checkResult("Address", address)
                .checkResult("State and City", stateCity);
    }
}
