package chap8.degreeworks;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLoginPane {

    protected JTextField username = new JTextField("jhill");
    protected JTextField password = new JTextField("jacksonpass001");

    public JLoginPane() {
      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Username:"));
      myPanel.add(username);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Password:"));
      myPanel.add(password);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Login to DegreeWorks (no Duo required!!!)", JOptionPane.OK_CANCEL_OPTION);

      if (result == JOptionPane.OK_OPTION) {
         System.out.println("x value: " + username.getText());
         System.out.println("y value: " + password.getText());
      }
    }
    
}
