// Experience Data Access Object

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Experience;

public class ExperienceDAO
{
    // Method to insert or update experience
    public void saveExperience(Connection connection, int personalID, String jobTitle, String companyName, String description, LocalDate startDate, LocalDate endDate) throws SQLException
    {
        String sql = "INSERT INTO Experience (personalID, jobTitle, companyName, description, startDate, endDate) " + "VALUES (?, ?, ?, ?, ?, ?) " + 
         "ON DUPLICATE KEY UPDATE jobTitle = ?, companyName = ?, description = ?, startDate = ?, endDate = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, jobTitle);
            stmt.setString(3, companyName);
            stmt.setString(4, description);
            stmt.setDate(5, Date.valueOf(startDate));
            stmt.setDate(6, Date.valueOf(endDate));
            stmt.setString(7, jobTitle);
            stmt.setString(8, companyName);
            stmt.setString(9, description);
            stmt.setDate(10, Date.valueOf(startDate));
            stmt.setDate(11, Date.valueOf(endDate));
            stmt.executeUpdate();
        }
    }

    // Method to fetch all experiences by personalID
    public List<Experience> getExperience(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Experience WHERE personalID = ?";
        List<Experience> experienceList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    experienceList.add(new Experience(
                        rs.getInt("experienceID"),
                        rs.getInt("personalID"),
                        rs.getString("jobTitle"),
                        rs.getString("companyName"),
                        rs.getString("description"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate()
                    ));
                }
            }
        }
        return experienceList;
    }
}