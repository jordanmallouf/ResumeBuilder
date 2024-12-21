// CertificationsGUI Program

package main.java.com.programs.gui;

import main.java.com.programs.dao.CertificationsDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CertificationsGUI extends JPanel
{
    private Connection connection;
    private int personalID;

    public CertificationsGUI(Connection connection, int personalID)
    {
        this.connection = connection;
        this.personalID = personalID;
        initialize();
    }

    private void initialize()
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Certifications", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel certificationPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        certificationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        certificationPanel.add(new JLabel("Certification:"));
        JTextField certificationField = new JTextField();
        certificationPanel.add(certificationField);

        certificationPanel.add(new JLabel("Organization:"));
        JTextField organizationField = new JTextField();
        certificationPanel.add(organizationField);

        certificationPanel.add(new JLabel("Issue Date (YYYY-MM-DD):"));
        JTextField issueDateField = new JTextField();
        certificationPanel.add(issueDateField);

        certificationPanel.add(new JLabel("Expiration Date (YYYY-MM-DD):"));
        JTextField expirationDateField = new JTextField();
        certificationPanel.add(expirationDateField);

        add(certificationPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Certification");
        saveButton.addActionListener(e ->
        {
            try
            {
                CertificationsDAO dao = new CertificationsDAO();
                LocalDate issueDate = LocalDate.parse(issueDateField.getText());
                LocalDate expirationDate = LocalDate.parse(expirationDateField.getText());
                dao.saveCertification(
                    connection,
                    personalID,
                    certificationField.getText(),
                    organizationField.getText(),
                    issueDate,
                    expirationDate
                );
                JOptionPane.showMessageDialog(this, "Certification Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException dtpe)
            {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error saving certification: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
