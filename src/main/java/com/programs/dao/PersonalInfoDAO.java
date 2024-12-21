// Personal Information Data Access Object

package main.java.com.programs.dao;

import main.java.com.programs.model.PersonalInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class PersonalInfoDAO {

    public Optional<PersonalInfo> getPersonalInfo(Connection connection, String fName, String lName)
    {
        String sql = "SELECT * FROM PersonalInfo WHERE fName = ? AND lName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    PersonalInfo personalInfo = new PersonalInfo(
                        rs.getInt("personalID"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("country")
                    );
                    return Optional.of(personalInfo);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<PersonalInfo> getPersonalInfoById(Connection connection, int personalID)
    {
        String query = "SELECT * FROM PersonalInfo WHERE personalID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setInt(1, personalID);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    PersonalInfo personalInfo = new PersonalInfo(
                        rs.getInt("personalID"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"),
                        rs.getString("country")
                    );
                    return Optional.of(personalInfo);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int insertPersonalInfo(Connection connection, String fName, String lName, String email, String phone, String address, String city, String state, String zip, String country)
    {
        String sql = "INSERT INTO PersonalInfo (fName, lName, email, phone, address, city, state, zip, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setString(8, zip);
            stmt.setString(9, country);
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys())
            {
                if (keys.next())
                {
                    return keys.getInt(1);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}
