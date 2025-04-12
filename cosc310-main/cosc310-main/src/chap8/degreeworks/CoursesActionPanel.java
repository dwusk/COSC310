package chap8.degreeworks;

import java.util.ArrayList;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chap8.courses.Term;
import chap8.courses.Course;
import chap8.courses.Section;
import chap8.users.User;

public class CoursesActionPanel extends ActionPanel {
    protected JTextField deptField;
    protected JTextField numField;
    protected JTextField titleField;
    protected JTextField creditsField;
    
    public CoursesActionPanel(Main main, ArrayList<User> allusers, ArrayList<Course> allcourses, ArrayList<Section> allsections, ArrayList<Term> allterms) {
        super(main, allusers, allcourses, allsections, allterms);
    }

    public void loadCourse(Course c) {
        addButton.setVisible(true);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);
        updateButton.setVisible(true);
        deleteButton.setVisible(true);
        this.deptField.setText(c.getDepartment());
        this.numField.setText(c.getNumber());
        this.titleField.setText(c.getTitle());
        this.creditsField.setText(Integer.toString(c.getCredits()));
    }

    @Override
    protected String getTitle() {
        return "COURSES ACTION PANEL";
    }

    @Override
    protected JLabel[] getLabels() {
        return new JLabel[] {
            new JLabel("Department: "),
            new JLabel("Number: "),
            new JLabel("Title: "),
            new JLabel("Credits: ")
        };
    }

    @Override
    protected JTextField[] getTextFields() {
        return new JTextField[] {
            deptField = new JTextField(15),
            numField = new JTextField(15),
            titleField = new JTextField(15),
            creditsField = new JTextField(15)
        };
    }

    @Override
    protected JButton[] getButtons() {
        addButton = new JButton("Add New Course");
        submitButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        updateButton = new JButton("Update Selected Course");        
        deleteButton = new JButton("Delete Selected Course");
        deleteButton.setForeground(Color.RED); // Sets text color to red
        updateButton.setVisible(false);
        deleteButton.setVisible(false);

        // now add all the listeners for the buttons
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptField.setText("");
                numField.setText("");
                titleField.setText("");
                creditsField.setText("");
                addButton.setVisible(false);
                updateButton.setVisible(false);
                deleteButton.setVisible(false);
                submitButton.setVisible(true);
                cancelButton.setVisible(true);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String dept = deptField.getText();
                String num = numField.getText();
                String title = titleField.getText();
                String credits = creditsField.getText();
                Course c = new Course(dept, num, title, Integer.parseInt(credits));
                allcourses.add(c);
                main.catalogPanel.setSelectedCourse(c);
                main.catalogPanel.updateAllLists();
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course c = main.catalogPanel.getSelectedCourse();
                if (c != null) {
                    loadCourse(main.catalogPanel.getSelectedCourse());
                } else if (allcourses.size() > 0) {
                    main.catalogPanel.setSelectedCourse(allcourses.get(0));
                    loadCourse(allcourses.get(0));
                } else {
                    addButton.setVisible(true);
                    submitButton.setVisible(false);
                    cancelButton.setVisible(false);
                    updateButton.setVisible(false);
                    deleteButton.setVisible(false);
                }
            }
        });
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course c = main.catalogPanel.getSelectedCourse();
                c.setDepartment(deptField.getText());
                c.setNumber(numField.getText());
                c.setTitle(titleField.getText());
                c.setCredits(Integer.parseInt(creditsField.getText()));
                main.catalogPanel.updateAllLists();
                loadCourse(c);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course c = main.catalogPanel.getSelectedCourse();
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Course? " + c, "Confirmation", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    allcourses.remove(c);
                    main.catalogPanel.updateAllLists();
                    deptField.setText("");
                    numField.setText("");
                    titleField.setText("");
                    creditsField.setText("");
                    addButton.setVisible(false);
                    submitButton.setVisible(true);
                    cancelButton.setVisible(true);
                    updateButton.setVisible(false);
                    deleteButton.setVisible(false);
                }
            }
        });
        return new JButton[] {addButton, submitButton, cancelButton, updateButton, deleteButton};
    }


}
