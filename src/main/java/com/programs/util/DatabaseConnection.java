//Java Database Connection for MySQL backend

package main.java.com.programs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    //JDBC Credentials
    private static String url = "jdbc:mysql://localhost:3306/ResumeBuilder";
    private static String user = "root";
    private static String pwd = "GoMeanGreen";

    public static Connection getConnection()
    {
        try
        {
            //Registering JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creating connection to database
            return DriverManager.getConnection(url, user, pwd);
        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}