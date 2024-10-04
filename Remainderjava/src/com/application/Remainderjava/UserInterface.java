package com.application.Remainderjava;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;

public class UserInterface extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton createUserButton;
    private JButton loginButton;
    private JButton quitButton;
    private UserDao userdao;

    public void start() {

        setTitle("User Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        userdao = new UserDao();


        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 100, 150, 30);
        add(passwordField);


        createUserButton = new JButton("Create New User");
        createUserButton.setBounds(50, 150, 150, 30);
        add(createUserButton);


        loginButton = new JButton("Login");
        loginButton.setBounds(220, 150, 80, 30);
        add(loginButton);

 
        quitButton = new JButton("Quit");
        quitButton.setBounds(150, 200, 100, 30);
        add(quitButton);

        setVisible(true);

       
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewUser();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DbConnection db = DbConnection.instance();
                try {
                    db.close(); // Close the database connection
                    System.out.println("Database connection closed successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Handle any SQL exceptions
                } finally {
                    System.exit(0); // Exit the application
                }
            }
        });
    }


    private void createNewUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.");
            return;
        }

        User newUser = new User();
        newUser.setUser_name(username);
        newUser.setPassword(password);

        userdao.save(newUser);
        JOptionPane.showMessageDialog(this, "User created successfully!");

        usernameField.setText("");
        passwordField.setText("");
    }

    
    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username and password.");
            return;
        }

        User user = new User();
        user.setUser_name(username);
        user.setPassword(password);

        Optional<User> foundUser = userdao.find(user);

        if (foundUser.isPresent()) {
            user = foundUser.get();
            JOptionPane.showMessageDialog(this, "Login successful! User ID: " + user.getUser_id());

            
            new RemainderManager(user);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    
    public static void main(String[] args) {
        new UserInterface();
    }
}
