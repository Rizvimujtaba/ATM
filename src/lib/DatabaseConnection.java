package lib;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_db";
    private static final String USER = "root";  
   
    // If the "DB_PASS" variable exists on your PC, use it. Otherwise, use "1234".
    private static final String PASSWORD = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "1234";

    public static Connection getConnection()  {
       Connection connection = null;
       try {
        // Load the MySQL  driver you just added
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database connected successfully.");
       } catch (SQLException e) {
        System.out.println("Failed to connect to the database.");
        e.printStackTrace();
       } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found.");
        e.printStackTrace();
       }
         return connection;
    }
}
