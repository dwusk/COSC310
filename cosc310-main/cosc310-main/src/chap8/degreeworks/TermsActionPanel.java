package chap8.degreeworks;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;

import chap8.courses.Course;
import chap8.courses.Section;
import chap8.courses.Term;
import chap8.users.User;

public class TermsActionPanel extends ActionPanel {

    // GUI components we need to access at various times
    protected JTextField nameField;
    protected JTextField yearField;
    public TermsActionPanel(Main main, ArrayList<User> allusers, ArrayList<Course> allcourses, ArrayList<Section> allsections, ArrayList<Term> allterms) {
        super(main, allusers, allcourses, allsections, allterms);
    }
    
    @Override
    protected JButton[] getButtons() {
        addButton = new JButton("Add New Term");
        submitButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        updateButton = new JButton("Update Selected Term");        
        deleteButton = new JButton("Delete Selected Term");
        deleteButton.setForeground(Color.RED); // Sets text color to red
        updateButton.setVisible(false);
        deleteButton.setVisible(false);

        // now add all the listeners for the buttons
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField.setText("");
                yearField.setText("");
                addButton.setVisible(false);
                updateButton.setVisible(false);
                deleteButton.setVisible(false);
                submitButton.setVisible(true);
                cancelButton.setVisible(true);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = nameField.getText();
                String year = yearField.getText();
                Term t = new Term(name, year);
                allterms.add(t);
                main.catalogPanel.setSelectedTerm(t);
                main.catalogPanel.updateAllLists();
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Term t = main.catalogPanel.getSelectedTerm();
                if (t != null) {
                    loadTerm(main.catalogPanel.getSelectedTerm());
                } else if (allterms.size() > 0) {
                    main.catalogPanel.setSelectedTerm(allterms.get(0));
                    loadTerm(allterms.get(0));
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
                Term t = main.catalogPanel.getSelectedTerm();
                t.setName(nameField.getText());
                t.setYear(yearField.getText());
                main.catalogPanel.updateAllLists();
                loadTerm(t);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Term t = main.catalogPanel.getSelectedTerm();
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this term?" + t, "Confirmation", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    allterms.remove(t);
                    main.catalogPanel.updateAllLists();
                    nameField.setText("");
                    yearField.setText("");
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

    public void loadTerm(Term t) {
        addButton.setVisible(true);
        submitButton.setVisible(false);
        cancelButton.setVisible(false);
        updateButton.setVisible(true);
        deleteButton.setVisible(true);
        this.nameField.setText(t.getName());
        this.yearField.setText(t.getYear());
    }

    @Override
    protected String getTitle() {
        return "TERMS ACTION PANEL";
    }

    @Override
    protected JLabel[] getLabels() {
        return new JLabel[] {
            new JLabel("Name:"),
            new JLabel("Year")
        };
    }

    @Override
    protected JTextField[] getTextFields() {
        nameField = new JTextField(15);
        yearField = new JTextField(15);
        return new JTextField[] {nameField, yearField};
    }

}
