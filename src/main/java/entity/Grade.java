package entity;


public class Grade {
    // private int id;
    private int gender;
    private int grade;

    @Override
    public String toString() {
        return "Grade{" +
                "gender=" + gender +
                ", grade=" + grade +
                '}';
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Grade() {
    }

    public Grade(int gender, int grade) {

        this.gender = gender;
        this.grade = grade;
    }
}
