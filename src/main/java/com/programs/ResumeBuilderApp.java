// Resume Builder Application
// Main Entry Point

package main.java.com.programs;

import main.java.com.programs.dao.*;
import main.java.com.programs.gui.*;
import main.java.com.programs.util.PDFGenerator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ResumeBuilderApp
{
    public static void main(String [] args) {
        String url = "jdbc:mysql://localhost:3306/ResumeBuilder";
        String user = "root";
        String pwd = "GoMeanGreen";

        try
        {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            JFrame frame = new JFrame("Resume Builder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Ask for user's first and last name
            String fName = JOptionPane.showInputDialog(frame, "Enter your first name:");
            String lName = JOptionPane.showInputDialog(frame, "Enter your last name:");

            if (fName == null || lName == null || fName.trim().isEmpty() || lName.trim().isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "First and last name are required to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
                connection.close();
                return;
            }

            PersonalInfoDAO personalInfoDAO = new PersonalInfoDAO();
            var personalInfo = personalInfoDAO.getPersonalInfo(connection, fName.trim(), lName.trim());

            if (personalInfo.isPresent())
            {
                // User exists, ask to retrieve or create
                int choice = JOptionPane.showOptionDialog(
                        frame,
                        "Would you like to retrieve an existing resume or create a new one?",
                        "Choose an Option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Retrieve Existing", "Create New"},
                        "Retrieve Existing"
                );

                if (choice == JOptionPane.YES_OPTION)
                {
                    // Retrieve existing resume and generate PDF
                    PDFGenerator.generatePDF(connection, personalInfo.get().getID());
                    JOptionPane.showMessageDialog(frame, "Resume has been retrieved and saved as a PDF.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (choice == JOptionPane.NO_OPTION)
                {
                    // Create new resume
                    displayTabs(frame, connection, personalInfo.get().getID());
                }
            } else
            {
                // Insert new personal info and use the returned personalID directly
                int generatedID = personalInfoDAO.insertPersonalInfo(connection, fName.trim(), lName.trim(), "", "", "", "", "", "", "");
                if (generatedID > 0)
                {
                    displayTabs(frame, connection, generatedID);
                } else
                {
                    JOptionPane.showMessageDialog(frame, "Error creating new personal record. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Close the connection when the application ends
            frame.addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    try
                    {
                        connection.close();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void displayTabs(JFrame frame, Connection connection, int personalID)
    {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Personal Info", new PersonalInfoGUI(connection));
        tabbedPane.addTab("Education", new EducationGUI(connection, personalID));
        tabbedPane.addTab("Experience", new ExperienceGUI(connection, personalID));
        tabbedPane.addTab("Certifications", new CertificationsGUI(connection, personalID));
        tabbedPane.addTab("Projects", new ProjectsGUI(connection, personalID));
        tabbedPane.addTab("Skills", new SkillsGUI(connection, personalID));
        tabbedPane.addTab("Achievements", new AchievementsGUI(connection, personalID));

        // Add Submit Tab
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new BorderLayout());
        JLabel submitLabel = new JLabel("Submit Resume", JLabel.CENTER);
        submitLabel.setFont(new Font("Arial", Font.BOLD, 18));
        submitPanel.add(submitLabel, BorderLayout.NORTH);

        JButton submitButton = new JButton("Submit and Generate PDF");
        submitButton.addActionListener(e ->
        {
            try
            {
                PDFGenerator.generatePDF(connection, personalID); // Use the personalID directly
                JOptionPane.showMessageDialog(frame, "Resume has been created and saved as a PDF.", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        submitPanel.add(submitButton, BorderLayout.CENTER);

        tabbedPane.addTab("Submit", submitPanel);

        // Display the frame
        frame.setContentPane(tabbedPane);
        frame.setVisible(true);
    }
}
