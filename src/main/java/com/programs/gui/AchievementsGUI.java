// AchievementsGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.AchievementsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AchievementsGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public AchievementsGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Achievements", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel achievementPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        achievementPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        achievementPanel.add(new JLabel("Achievement:"));
        JTextField achievementField = new JTextField();
        achievementPanel.add(achievementField);

        achievementPanel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        achievementPanel.add(descriptionField);

        achievementPanel.add(new JLabel("Achievement Date (YYYY-MM-DD):"));
        JTextField dateField = new JTextField();
        achievementPanel.add(dateField);

        add(achievementPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Achievement");
        saveButton.addActionListener(e ->
        {
            try
            {
                AchievementsDAO dao = new AchievementsDAO();
                LocalDate achievementDate = LocalDate.parse(dateField.getText());
                dao.saveAchievement(
                    connection,
                    personalID,
                    achievementField.getText(),
                    descriptionField.getText(),
                    achievementDate
                );
                JOptionPane.showMessageDialog(this, "Achievement Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException dtpe)
            {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving achievement: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
