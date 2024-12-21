// Education Data Access Object

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Education;

public class EducationDAO
{
    // Method to insert or update education
    public void saveEducation(Connection connection, int personalID, String degree, String institution, LocalDate startDate, LocalDate endDate, String major, double gpa) throws SQLException
    {
        String sql = "INSERT INTO Education (personalID, degree, institution, startDate, endDate, major, gpa) " + "VALUES (?, ?, ?, ?, ?, ?, ?) " + 
         "ON DUPLICATE KEY UPDATE degree = ?, institution = ?, startDate = ?, endDate = ?, major = ?, gpa = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, degree);
            stmt.setString(3, institution);
            stmt.setDate(4, Date.valueOf(startDate));
            stmt.setDate(5, Date.valueOf(endDate));
            stmt.setString(6, major);
            stmt.setDouble(7, gpa);
            stmt.setString(8, degree);
            stmt.setString(9, institution);
            stmt.setDate(10, Date.valueOf(startDate));
            stmt.setDate(11, Date.valueOf(endDate));
            stmt.setString(12, major);
            stmt.setDouble(13, gpa);
            stmt.executeUpdate();
        }
    }

    // Method to fetch all education by personalID
    public List<Education> getEducation(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Education WHERE personalID = ?";
        List<Education> educationList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    educationList.add(new Education(
                        rs.getInt("educationID"),
                        rs.getInt("personalID"),
                        rs.getString("degree"),
                        rs.getString("institution"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate(),
                        rs.getString("major"),
                        rs.getDouble("gpa")
                    ));
                }
            }
        }
        return educationList;
    }
}