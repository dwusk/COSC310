package chap8.courses;

public class Course {
    protected String department; // e.g., COSC
    protected String number; // e.g., 310
    protected String title; // e.g., Advanced Progamming Concepts
    protected int credits; // e.g., 4

    public Course(String department, String number, String title, int credits) {
        this.department = department;
        this.number = number;
        this.title = title;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return department + " " + number + " - " + title + " (" + credits + " credits)";
    }

    public void setDepartment(String text) {
        this.department = text;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}