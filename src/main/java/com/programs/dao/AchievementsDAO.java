// AchievementsDAO class to handle database operations for achievments

package main.java.com.programs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.programs.model.Achievements;

public class AchievementsDAO
{
    // Method to insert or update achievements
    public void saveAchievement(Connection connection, int personalID, String achievement, String achievementDescription, LocalDate achievementDate) throws SQLException
    {
        String sql = "INSERT INTO Achievements (personalID, achievement, achievementDescription, achievementDate) " + "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE achievement = ?, achievementDescription = ?, achievementDate = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            stmt.setString(2, achievement);
            stmt.setString(3, achievementDescription);
            stmt.setDate(4, Date.valueOf(achievementDate));
            stmt.setString(5, achievement);
            stmt.setString(6, achievementDescription);
            stmt.setDate(7, Date.valueOf(achievementDate));
            stmt.executeUpdate();
        }
    }

    // Method to fetch all achievements by personalID
    public List<Achievements> getAchievements(Connection connection, int personalID) throws SQLException
    {
        String sql = "SELECT * FROM Achievements WHERE personalID = ?";
        List<Achievements> achievements = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    achievements.add(new Achievements(
                        rs.getInt("achievementID"),
                        rs.getInt("personalID"),
                        rs.getString("achievement"),
                        rs.getString("achievementDescription"),
                        rs.getDate("achievementDate").toLocalDate()
                    ));
                }
            }
        }
        return achievements;
    }
}