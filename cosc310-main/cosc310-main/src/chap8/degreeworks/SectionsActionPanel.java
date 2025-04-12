package chap8.degreeworks;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chap8.courses.Course;
import chap8.courses.Section;
import chap8.courses.Term;
import chap8.users.User;

public class SectionsActionPanel extends ActionPanel {
    // Declare your GUI Components here (JTextFields and your JList and DefaultListModel) as protected so they can be accessed by the loadSection method
    // See CoursesActionPanel for an example

    // Data reference to the current section ... also keep in mind you are inheriting allsections here and can access it directly in your listeners when it is time to add/update/delete a section
    // But unlike the other ActionPanels, you will also need a reference to the current section so you can populate the textfields and JList with the data from the section AND enroll/unenroll students
    protected Section currentSection;

    public SectionsActionPanel(Main main, ArrayList<User> allusers, ArrayList<Course> allcourses, ArrayList<Section> allsections, ArrayList<Term> allterms) {
        super(main, allusers, allcourses, allsections, allterms);
    }

    // lots of work to do here as you need to not only populate the textfields with data for the loaded section but ALSO populate the JList  ... see CourseActionPanel for an example with the textfields and...
    // SEE CatalogPanel for an example of how to populate the JList
    public void loadSection(Section s) {
        currentSection = s;
        // populate the textfields with the data from the Section object

        // populate the JList with the classlist from the Section object
    }

        @Override
    protected String getTitle() {
        return "SECTIONS ACTION PANEL";
    }

    @Override
    protected JLabel[] getLabels() {
        // CREATE YOUR LABEL OBJECTS INSIDE THE ARRAY INITIALIZER BELOW (SEE EXAMPLES IN CoursesActionPanel and TermsActionPanel)
        return new JLabel[] {};
    }

    @Override
    protected JTextField[] getTextFields() {
        // CREATE YOUR JTextField OBJECTS BEFORE THE ARRAY INITIALIZER BELOW (SEE EXAMPLES IN CoursesActionPanel and TermsActionPanel)
        // Then simply list the references to the objects in the array initializer
        return new JTextField[] {};
    }

    @Override
    protected JButton[] getButtons() {
        // This will be a VERY long function as you are not only inititializing all the buttons, you are creating the listeners with code to handle the button clicks
        // CREATE YOUR JButton OBJECTS BEFORE THE ARRAY INITIALIZER BELOW (SEE EXAMPLES IN CoursesActionPanel and TermsActionPanel)
        // Then simply list the references to the buttons in the array initializer
        return new JButton[] {};
    }
    
    @Override
    public JPanel setupGUI() {
        JPanel ourSpecializedSectionPanel = new JPanel();
        JPanel theNormalActionPanel = super.setupGUI(); // this will setup all the JLabels and JTextFields for you
        JPanel theRosterPanel = new JPanel();
        // add all the new GUI components to theRosterPanel
        // including the two new buttons AND their listeners
                
        // << PUT A LOT OF CODE >>

        //
        ourSpecializedSectionPanel.add(theNormalActionPanel, theRosterPanel);
        return ourSpecializedSectionPanel;
    }

}
