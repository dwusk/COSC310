package chap8.courses;

import java.util.ArrayList;
import chap8.users.Faculty;
import chap8.users.User;

public class Section {
    Term term;
    String number;
    String schedule;
    String room;
    Course course;
    Faculty instructor;
    ArrayList<User> students;

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getInstructor() {
        return instructor;
    }

    public void setInstructor(Faculty instructor) {
        this.instructor = instructor;
    }

    public ArrayList<User> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }
    
    public Section(Term term, String number, String schedule, String room, Course course, Faculty instructor, ArrayList<User> students) {
        this.term = term;
        this.number = number;
        this.schedule = schedule;
        this.room = room;
        this.course = course;
        this.instructor = instructor;
        this.students = students;
    }

    @Override
    public String toString() {
        return course.number + "-" + number + ": " + schedule + ", " + room + ", " + instructor.getName() + ", " + students.size() + " student(s)";
    }

    public void enrollStudent(User u) {
        students.add(u);
    }

    public void unenrollStudent(User u) {
        students.remove(u);
    }
}