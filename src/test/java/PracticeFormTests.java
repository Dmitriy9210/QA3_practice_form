import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    public enum Hobbies {
        Sports(1),
        Reading(2),
        Music(3);

        int hobbies;

        Hobbies(int i) {
            this.hobbies = i;
        }

        public int getHobbies() {
            return hobbies;
        }
    }

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://demoqa.com/";
//        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void firstTest() {
        String name = "NameTest";
        String lastName = "SurnameTest";
        String email = "test@mail.ru";
        String phone = "9988898898";
        String mounth = "May";
        String year = "2000";
        String address = "AddressTest";
        String gender = "Male";
        String subjects = "testSubjects";
        int hobbies = Hobbies.Music.getHobbies();
        String imgPath = "dog.jpeg";

        open("automation-practice-form");
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#uploadPicture").uploadFromClasspath(imgPath);


        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(mounth);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--001").click();
        $("[for=hobbies-checkbox-" + hobbies + "]").click();
        $("#currentAddress").setValue(address);
        $("#subjectsInput").setValue(subjects).pressEnter();

        $(".table-dark").shouldHave(
                text(name + " " + lastName),
                text(email),
                text(phone),
                text(address),
                text(gender),
                text(imgPath),
                text(Hobbies.Music.name())
        );
    }
}
