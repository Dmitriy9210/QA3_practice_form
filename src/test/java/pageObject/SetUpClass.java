package pageObject;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import pageObject.component.Hobbies;

import static pageObject.component.Hobbies.Music;

public class SetUpClass {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.holdBrowserOpen = true;
    }


    Faker faker = new Faker();


    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String fullName = String.format("%s %s", firstName, lastName);
    private String email = faker.internet().emailAddress();
    private String phone = "1111111111";
    private String day = "20";
    private String month = "July";
    private String year = "2000";
    private String date = String.format("%s %s,%s", day, month, year);
    private String address = faker.address().fullAddress();
    private String gender = "Male";
    private String subjects = "Math";
    private Hobbies hobbies = Music;
    private String imgPath = "dog.jpeg";
    private String state = "NCR";
    private String city = "Delhi";
    private String stateCity = String.format("%s %s", state, city);

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getSubjects() {
        return subjects;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStateCity() {
        return stateCity;
    }
}
