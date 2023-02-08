package com.project;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class RegistrationForm extends JFrame implements ActionListener {

    JButton alreadyRegisteredButton = new JButton("Sign In");

    RegistrationForm() {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        Font serifFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
////////////////////////////////////////////////////////////////////////////
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        this.setLocation(450, 170);
        this.setTitle("Registration Form");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);

        //First Name Contents
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(30, 80, 200, 20);
        firstNameLabel.setFont(serifFont);
        this.add(firstNameLabel);

        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setBounds(30, 100, 300, 30);
        firstNameTextField.setBorder(blackLine);
        this.add(firstNameTextField);

        //Last Name Contents
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(30, 160, 200, 20);
        lastNameLabel.setFont(serifFont);
        this.add(lastNameLabel);

        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setBounds(30, 180, 300, 30);
        lastNameTextField.setBorder(blackLine);
        this.add(lastNameTextField);

        //Email Address Contents
        JLabel emailAddressLabel = new JLabel("Email Address");
        emailAddressLabel.setBounds(30, 240, 200, 20);
        emailAddressLabel.setFont(serifFont);
        this.add(emailAddressLabel);

        JTextField emailAddressTextField = new JTextField();
        emailAddressTextField.setBounds(30, 260, 300, 30);
        emailAddressTextField.setBorder(blackLine);
        this.add(emailAddressTextField);

        //Password Contents
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 320, 200, 20);
        passwordLabel.setFont(serifFont);
        this.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 340, 300, 30);
        passwordField.setBorder(blackLine);
        this.add(passwordField);

        //Password Contents
        JLabel alreadyRegisteredLabel = new JLabel("Already Registered?");
        alreadyRegisteredLabel.setBounds(30, 460, 200, 20);
        alreadyRegisteredLabel.setFont(serifFont);
        this.add(alreadyRegisteredLabel);

        alreadyRegisteredButton.setBounds(190, 460, 100, 20);
        alreadyRegisteredButton.setFont(serifFont);
        alreadyRegisteredButton.addActionListener(this);
        this.add(alreadyRegisteredButton);

        //Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(125, 400, 100, 20);
        registerButton.setFont(serifFont);
        registerButton.addActionListener(e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String emailAddress = emailAddressTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedatabase",
                        "root", "Velmadin1234!");

                String query = "insert into employeeLoginDetails(FirstName,LastName,EmailAddress,Password)" + "values(?,?,?,?)";
                PreparedStatement statement = databaseConnection.prepareStatement(query);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, emailAddress);
                statement.setString(4, password);
                statement.execute();

                JOptionPane.showMessageDialog(this, "You have successfully registered an account!");

                databaseConnection.close();

            } catch (Exception connectionException) {
                System.err.println("Found an exception");
                System.err.println(connectionException.getMessage());
            }
        });
        this.add(registerButton);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alreadyRegisteredButton) {
            LoginForm loginFrame = new LoginForm();
            this.dispose();
        }
    }
}



