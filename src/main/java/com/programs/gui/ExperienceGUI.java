// ExperienceGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.ExperienceDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ExperienceGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public ExperienceGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Experience", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel experiencePanel = new JPanel(new GridLayout(6, 2, 10, 10));
        experiencePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        experiencePanel.add(new JLabel("Job Title:"));
        JTextField jobTitleField = new JTextField();
        experiencePanel.add(jobTitleField);

        experiencePanel.add(new JLabel("Company Name:"));
        JTextField companyField = new JTextField();
        experiencePanel.add(companyField);

        experiencePanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        experiencePanel.add(descriptionField);

        experiencePanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        JTextField startDateField = new JTextField();
        experiencePanel.add(startDateField);

        experiencePanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        JTextField endDateField = new JTextField();
        experiencePanel.add(endDateField);

        add(experiencePanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Experience");
        saveButton.addActionListener(e ->
        {
            try
            {
                ExperienceDAO dao = new ExperienceDAO();
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                dao.saveExperience(
                    connection,
                    personalID,
                    jobTitleField.getText(),
                    companyField.getText(),
                    descriptionField.getText(),
                    startDate,
                    endDate
                );
                JOptionPane.showMessageDialog(this, "Experience Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException dtpe)
            {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving experience: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}