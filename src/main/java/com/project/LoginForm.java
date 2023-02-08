package com.project;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    public LoginForm() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        Font serifFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
///////////////////////////////////////////////////////////////////
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        this.setLocation(450, 170);
        this.setTitle("Login Form");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        //Email Address Contents
        JLabel emailAddressLabel = new JLabel("Email Address");
        emailAddressLabel.setBounds(30, 140, 200, 20);
        emailAddressLabel.setFont(serifFont);
        this.add(emailAddressLabel);

        JTextField emailAddressTextField = new JTextField();
        emailAddressTextField.setBounds(30, 160, 300, 30);
        emailAddressTextField.setBorder(blackLine);
        this.add(emailAddressTextField);

        //Password Contents
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 220, 200, 20);
        passwordLabel.setFont(serifFont);
        this.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 240, 300, 30);
        passwordField.setBorder(blackLine);
        this.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(125, 300, 100, 20);
        loginButton.setFont(serifFont);
        loginButton.addActionListener(e -> {
            String emailAddress = emailAddressTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedatabase",
                        "root", "Velmadin1234!");

                String query = "Select EmailAddress, Password from employeelogindetails where EmailAddress=? and Password=?";
                PreparedStatement statement = databaseConnection.prepareStatement(query);

                statement.setString(1, emailAddress);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    JOptionPane.showMessageDialog(loginButton, "You have successfully logged in");
                    databaseConnection.close();
                } else {
                    JOptionPane.showMessageDialog(loginButton, "Incorrect Username & Password! Try again");
                }

            } catch (Exception connectionException) {
                System.err.println("Found an exception");
                System.err.println(connectionException.getMessage());
            }

        });
        this.add(loginButton);

        this.setVisible(true);
    }

}

