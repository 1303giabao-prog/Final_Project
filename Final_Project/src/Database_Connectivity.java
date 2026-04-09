import java.sql.*;
import java.util.Scanner;

public class Database_Connectivity {
	
	 // Global connection variable
    static Connection conn = null;
    static Statement statement = null;
   
    // Database credentials.
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "demo211";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "P@ssw0rd"; 


	
	//Connect to database
    public static void connect() {
        final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", 
                                            SERVER, PORT, DATABASE, USERNAME, PASSWORD);
        try {
            conn = DriverManager.getConnection(DB_URL);
            //create table
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS customers (customerID INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(100) NOT NULL, firstName VARCHAR(50), lastName VARCHAR(50), phoneNum VARCHAR(20), membership VARCHAR(20));";
            statement.execute(sql);
            System.out.println("DB connection established.");
        } catch(SQLException e) {
            System.out.println("Problem with connecting to DB: " + e.getMessage());
        }
    }

    // Safely close the database connection
    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DB connection safely closed.");
            }
        } catch (SQLException e) {
            System.out.println("Problem closing the DB connection: " + e.getMessage());
        }
    }
    
	
	

}
