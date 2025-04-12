package chap8.degreeworks;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chap8.courses.Course;
import chap8.courses.Section;
import chap8.courses.Term;
import chap8.users.Faculty;
import chap8.users.Student;
import chap8.users.User;

public class Main {

    // our data lives here
    protected ArrayList<User> allusers = new ArrayList<>();
    protected ArrayList<Course> allcourses = new ArrayList<>();
    protected ArrayList<Section> allsections = new ArrayList<>();
    protected ArrayList<Term> allterms = new ArrayList<>();
    protected User currentuser; // this is the user who logged in

    // our main UI components in the top area
    protected CoursesActionPanel coursesPanel; 
    protected SectionsActionPanel sectionsPanel; 
    protected TermsActionPanel termsPanel;
    protected HelpActionPanel helpPanel;
    protected CatalogPanel catalogPanel;

    // our UI callback methods to handle item selections in the various lists
    public void handleHelpClick() {
        // update visibility of all four panels
        helpPanel.setVisible(true);
        coursesPanel.setVisible(false);
        sectionsPanel.setVisible(false);
        termsPanel.setVisible(false);
    }

    public void handleCourseClick(Course c) {
        coursesPanel.loadCourse(c);
        
        // update visibility of all four panels
        helpPanel.setVisible(false);
        coursesPanel.setVisible(true);
        sectionsPanel.setVisible(false);
        termsPanel.setVisible(false);
    }

    public void handleSectionClick(Section s) {
        sectionsPanel.loadSection(s);

        // update visibility of all four panels
        helpPanel.setVisible(false);
        coursesPanel.setVisible(false);
        sectionsPanel.setVisible(true);
        termsPanel.setVisible(false);
    }

    public void handleTermClick(Term t) {
        termsPanel.loadTerm(t);

        // update visibility of all four panels
        helpPanel.setVisible(false);
        coursesPanel.setVisible(false);
        sectionsPanel.setVisible(false);
        termsPanel.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        loadUsers(); // populates users from a file exported from our banner program
        populateData(); // populates the courses, sections, and terms
        currentuser = showLogin();

        // the ONLY way to make it to this line of code is if they successfully logged in!

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        System.out.println("exiting main thread, swing GUI thread is now handling the program");
    }

    private User showLogin() {
        while (true) {
            JLoginPane loginWindow = new JLoginPane(); // shows a login popup dialog
            String username = loginWindow.username.getText(); 
            String password = loginWindow.password.getText(); 
            for (User user : allusers) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            JOptionPane.showMessageDialog(null, "Incorrect username or password, keep guessing!", "Uh-oh!", JOptionPane.ERROR_MESSAGE);
        }        
    }

    protected void createAndShowGUI() {
        // The overall design is again TWO panels ... but the top one will actually have FOUR panels with THREE always hidden and only one shown at a time
        // The bottom panel will have a list of courses, sections, and terms
        JFrame frame = new JFrame("DegreeWorks");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the panels
        // We need a wrapper panel in the NORTH where all four of our real top panels will live
        JPanel topPanel = new JPanel();
        helpPanel = new HelpActionPanel(this, allusers, allcourses, allsections, allterms);
        coursesPanel = new CoursesActionPanel(this, allusers, allcourses, allsections, allterms);
        sectionsPanel = new SectionsActionPanel(this, allusers, allcourses, allsections, allterms);
        termsPanel = new TermsActionPanel(this, allusers, allcourses, allsections, allterms);
        // add all four panels to the topPanel, but only one will ever be visible at a time
        topPanel.add(helpPanel);
        topPanel.add(coursesPanel);
        topPanel.add(sectionsPanel);
        topPanel.add(termsPanel);
        // make sure ONLY the help panel is visible initially ... hide the other three panels
        helpPanel.setVisible(true);
        coursesPanel.setVisible(false);
        sectionsPanel.setVisible(false);
        termsPanel.setVisible(false);

        catalogPanel = new CatalogPanel(this, allusers, allcourses, allsections, allterms);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(catalogPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // reads from our current user data dump
    private void loadUsers() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fi = new FileInputStream(chooser.getSelectedFile());
                ObjectInputStream si = new ObjectInputStream(fi);
                ArrayList<User> tmpusers = (ArrayList<User>) si.readObject();
                allusers.clear();
                for (User u : tmpusers) {
                    allusers.add(u);
                }
                si.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected ArrayList<User> getSeveralRandomStudents(ArrayList<User> allStudents, int count) {
        Random random = new Random();
        ArrayList<User> selectedStudents = new ArrayList<>();
        HashSet<Integer> selectedIndexes = new HashSet<>();

        while (selectedIndexes.size() < count) {
            int index = random.nextInt(allStudents.size());
            if (!selectedIndexes.contains(index)) {
                selectedIndexes.add(index);
                selectedStudents.add(allStudents.get(index));
            }
        }
        return selectedStudents;
    }

    protected void populateData() {
        ArrayList<Faculty> facultyMembers = User.getFaculty(allusers);
        ArrayList<Student> allStudents = User.getStudents(allusers);
        ArrayList<User> studentUsers = new ArrayList<>();
        for (Student s : allStudents) {
            studentUsers.add(s);
        }

        ArrayList<Term> terms = new ArrayList<>();
        String[] termNames = { "Spring", "Summer", "Fall", "Winter" };
        for (int year = 2024; year <= 2026; year++) {
            for (String termName : termNames) {
                terms.add(new Term(termName, String.valueOf(year)));
            }
        }

        // Generate courses (20 courses)
        ArrayList<Course> courses = new ArrayList<>(Arrays.asList(
                new Course("COSC", "101", "Introduction to Computer Science", 3),
                new Course("COSC", "210", "Data Structures", 4),
                new Course("COSC", "310", "Advanced Programming Concepts", 4),
                new Course("MATH", "120", "Calculus I", 4),
                new Course("MATH", "220", "Discrete Mathematics", 3),
                new Course("MATH", "320", "Linear Algebra", 3),
                new Course("PHYS", "101", "General Physics I", 4),
                new Course("PHYS", "102", "General Physics II", 4),
                new Course("ENGR", "150", "Engineering Design", 3),
                new Course("ENGR", "250", "Thermodynamics", 3),
                new Course("CHEM", "101", "General Chemistry", 4),
                new Course("BIO", "110", "Cell Biology", 3),
                new Course("ECON", "200", "Principles of Economics", 3),
                new Course("HIST", "101", "World History", 3),
                new Course("PSYC", "101", "Introduction to Psychology", 3),
                new Course("SOC", "205", "Sociology of Organizations", 3),
                new Course("PHIL", "210", "Ethics in Technology", 3),
                new Course("STAT", "350", "Probability and Statistics", 3),
                new Course("ART", "101", "Art History", 3),
                new Course("MUS", "105", "Music Theory", 3)));

        // Add terms and courses to the global lists
        allterms.addAll(terms);
        allcourses.addAll(courses);

        // Generate multiple sections (50 sections)
        Random random = new Random();
        String[] schedules = { "MWF 9AM", "TR 11AM", "MW 2PM", "TR 3PM", "MWF 1PM", "TR 4PM" };
        String[] rooms = { "RUSS325", "SMITH200", "ENGR105", "MATH220", "SCIENCE301", "CHEM102" };
        String[] numbers = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

        for (int i = 0; i < 50; i++) {
            Term term = terms.get(random.nextInt(terms.size()));
            Course course = courses.get(random.nextInt(courses.size()));
            Faculty instructor = facultyMembers.get(random.nextInt(facultyMembers.size()));
            ArrayList<User> enrolledStudents = getSeveralRandomStudents(studentUsers, 10 + random.nextInt(6)); // 10-15
                                                                                                                 // students
            Section section = new Section(
                    term,
                    numbers[random.nextInt(numbers.length)],
                    schedules[random.nextInt(schedules.length)],
                    rooms[random.nextInt(rooms.length)],
                    course,
                    instructor,
                    enrolledStudents);
            allsections.add(section);
        }

        // Print sections for verification
        for (Section s : allsections) {
            System.out.println(s);
            System.out.println("-----------------------------------------");
        }
    }

}
