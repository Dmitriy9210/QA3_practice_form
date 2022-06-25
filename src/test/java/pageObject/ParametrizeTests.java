package pageObject;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pageObject.pages.RegistrationFormPage;

import java.util.List;
import java.util.stream.Stream;

public class ParametrizeTests extends SetUpClass {

    @ValueSource(strings = {
            "Ivan", "Petr", "Dima"
    })
    @ParameterizedTest(name = "Проверка регистрации пользователя с именем: {0}")
    void test1(String name) {
        String lastname = getLastName();
        String fullName = String.format("%s %s", name, lastname);
        String gender = "Other";
        String phone = "1234567890";
        String day = "20";
        String month = "July";
        String year = "2000";
        String date = String.format("%s %s,%s", day, month, year);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastname)
                .setGender(gender)
                .setBirthDate(getDay(), getMonth(), getYear())
                .setUserPhone(phone)
                .submitClick();

        checkForm(fullName, gender, phone, date);
    }

    @CsvSource({
            "Ivan, Ivanov",
            "Petr, Petrov",
            "Dima, Pushkin"
    })
    @ParameterizedTest(name = "Проверка регистрации пользователя с именем: {0}, фамилией: {1}")
    void test2(String name, String lastname) {
        String fullName = String.format("%s %s", name, lastname);
        String gender = "Other";
        String phone = "1234567890";
        String day = "20";
        String month = "July";
        String year = "2000";
        String date = String.format("%s %s,%s", day, month, year);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastname)
                .setGender(gender)
                .setBirthDate(getDay(), getMonth(), getYear())
                .setUserPhone(phone)
                .submitClick();

        checkForm(fullName, gender, phone, date);
    }

    @CsvSource(value = {
            "Ivanddd Sidorovddd 1234567890",
            "Petrddd Petrovddd 0111654321",
            "Dimaddd Pushkinddd1231231234"
    }, delimiterString = "ddd")
    @ParameterizedTest(name = "Проверка регистрации пользователя с именем: {0}, фамилией: {1} и телефоном: {2}")
    void test3(String name, String lastname, int phone) {
        String fullName = String.format("%s %s", name, lastname);
        String gender = "Other";
        String day = "20";
        String month = "July";
        String year = "2000";
        String date = String.format("%s %s,%s", day, month, year);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastname)
                .setGender(gender)
                .setBirthDate(getDay(), getMonth(), getYear())
                .setUserPhone(String.valueOf(phone))
                .submitClick();

        checkForm(fullName, gender, String.valueOf(phone), date);
    }


    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("Petr", "Ivanov", List.of("Maths", "English", "Arts")),
                Arguments.of("Vasia", "Jgunov", List.of("Maths", "English", "Arts")),
                Arguments.of("Lera", "Doynova", List.of("Maths", "English", "Arts"))
        );
    }

    @MethodSource("argumentsStream")
    @ParameterizedTest(name = "Проверка регистрации пользователя с именем: {0}, фамилией: {1} и субъектами: {2}")
    void test4(String name, String lastname, List<String> subjects) {
        String fullName = String.format("%s %s", name, lastname);
        String phone = "1111111111";
        String gender = "Other";
        String day = "20";
        String month = "July";
        String year = "2000";
        String date = String.format("%s %s,%s", day, month, year);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastname)
                .setGender(gender)
                .setBirthDate(getDay(), getMonth(), getYear())
                .setUserPhone(phone)
                .setSubject(subjects)
                .submitClick();

        checkForm(fullName, gender, phone, date, subjects);
    }


    private void checkForm(String fullName, String gender, String phone, String date, List<String> subjects) {
        StringBuilder allSubjects = new StringBuilder();
        for (int i = 0; i < subjects.size(); i++) {
            allSubjects.append(subjects.get(i));
            if (i < subjects.size() - 1) {
                allSubjects.append(", ");
            }
        }
        checkForm(fullName, gender, phone, date).checkResult("Subjects", allSubjects.toString());
    }

    private RegistrationFormPage checkForm(String fullName, String gender, String phone, String date) {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage
                .checkResult("Student Name", fullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", date);
        return registrationFormPage;
    }
}
