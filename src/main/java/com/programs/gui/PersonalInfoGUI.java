// PersonalInfoGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.PersonalInfoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class PersonalInfoGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public PersonalInfoGUI(Connection connection)
    {
        this.connection = connection;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Personal Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        infoPanel.add(firstNameField);

        infoPanel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        infoPanel.add(lastNameField);

        infoPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        infoPanel.add(emailField);

        infoPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        infoPanel.add(phoneField);

        infoPanel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        infoPanel.add(addressField);

        infoPanel.add(new JLabel("City:"));
        JTextField cityField = new JTextField();
        infoPanel.add(cityField);

        infoPanel.add(new JLabel("State:"));
        JTextField stateField = new JTextField();
        infoPanel.add(stateField);

        infoPanel.add(new JLabel("Zip Code:"));
        JTextField zipField = new JTextField();
        infoPanel.add(zipField);

        infoPanel.add(new JLabel("Country:"));
        JTextField countryField = new JTextField();
        infoPanel.add(countryField);

        add(infoPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Personal Info");
        saveButton.addActionListener(e ->
        {
            try
            {
                PersonalInfoDAO dao = new PersonalInfoDAO();

                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String address = addressField.getText().trim();
                String city = cityField.getText().trim();
                String state = stateField.getText().trim();
                String zip = zipField.getText().trim();
                String country = countryField.getText().trim();

                if (firstName.isEmpty() || lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "First and Last Name are required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (personalID == 0) {
                    personalID = dao.insertPersonalInfo(connection, firstName, lastName, email, phone, address, city, state, zip, country);
                } else
                {
                    dao.insertPersonalInfo(connection, firstName, lastName, email, phone, address, city, state, zip, country);
                }

                JOptionPane.showMessageDialog(this, "Personal information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving personal information: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}


