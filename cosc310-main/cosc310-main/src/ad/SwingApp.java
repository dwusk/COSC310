package ad;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SwingApp {
   // JDBC connection settings
    static String DB_URL = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.uzfqtmeirzyioxrfjniw&password=Hjxbc4hiHc8u9Jjq";
    static Connection conn;
    static int businesscustomer_id;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        SwingUtilities.invokeLater(() -> createAndShowLoginFrame());
    }

    private static void createAndShowLoginFrame() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        // Create login panel with GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField("wendys");
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField("test1111");
        JButton btnLogin = new JButton("Login");

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Empty cell for layout spacing
        panel.add(btnLogin);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String role = authenticateUser(username, password);

            if (role != null) {
                // If authentication succeeds, dispose of the login frame and show the appropriate dashboard
                frame.dispose();
                showDashboard(role);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // This method connects to the database, executes the query, and returns the user's role
    private static String authenticateUser(String username, String password) {
        String role = null;
        // SQL query joining the users and roles tables
        String query = "SELECT r.name, u.businesscustomer_id " +
                       "FROM users u JOIN roles r ON u.role_id = r.id " +
                       "WHERE u.username = ? AND u.password = crypt(?, u.password)";
        try {
            ResultSet rs = SQLHelper.executeQuery(conn, query, username, password);
            if (rs.next()) {
                role = rs.getString("name");
                businesscustomer_id = rs.getInt("businesscustomer_id");
            }
       } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return role;
    }

    // This method displays a new dashboard frame based on the user's role.
    private static void showDashboard(String role) {
        JFrame dashboardFrame = new JFrame("Dashboard - " + role);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setLocationRelativeTo(null);

        JPanel dashboardPanel;
        // Show different dashboards based on role
        if ("ADMIN".equalsIgnoreCase(role)) {
            dashboardPanel = new AdminDashboardPanel();
        } else if ("SALES".equalsIgnoreCase(role)) {
            dashboardPanel = new SalesDashboardPanel();
        } else if ("BUSINESSCUSTOMER".equalsIgnoreCase(role)) {
            dashboardPanel = new BusinessCustomerDashboardPanel();
        } else {
            throw new Error("who are you and why are you using our system???");
        }
        dashboardFrame.getContentPane().add(dashboardPanel);
        dashboardFrame.setVisible(true);
    }

 
}

class AdsPanel extends JPanel {
    private CardLayout adsLayout;
    private JPanel adsContent;

    public AdsPanel() {
        setLayout(new BorderLayout());

        // Top buttons to toggle between current and old ads
        JPanel topNav = new JPanel(new FlowLayout());
        JButton btnCurrent = new JButton("Current Ads");
        JButton btnOld = new JButton("Old Ads");

        topNav.add(btnCurrent);
        topNav.add(btnOld);
        add(topNav, BorderLayout.NORTH);

        // Content area with CardLayout
        adsLayout = new CardLayout();
        adsContent = new JPanel(adsLayout);

        JTextArea currentAdsArea = new JTextArea("Loading...", 10, 50);
        JTextArea oldAdsArea = new JTextArea("Old ads go here...", 10, 50);
        ResultSet rs = SQLHelper.executeQuery(SwingApp.conn, "select ads.* from ads right join adcampaigns on ad_id = ads.id where businesscustomer_id = ? and date_start is not null and now() > date_start and (date_stop is null or now() < date_stop);", SwingApp.businesscustomer_id);
        currentAdsArea.setText("");
        try {
            while (rs.next()) {
                currentAdsArea.append(rs.getString("title") + "\n");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        adsContent.add(new JScrollPane(currentAdsArea), "Current");
        adsContent.add(new JScrollPane(oldAdsArea), "Old");

        add(adsContent, BorderLayout.CENTER);

        btnCurrent.addActionListener(e -> adsLayout.show(adsContent, "Current"));
        btnOld.addActionListener(e -> adsLayout.show(adsContent, "Old"));
    }
}

class BillingPanel extends JPanel {
    private CardLayout billingLayout;
    private JPanel billingContent;

    public BillingPanel() {
        setLayout(new BorderLayout());

        JPanel topNav = new JPanel(new FlowLayout());
        JButton btnInvoices = new JButton("Invoices");
        JButton btnHistory = new JButton("History");

        topNav.add(btnInvoices);
        topNav.add(btnHistory);
        add(topNav, BorderLayout.NORTH);

        billingLayout = new CardLayout();
        billingContent = new JPanel(billingLayout);

        JTextArea invoiceArea = new JTextArea("Invoice data here...", 10, 50);
        JTextArea historyArea = new JTextArea("Billing history here...", 10, 50);

        billingContent.add(new JScrollPane(invoiceArea), "Invoices");
        billingContent.add(new JScrollPane(historyArea), "History");

        add(billingContent, BorderLayout.CENTER);

        btnInvoices.addActionListener(e -> billingLayout.show(billingContent, "Invoices"));
        btnHistory.addActionListener(e -> billingLayout.show(billingContent, "History"));
    }
}

class BusinessCustomerDashboardPanel extends JPanel {
    private CardLayout sectionLayout;
    private JPanel sectionPanel;

    public BusinessCustomerDashboardPanel() {
        setLayout(new BorderLayout());
        JPanel topArea = new JPanel(new GridLayout(2,1));
        topArea.add(new JLabel("Welcome, Business Customer!", SwingConstants.CENTER));

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout());
        JButton btnAds = new JButton("Ads");
        JButton btnBilling = new JButton("Billing");
        JButton btnLogout = new JButton("Logout");

        navPanel.add(btnAds);
        navPanel.add(btnBilling);
        navPanel.add(btnLogout);
        topArea.add(navPanel);
        add(topArea, BorderLayout.NORTH);

        // Section content switching
        sectionLayout = new CardLayout();
        sectionPanel = new JPanel(sectionLayout);

        sectionPanel.add(new AdsPanel(), "Ads");
        sectionPanel.add(new BillingPanel(), "Billing");

        add(sectionPanel, BorderLayout.CENTER);

        btnAds.addActionListener(e -> sectionLayout.show(sectionPanel, "Ads"));
        btnBilling.addActionListener(e -> sectionLayout.show(sectionPanel, "Billing"));
        btnLogout.addActionListener(e -> System.exit(0)); // Placeholder logout
    }
}

// Dashboard panel for ADMIN role
class AdminDashboardPanel extends JPanel {
    public AdminDashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome, Admin!"), BorderLayout.NORTH);
        // Additional admin-specific components can be added here
    }
}

// Dashboard panel for USER role
class SalesDashboardPanel extends JPanel {
    public SalesDashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Welcome, Sales Person!"), BorderLayout.NORTH);
        // Additional user-specific components can be added here
    }
}

class SQLHelper {
    static public ResultSet executeQuery(Connection conn, String query, Object... params) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

