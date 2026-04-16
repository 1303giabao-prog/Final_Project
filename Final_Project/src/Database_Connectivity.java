import java.sql.*;
import java.util.Scanner;

public class Database_Connectivity {
	
	 // Global connection variable
    static Connection conn = null;

   
    // Database credentials.
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "gymmanagementsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "P@ssw0rd"; 


	
	//Connect to database
	// --- Member 4 change start ---
    public static void connect() throws DatabaseConnectionException {
    // --- Member 4 change end ---
        final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", 
                                            SERVER, PORT, DATABASE, USERNAME, PASSWORD);
        try {
            conn = DriverManager.getConnection(DB_URL);
            
            System.out.println("DB connection established.");
        } catch(SQLException e) {
        	// --- Member 4 change start ---
            throw new DatabaseConnectionException("Problem with connecting to DB: " + e.getMessage());
            // --- Member 4 change end ---
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