package chap8.banner;

import chap8.users.Admin;
import chap8.users.Faculty;
import chap8.users.Staff;
import chap8.users.Student;
import chap8.users.User; 
import java.util.ArrayList;
import javax.swing.*;
import java.awt.BorderLayout;

public class Main {

    // Data attribute
    ArrayList<User> allusers = new ArrayList<>(100);
    public static String[] userTypes = new String[] {"Student","Faculty","Staff","Admin"};

    public Main() {
        populateUsers();
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void main(String[] args) {
        new Main();  
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("User Management");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the panels
        UserListPanel userListPanel = new UserListPanel(allusers);
        UserPanel userPanel = new UserPanel(userListPanel, allusers);
        userListPanel.userPanel = userPanel;

        frame.add(userPanel, BorderLayout.NORTH);
        frame.add(userListPanel, BorderLayout.CENTER);
        userListPanel.updateUserList(allusers);
        frame.setVisible(true);
    }

    public void populateUsers() {
        // Create some users 
        allusers.add(new Faculty("Theo Harmon", "989041908", "tharmon", "somerandompassword"));
        allusers.add(new Staff("Uriah Baldwin", "990030897", "ubaldwin", "someotherpass"));
        allusers.add(new Student("Vanessa Lowe", "901219786", "vlowe", "yetanotherp"));
        allusers.add(new Admin("Admin Smith", "999999999", "asmith", "onemorepass"));
        allusers.add(new Faculty("Linda Wright", "982134567", "lwright", "anotherpassword1"));
        allusers.add(new Staff("Kevin Miller", "984756345", "kmiller", "staffpassword123"));
        allusers.add(new Student("Jackson Turner", "930182745", "jturner", "studentpass456"));
        allusers.add(new Admin("Jane Doe", "981032987", "jdoe", "adminpassword789"));
        allusers.add(new Faculty("Emma Davis", "980238746", "edavis", "facultypass000"));
        allusers.add(new Staff("Sophia Walker", "983765432", "swalker", "staffpassword321"));
        allusers.add(new Student("Benjamin Harris", "921047638", "bharris", "studpass987"));
        allusers.add(new Admin("Michael Johnson", "939123456", "mjohnson", "admin987654"));
        allusers.add(new Faculty("Olivia White", "975310624", "owhite", "whitefaculty123"));
        allusers.add(new Staff("Liam King", "986407312", "lking", "staffpass456"));
        allusers.add(new Student("Charlotte Scott", "920135689", "cscott", "scottpass890"));
        allusers.add(new Admin("Henry Taylor", "977123890", "htaylor", "admintaylor123"));
        allusers.add(new Faculty("Ava Clark", "951623784", "aclark", "faculty12345"));
        allusers.add(new Staff("Zoe Moore", "937516408", "zmoore", "passwordmoore234"));
        allusers.add(new Student("Ethan Adams", "964032578", "eadams", "ethanpassword999"));
        allusers.add(new Admin("Liam Harris", "922034561", "lharris", "adminharris234"));
        allusers.add(new Faculty("Mason Rodriguez", "902153748", "mrodriguez", "facultyrodriguez8"));
        allusers.add(new Staff("Isla Ramirez", "908123976", "iramirez", "staffpassword032"));
        allusers.add(new Student("Mia Robinson", "934658712", "mrobinson", "studentpass001"));
        allusers.add(new Admin("Olivia Brown", "927065832", "obrown", "adminbrown222"));
        allusers.add(new Faculty("James Martinez", "990674521", "jmartinez", "facultypass033"));
        allusers.add(new Staff("Mason Lee", "983415876", "mlee", "masonstaffpass123"));
        allusers.add(new Student("Amelia Nelson", "945263098", "anelson", "amelianelson789"));
        allusers.add(new Admin("Elijah Wilson", "916874302", "ewilson", "adminwilson888"));
        allusers.add(new Faculty("Daniel King", "967893721", "dking", "facultyking777"));
        allusers.add(new Staff("Avery Gonzalez", "950673487", "agonzalez", "averystaffpass987"));
        allusers.add(new Student("Harper Perez", "973648256", "hperez", "harperperez321"));
        allusers.add(new Admin("Lucas Thomas", "992084572", "lthomas", "lucasadmin456"));
        allusers.add(new Faculty("Eleanor Lopez", "980243159", "elopez", "elopezpassword0"));
        allusers.add(new Staff("Lily Scott", "981740286", "lscott", "stafflilypassword01"));
        allusers.add(new Student("Oliver Jackson", "930174506", "ojackson", "oliverjackson2024"));
        allusers.add(new Admin("Nathaniel Young", "932869417", "nyoung", "nathanadmin312"));
        allusers.add(new Faculty("Chloe Cooper", "940573801", "ccooper", "chloepass567"));
        allusers.add(new Staff("Jack Mitchell", "952103687", "jmitchell", "jackstaffpass045"));
        allusers.add(new Student("Ella Carter", "902567319", "ecarter", "ellacarter678"));
        allusers.add(new Admin("Samuel Harris", "960283741", "sharris", "samueladmin444"));
        allusers.add(new Faculty("Jackson Hill", "974863259", "jhill", "jacksonpass001"));
        allusers.add(new Staff("Emily Lewis", "931264807", "elewis", "emilystaffpassword2"));
        allusers.add(new Student("Lucas Wright", "918257634", "lwright", "lucaswright321"));
        allusers.add(new Admin("Scarlett Baker", "906473158", "sbaker", "scarlettadmin555"));
        allusers.add(new Faculty("William Turner", "977982645", "wturner", "williamfaculty333"));
        allusers.add(new Staff("Madeline Evans", "944271609", "mevans", "staffmadeline44"));
        allusers.add(new Student("Zoe Mitchell", "931467520", "zmitchell", "zoemitchell001"));
        allusers.add(new Admin("David Clark", "951803246", "dclark", "davidadmin555"));
        allusers.add(new Faculty("Benjamin Harris", "982416735", "bharris", "benharrispassword"));
        allusers.add(new Staff("Sophie Taylor", "938674120", "staylor", "staffsophie789"));
        allusers.add(new Student("Samuel Perez", "943725861", "sperez", "samuelstudent321"));
        allusers.add(new Admin("Olivia Wright", "913652748", "owright", "oliviaadmin234"));
        allusers.add(new Faculty("Sophia Young", "979465283", "syoung", "sophiafaculty987"));
        allusers.add(new Staff("Noah Williams", "960532478", "nwilliams", "noahstaff111"));
        allusers.add(new Student("Mia Evans", "932614587", "mevans", "miastudent234"));
        allusers.add(new Admin("Abigail Lee", "918736452", "alee", "abigailadmin888"));
        allusers.add(new Faculty("James Rodriguez", "943651728", "jrodriguez", "jamesfacpass1"));
        allusers.add(new Staff("Aidan White", "981347680", "awhite", "staffpasswordaiden"));
        allusers.add(new Student("Lily Davis", "975634812", "ldavis", "lilystudent789"));
        allusers.add(new Admin("Isaac Scott", "963147250", "iscott", "isaacadmin444"));
        allusers.add(new Faculty("Lucas Jackson", "907654218", "ljackson", "lucasfacultypass"));
        allusers.add(new Staff("Sarah Harris", "953276489", "sharris", "staffsarah1234"));
        allusers.add(new Student("Ethan Perez", "982736471", "eperez", "ethanstudent567"));
        allusers.add(new Admin("Joshua King", "912746358", "jking", "joshuaadmin567"));
        allusers.add(new Faculty("Grace Johnson", "966817230", "gjohnson", "gracefaculty345"));
        allusers.add(new Staff("Chloe Green", "961428037", "cgreen", "chloestaffpassword"));
        allusers.add(new Student("Zoe Miller", "934827612", "zmiller", "zoemillstudent"));
        allusers.add(new Admin("Madeline Evans", "924736589", "mevans", "madelineadmin012"));
        allusers.add(new Faculty("Daniel Scott", "917634580", "dscott", "danielpass999"));
        allusers.add(new Staff("Isabel King", "933210786", "iking", "isabelstaff543"));
        allusers.add(new Student("Nathan Wright", "968513724", "nwright", "nathanstudent789"));
        allusers.add(new Admin("Ava Lee", "914352678", "alee", "avaadmin987"));
        allusers.add(new Faculty("Sophia White", "939405128", "swhite", "sophiafacultypassword"));
        allusers.add(new Staff("Owen Moore", "976254837", "omoore", "owenstaff321"));
        allusers.add(new Student("Liam Harris", "945021736", "lharris", "liamharris321"));
        allusers.add(new Admin("Lucas Brown", "925476023", "lbrown", "lucasadmin432"));
        allusers.add(new Faculty("Ella Taylor", "960384127", "etaylor", "ellafaculty234"));
        allusers.add(new Staff("Madeline Carter", "976431235", "mcarter", "madelinecstaff567"));
        allusers.add(new Student("Amelia Martinez", "903764582", "amartinez", "ameliastudent123"));
        allusers.add(new Admin("Jacob Young", "912763984", "jyoung", "jacobadmin789"));
        allusers.add(new Faculty("Maya Gonzalez", "952184763", "mgonzalez", "mayafaculty098"));
        allusers.add(new Staff("Grace Mitchell", "925378640", "gmitchell", "gracestaff011"));
        allusers.add(new Student("Lucas Thomas", "943875216", "lthomas", "lucasstudent234"));
        allusers.add(new Admin("Maya Harris", "967394102", "mharris", "mayaadmin777"));
        allusers.add(new Faculty("Isaac Clark", "981650743", "iclarck", "isaacfaculty003"));
        allusers.add(new Staff("Nina White", "903672814", "nwhite", "ninastaffpass777"));
        allusers.add(new Student("Julian Scott", "967512398", "jscott", "julianscott123"));
        allusers.add(new Admin("David King", "942086157", "dking", "davidadmin234"));
        allusers.add(new Faculty("Michael Wright", "954736208", "mwright", "michaelpassword987"));
        allusers.add(new Staff("Ella Taylor", "924576103", "etaylor", "ellastaffpass567"));
        allusers.add(new Student("Daniel Adams", "936124580", "dadams", "danieladams321"));
        allusers.add(new Admin("Sophie Evans", "978452169", "sevans", "sophieadmin234"));
        allusers.add(new Faculty("Isabelle Moore", "912376584", "imoore", "isabellefaculty002"));
        allusers.add(new Staff("Benjamin Lee", "946372810", "blee", "benstaffpassword"));
        allusers.add(new Student("Chloe Wright", "974620813", "cwright", "chloewright123"));
        allusers.add(new Admin("Matthew Harris", "909873654", "mharris", "matthewadmin654"));
        allusers.add(new Faculty("Zoe Johnson", "976389752", "zjohnson", "zoefaculty987"));
        allusers.add(new Staff("Andrew Scott", "983521746", "ascott", "andrewstaffpass001"));
        allusers.add(new Student("Sophia Adams", "932184675", "sadams", "sophiaadams234"));
        allusers.add(new Admin("Oliver Lee", "951672839", "olee", "oliveradmin456"));
        allusers.add(new Faculty("Nathaniel White", "916480532", "nwhite", "nathanielfaculty"));
        allusers.add(new Staff("Megan Harris", "929867410", "mharris", "meganstaff789"));
        allusers.add(new Student("Zoe Carter", "977803255", "zcarter", "zoecarter456"));
        allusers.add(new Admin("Eli Taylor", "981273684", "etaylor", "eliadmin789"));
        allusers.add(new Faculty("Jack Lee", "935620489", "jlee", "jackfaculty098"));
        allusers.add(new Staff("Ava Moore", "950182673", "amoore", "avastaffpass"));
        allusers.add(new Student("Oliver Harris", "971320584", "oharris", "oliverpass001"));
        allusers.add(new Admin("Chloe Taylor", "915728463", "ctaylor", "chloeadmin456"));
        allusers.add(new Faculty("Lily Robinson", "952736108", "lrobinson", "lilyfaculty234"));
        allusers.add(new Staff("Henry Scott", "961283759", "hscott", "henrystaff678"));
        allusers.add(new Student("Abigail White", "937162580", "awhite", "abigailpass123"));
        allusers.add(new Admin("Leo Thomas", "944736159", "lthomas", "leoadmin234"));
    }

}
