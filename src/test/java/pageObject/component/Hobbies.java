package pageObject.component;

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
