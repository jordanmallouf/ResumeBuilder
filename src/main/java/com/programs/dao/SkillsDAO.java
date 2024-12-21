// Skills Data Access Object

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Skills;

public class SkillsDAO
{
    // Method to insert or update skills
    public void saveSkill(Connection connection, int personalID, String skill, String skillDescription, String proficiency) throws SQLException
    {
        String sql = "INSERT INTO Skills (personalID, skill, skillDescription, proficiency) VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE skill = ?, skillDescription = ?, proficiency = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, skill);
            stmt.setString(3, skillDescription);
            stmt.setString(4, proficiency);
            stmt.setString(5, skill);
            stmt.setString(6, skillDescription);
            stmt.setString(7, proficiency);
            stmt.executeUpdate();
        }
    }

    // Method to fetch all skills by personalID
    public List<Skills> getSkills(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Skills WHERE personalID = ?";
        List<Skills> skillList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    skillList.add(new Skills(
                        rs.getInt("skillsID"),
                        rs.getInt("personalID"),
                        rs.getString("skill"),
                        rs.getString("skillDescription"),
                        rs.getString("proficiency")
                    ));
                }
            }
        }
        return skillList;
    }
}