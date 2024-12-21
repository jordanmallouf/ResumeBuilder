// SkillsGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.SkillsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class SkillsGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public SkillsGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Skills", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel skillPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        skillPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        skillPanel.add(new JLabel("Skill:"));
        JTextField skillField = new JTextField();
        skillPanel.add(skillField);

        skillPanel.add(new JLabel("Description:"));
        JTextField skillDescriptionField = new JTextField();
        skillPanel.add(skillDescriptionField);

        skillPanel.add(new JLabel("Proficiency:"));
        JTextField proficiencyField = new JTextField();
        skillPanel.add(proficiencyField);

        add(skillPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Skill");
        saveButton.addActionListener(e ->
        {
            try
            {
                SkillsDAO dao = new SkillsDAO();
                dao.saveSkill(
                    connection,
                    personalID,
                    skillField.getText(),
                    skillDescriptionField.getText(),
                    proficiencyField.getText()
                );
                JOptionPane.showMessageDialog(this, "Skill Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving skill: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}