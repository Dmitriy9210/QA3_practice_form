package pageObject;

import org.junit.jupiter.api.Test;
import pageObject.pages.RegistrationFormPage;

public class RegistrationFormTests extends SetUpClass{


    @Test
    public void firstTest() {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(getFirstName())
                .setLastName(getLastName())
                .setEmail(getEmail())
                .setGender(getGender())
                .setBirthDate(getDay(), getMonth(), getYear())
                .setUserPhone(getPhone())
                .setUserAddress(getAddress())
                .uploadPictures(getImgPath())
                .setHobbies(getHobbies().getHobbies())
                .setState(getState())
                .setCity(getCity())
                .setSubject(getSubjects())
                .submitClick();


        registrationFormPage
                .checkResult("Student Name", getFullName())
                .checkResult("Student Email", getEmail())
                .checkResult("Gender", getGender())
                .checkResult("Mobile", getPhone())
                .checkResult("Date of Birth", getDate())
                .checkResult("Subjects", getSubjects())
                .checkResult("Hobbies", getHobbies().name())
                .checkResult("Picture", getImgPath())
                .checkResult("Address", getAddress())
                .checkResult("State and City", getStateCity());
    }
}
