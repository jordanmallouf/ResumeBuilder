// EducationGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.EducationDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EducationGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public EducationGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Education", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel educationPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        educationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        educationPanel.add(new JLabel("Degree:"));
        JTextField degreeField = new JTextField();
        educationPanel.add(degreeField);

        educationPanel.add(new JLabel("Institution:"));
        JTextField institutionField = new JTextField();
        educationPanel.add(institutionField);

        educationPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        JTextField startDateField = new JTextField();
        educationPanel.add(startDateField);

        educationPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        JTextField endDateField = new JTextField();
        educationPanel.add(endDateField);

        educationPanel.add(new JLabel("Major:"));
        JTextField majorField = new JTextField();
        educationPanel.add(majorField);

        educationPanel.add(new JLabel("GPA:"));
        JTextField gpaField = new JTextField();
        educationPanel.add(gpaField);

        add(educationPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Education");
        saveButton.addActionListener(e ->
        {
            try
            {
                EducationDAO dao = new EducationDAO();
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                double gpa = Double.parseDouble(gpaField.getText());
                dao.saveEducation(
                    connection,
                    personalID,
                    degreeField.getText(),
                    institutionField.getText(),
                    startDate,
                    endDate,
                    majorField.getText(),
                    gpa
                );
                JOptionPane.showMessageDialog(this, "Education Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException dtpe)
            {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(this, "Invalid GPA format. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving education: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
