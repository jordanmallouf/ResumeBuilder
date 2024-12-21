// ProjectsGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.ProjectsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProjectsGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public ProjectsGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Projects", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel projectPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        projectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        projectPanel.add(new JLabel("Project Name:"));
        JTextField projectNameField = new JTextField();
        projectPanel.add(projectNameField);

        projectPanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        projectPanel.add(descriptionField);

        projectPanel.add(new JLabel("Tools:"));
        JTextField toolsField = new JTextField();
        projectPanel.add(toolsField);

        projectPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        JTextField startDateField = new JTextField();
        projectPanel.add(startDateField);

        projectPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        JTextField endDateField = new JTextField();
        projectPanel.add(endDateField);

        add(projectPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Project");
        saveButton.addActionListener(e ->
        {
            try
            {
                ProjectsDAO dao = new ProjectsDAO();
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                dao.saveProject(
                    connection,
                    personalID,
                    projectNameField.getText(),
                    descriptionField.getText(),
                    toolsField.getText(),
                    startDate,
                    endDate
                );
                JOptionPane.showMessageDialog(this, "Project Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException dtpe)
            {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
