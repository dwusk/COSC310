package chap8.degreeworks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chap8.courses.Course;
import chap8.courses.Section;
import chap8.courses.Term;
import chap8.users.User;

public abstract class ActionPanel extends JPanel {

    // references to the global data ... NOTE: since this is in the base class we don't need to declare and init these in the derived classes!
    protected ArrayList<User> allusers;
    protected ArrayList<Course> allcourses;
    protected ArrayList<Section> allsections;
    protected ArrayList<Term> allterms;

    // rather than passing a reference to each of the panels with the associated lists so that we can notify them when something happens, we can instead pass a reference to Main which has a reference to all of our "ActionPanel"s
    // and can call the appropriate method on the appropriate panel (or all the panels for each of implementation)
    protected Main main;

    // GUI components common to (almost) all actionpanels
    protected JButton addButton;
    protected JButton submitButton;
    protected JButton cancelButton;
    protected JButton updateButton;        
    protected JButton deleteButton;

    public ActionPanel(Main main, ArrayList<User> allusers, ArrayList<Course> allcourses, ArrayList<Section> allsections, ArrayList<Term> allterms) {
        this.main = main;
        this.allusers = allusers;
        this.allcourses = allcourses;
        this.allsections = allsections;
        this.allterms = allterms;
        setupPanel(); // setup the top area with the name of the person who has logged in
    }

    // this method is called by the constructor to setup the top area with the name of the person who has logged in
    protected void setupPanel() {
        this.setBorder(BorderFactory.createLineBorder(Color.PINK));
        // setup the common components (title label, login label, and help button)
        // this is the same for all the panels so we can put it here
        this.setLayout(new BorderLayout());
        JPanel topArea = new JPanel();
        JLabel titleLabel = new JLabel("Welcome " + main.currentuser.getName(), JLabel.CENTER);
        JButton helpButton = new JButton("HELP");
        topArea.add(titleLabel);
        topArea.add(helpButton);
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.handleHelpClick();
            }
        });
        topArea.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        this.add(topArea, BorderLayout.NORTH);

        // now call the abstract method to allow the derived class to setup the rest of the GUI ... note that this is an example of polymorphism
        JPanel mainGUI = setupGUI(); 
        mainGUI.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(mainGUI, BorderLayout.CENTER);
    }

    abstract protected String getTitle();
    abstract protected JLabel[] getLabels();
    abstract protected JTextField[] getTextFields();
    abstract protected JButton[] getButtons();

    // since all of our action panels have the same general layout
    // let's setup the GUI here and then call the abstract polymorphic
    // methods to customize the setup where it's needed
    protected JPanel setupGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        northPanel.add(new JLabel(getTitle()), BorderLayout.NORTH);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // loop through all the labels and textfields
        JLabel labels[] = getLabels();
        JTextField textfields[] = getTextFields();

        for (int i=0; i<labels.length; i++) {
            JLabel label = labels[i];
            JTextField textfield = textfields[i];
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.EAST;
            centerPanel.add(label, gbc);
    
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            centerPanel.add(textfield, gbc);    
        }

        // Now let's add a panel for all the actions buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton buttons[] = getButtons();
        for (JButton button: buttons) {
            buttonPanel.add(button);
        }
        

        centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        panel.add(centerPanel, BorderLayout.CENTER);

        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    
}
