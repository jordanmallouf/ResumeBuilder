// Projects Data Access Object

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Projects;

public class ProjectsDAO
{
    // Method to insert or update projects
    public void saveProject(Connection connection, int personalID, String projectName, String description, String tools, LocalDate startDate, LocalDate endDate) throws SQLException
    {
        String sql = "INSERT INTO Projects (personalID, projectName, description, tools, startDate, endDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?) " + "ON DUPLICATE KEY UPDATE projectName = ?, description = ?, tools = ?, startDate = ?, endDate = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, projectName);
            stmt.setString(3, description);
            stmt.setString(4, tools);
            stmt.setDate(5, Date.valueOf(startDate));
            stmt.setDate(6, Date.valueOf(endDate));
            stmt.setString(7, projectName);
            stmt.setString(8, description);
            stmt.setString(9, tools);
            stmt.setDate(10, Date.valueOf(startDate));
            stmt.setDate(11, Date.valueOf(endDate));
            stmt.executeUpdate();
        }
    }

    // Method to fetch all projects by personalID
    public List<Projects> getProjects(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Projects WHERE personalID = ?";
        List<Projects> projectList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    projectList.add(new Projects(
                        rs.getInt("projectID"),
                        rs.getInt("personalID"),
                        rs.getString("projectName"),
                        rs.getString("description"),
                        rs.getString("tools"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate()
                    ));
                }
            }
        }
        return projectList;
    }
}
