package ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HelloDB {

    static String DB_URL = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.uzfqtmeirzyioxrfjniw&password=Hjxbc4hiHc8u9Jjq";
    static String QUERY = "select * from websites";

    public static void main(String[] args) {
        Scanner keyin = new Scanner(System.in);
        System.out.print("Username: ");
        String username = keyin.nextLine();
        System.out.print("Password: ");
        String password = keyin.nextLine();
        String loginQuery = "SELECT * FROM users LEFT JOIN roles ON role_id=roles.id WHERE username=? AND password=crypt(?, password)";
        // String roleQuery = "SELECT * FROM roles WHERE id = ?";

        // Open a connection
        try {            
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement(loginQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                // Retrieve by column name
                String teamname = rs.getString("name");
                System.out.println("Welcome back " + username + ". Glad to you have part of our " + teamname + " team.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }


}
