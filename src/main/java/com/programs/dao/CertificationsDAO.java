// Certifications Data Access Object

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Certifications;

public class CertificationsDAO
{
    // Method to insert or update certifications
    public void saveCertification(Connection connection, int personalID, String certification, String organization, LocalDate issueDate, LocalDate expirationDate) throws SQLException
    {
        String sql = "INSERT INTO Certifications (personalID, certification, organization, issueDate, expirationDate) " + "VALUES (?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE certification = ?, organization = ?, issueDate = ?, expirationDate = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, certification);
            stmt.setString(3, organization);
            stmt.setDate(4, Date.valueOf(issueDate));
            stmt.setDate(5, Date.valueOf(expirationDate));
            stmt.setString(6, certification);
            stmt.setString(7, organization);
            stmt.setDate(8, Date.valueOf(issueDate));
            stmt.setDate(9, Date.valueOf(expirationDate));
            stmt.executeUpdate();
        }
    }

    // Method to fetch all certifications by personalID
    public List<Certifications> getCertifications(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Certifications WHERE personalID = ?";
        List<Certifications> certificationsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    certificationsList.add(new Certifications(
                        rs.getInt("certID"),
                        rs.getInt("personalID"),
                        rs.getString("certification"),
                        rs.getString("organization"),
                        rs.getDate("issueDate").toLocalDate(),
                        rs.getDate("expirationDate").toLocalDate()
                    ));
                }
            }
        }
        return certificationsList;
    }
}