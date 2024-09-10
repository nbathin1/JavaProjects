package application;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginInterface {
    private Connection conn;
    private Scanner scanner;
    
    public LoginInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/application";
            this.conn = DriverManager.getConnection(dbUrl, "root", "Got@1234");
            String user_name;
            String pwd;
            String choice; 

            while (true) {
                System.out.println("1. User Login.....");
                System.out.println("2. Create new User.....");
                System.out.println("3. Quit.....");

                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Enter User_name....");
                        user_name = scanner.nextLine();

                        System.out.println("Enter Password....");
                        pwd = scanner.nextLine();

                        String query = "SELECT * FROM users WHERE user_name = ? AND password = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, user_name);
                        pstmt.setString(2, pwd);

                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            int userId = rs.getInt("user_id");
                            System.out.println("Login successful! Welcome, " + user_name);
                            UserInterface ui = new UserInterface(userId, conn,scanner);
                            ui.start();
                        } else {
                            System.out.println("Login failed! Invalid username or password.");
                        }
                        break;

                    case "2":
                        System.out.println("Enter new User_name...");
                        user_name = scanner.nextLine();

                        System.out.println("Set new Password....");
                        pwd = scanner.nextLine();

                        String query2 = "INSERT INTO users (user_name, password) VALUES (?, ?)";
                        PreparedStatement pstmt2 = conn.prepareStatement(query2);
                        pstmt2.setString(1, user_name);
                        pstmt2.setString(2, pwd);

                        pstmt2.executeUpdate();
                        System.out.println("New user created.");
                        break;

                    case "3":
                        System.out.println(">>>>Exiting<<<<");
                        return; // Exits the while loop and the method

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

    }
}