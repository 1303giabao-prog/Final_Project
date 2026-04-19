import java.sql.*;


import exceptions.CourseNotFoundException;


public class CRUD_Operation implements Customer_DAO {
	
	// --- SEARCH ---
	// --- READ (ONE) ---\/
	@Override
	// M44 change start ---
	// I added custom exception to handle when a customer is not found
	public Customer searchCustomer(int Id) throws CustomerNotFoundException {
	// --- M 4 change end ---
	    if (Database_Connectivity.conn == null) {
	        System.out.println("No database connection!");
	        return null; 
	    }

	    String sql = "SELECT * FROM customers WHERE id = ?";
	    Customer foundCustomer = null;
	    
	    try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
	        
	        // 1. We still use the ID to search the database
	        pstmt.setInt(1, Id); 
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) { 
	                // 2. Extract the data we need
	                String name = rs.getString("name");
	                String email = rs.getString("email");
	                String phone = rs.getString("phoneNum");
	                
	                String mem = rs.getString("membership");
	                Customer.Membership membership = Customer.Membership.valueOf(mem);
	                
	                
	                // 3. Make foundcustomer object in system
	                foundCustomer = new Customer(email, name, phone, membership);
	                
	                // We can still print the searchId here since we passed it into the method!
	                System.out.println( foundCustomer.toString());
	            } else {
	                // M4 change start ---
	                throw new CustomerNotFoundException("No customer found with ID: " + Id);
	                // M4 change end ---
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Problem searching for customer: " + e.getMessage());
	    }
	    
	    return foundCustomer;
	}
	
	// SEARCH FOR REGISTRATION IN REGIS LIST
	@Override
	public Registration searchRegistration(String name) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
	    if (Database_Connectivity.conn == null) {
	        System.out.println("No database connection!");
	        return null; 
	    }

	   
	    String sql = "SELECT * FROM registrations WHERE name = ?"; //use name to find customers
	    Registration foundReg = null;
	    
	    try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, name); 
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) { 
	                
	            	//Extract the data we need
	                String name1 = rs.getString("name");
	                String email = rs.getString("email");
	                String phone = rs.getString("phoneNum");
	                int courseId = rs.getInt("course_id");
	                String sta = rs.getString("status");
	                int date = rs.getInt("date");
	               Registration.Status regstatus =  Registration.Status.valueOf(sta);

	          
	                foundReg = new Registration( name1, email, phone, regstatus, courseId, date);
	                
	                System.out.println("Customer found: " + foundReg.toString());
	            } else {
	                // Throw exception If name not found
	                throw new CustomerNotFoundException("No registration found with " + name);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Problem searching for registration: " + e.getMessage());
	    }
	    
	    return foundReg;
	}
	

//SEARCG FOR COURSE  IN COURSES LIST
	public Course searchCourse(int Id) throws CourseNotFoundException  {
	    //  Check if the database connection is established
	    if (Database_Connectivity.conn == null) {
	        System.out.println("No database connection!");
	        return null;
	    }

	    // Define the SQL query using a '?' as a placeholder for security (prevents SQL Injection)
	    String sql = "SELECT c.*, s.name AS ptName \r\n"
	    		+ "FROM courses c \r\n"
	    		+ "JOIN staff s ON c.trainer_id = s.id \r\n"
	    		+ "WHERE c.id = ?";
	    Course foundCourse = null;
	    
	    //  Use try-with-resources to ensure the PreparedStatement is closed automatically
	    try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
	        
	        // Bind the integer ID to the first '?' in the SQL query
	        pstmt.setInt(1, Id); 
	        
	        //  Execute the query and store results in a ResultSet
	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Check if at least one row was returned
	            if (rs.next()) { 
	                
	                //  Extract data from the current row using column names from your database schema
	                int id = rs.getInt("id");
	                String courseName = rs.getString("courseName");
	                int session = rs.getInt("session");
	                
	                // Use getDouble for DECIMAL types in SQL
	                double cost = rs.getDouble("cost"); 
	               
	              
	                //  Handle the new 'status' attribute
	                // Retrieve the string value ('FULL' or 'NOT FULL')
	                String statusStr = rs.getString("status");
	                
	                // Convert the String from the DB into your Java Enum. 
	           
	                Course.courseStatus courseStatus = Course.courseStatus.valueOf(statusStr.replace(" ", "_").toUpperCase());
	                String ptName = rs.getString("ptName");

	                //  Create a new Course object using the retrieved data
	                foundCourse = new Course( id, courseName, session,cost, ptName, courseStatus);
	                
	                System.out.println(foundCourse.toString());
	                
	            } else {
	                //  If rs.next() is false, the ID does not exist in the database
	                throw new CourseNotFoundException("No course found with ID: " + Id);
	            }
	        }
	    } catch (SQLException e) {
	        // Handle database-related errors (syntax, connection drops, etc.)
	        System.out.println("Problem searching for course: " + e.getMessage());
	    }
	    
	    return foundCourse;
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    
	// --- CREATE ---//
    @Override
    public void addCustomer(String name, String email, String phoneNum, Customer.Membership membership) {
        // 1. Check if the database connection is active
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 2. Write the SQL statement. The '?' are placeholders for our variables.
        String sql = "INSERT INTO customers (name, email, phoneNum, membership) VALUES (?, ?, ?, ?)";
        
        // 3. Use try-with-resources to automatically close the PreparedStatement when done
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
            
            // 4. Fill in the '?' placeholders with our actual data
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNum);
            // Convert the enum to a String (e.g., "PREMIUM") so the database can store it
            pstmt.setString(4, membership.name()); 

            // 5. Execute the command
            int rowsAffected = pstmt.executeUpdate();
            // check if there is any change, if no, then throws exception
            if (rowsAffected > 0) {
                System.out.println("Success! Customer '" + name + "' added to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Problem adding customer: " + e.getMessage());
        }
    }
    
    
    
	@Override
	public void addRegistration(String name, String email, String phoneNum, int CourseId, Registration.Status status) {
		   // 1. Check if the database connection is active
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 2. Write the SQL statement. The '?' are placeholders for our variables.
        String sql = "INSERT INTO registrations (name, email, phoneNum, course_id,  status) VALUES (?, ?, ?, ?, ?)";
        
        // 3. Use try-with-resources to automatically close the PreparedStatement when done
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
            
            // 4. Fill in the '?' placeholders with our actual data
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNum);
            // Convert the enum to a String (e.g., "FREEZE") so the database can store it
            pstmt.setInt(4, CourseId);
            pstmt.setString(5, status.name()); 
           
            

            // 5. Execute the command
            int rowsAffected = pstmt.executeUpdate();
            // check if there is any change, if no, then throws exception
            if (rowsAffected > 0) {
                System.out.println("Success! '" + name + "' has been added ");
            }
        } catch (SQLException e) {
            System.out.println("Problem adding customer: " + e.getMessage());
        }
		
	
		
	}

    
    
    
    
    
 // --- READ (ALL) ---
	
	
	// SHOW ALL THE CUSTOMER WHO DOES NOT ENROLL IN THE COURSES
    @Override
    public void displayAllcustomers() {
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }
// sql script
        String sql = "SELECT * FROM customers";
        
        try (Statement stmt = Database_Connectivity.conn.createStatement();
        		//excute the script by this
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n================================= GYM CUSTOMER LIST =================================");
            // Header for the table
            System.out.printf("%-4s | %-18s | %-12s | %-12s | %-25s\n", 
                              "ID", "NAME", "PHONE", "TIER", "EMAIL");
            System.out.println("-------------------------------------------------------------------------------------");
            // read and retrieve the data in order
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phoneNum");
                String mem = rs.getString("membership");
                
                // Print using matching alignment widths
                System.out.printf("%-4d | %-18s | %-12s | %-12s | %-25s\n", 
                                   id, name, phone, mem, email);
            }
            System.out.println("=====================================================================================");
            
        } catch (SQLException e) {
            System.out.println("Problem reading customers: " + e.getMessage());
        }
    }
    
    //SHOW ALL THE COURSES
    @Override
    public void showAllCourses() {
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 1. Added 'c.status' to the SELECT statement
        String sql = "SELECT c.id, c.courseName, s.name AS trainerName, c.session, c.cost, c.status " +
                     "FROM courses c " +
                     "INNER JOIN staff s ON c.trainer_id = s.id";
        
        try (Statement stmt = Database_Connectivity.conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n==================================== GYM COURSE LIST ====================================");
            // 2. Updated header to include "STATUS" and adjusted column widths
            System.out.printf("%-4s | %-20s | %-18s | %-10s | %-10s | %-10s\n", 
                              "ID", "COURSE NAME", "TRAINER", "SESSIONS", "PRICE", "STATUS");
            System.out.println("-----------------------------------------------------------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("courseName");
                String trainer = rs.getString("trainerName");
                int sessions = rs.getInt("session");
                double cost = rs.getDouble("cost");
                // 3. Extract the status string from the ResultSet
                String status = rs.getString("status");
                
                // 4. Added %-10s at the end to display the status column
                System.out.printf("%-4d | %-20s | %-18s | %-10d | $%-9.2f | %-10s\n", 
                                   id, name, trainer, sessions, cost, status);
            }
            System.out.println("=========================================================================================");
            
        } catch (SQLException e) {
            System.out.println("Problem reading courses: " + e.getMessage());
        }
    }

//SHOW ALL THE TRAINERS

    @Override
    public void showAllTrainers() {
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        String sql = "SELECT * FROM staff";
        
        try (Statement stmt = Database_Connectivity.conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // formatting to make the output look organized
            System.out.println("\n================================================= GYM TRAINER LIST ================================================");
            System.out.printf("%-3s | %-15s | %-25s | %-15s | %-30s | %-5s\n", 
                              "ID", "NAME", "EMAIL", "PHONE", "CERTIFICATE", "EXP");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            
            // extract the data in order from mariaDB
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phoneNum");
                String certi = rs.getString("certificate");
                int exp = rs.getInt("yearsOfExperience");
                
                // Achievement is often long, so we print it on a new sub-line or keep it compact
                System.out.printf("%-3d | %-15s | %-25s | %-15s | %-30s | %-5d years\n", 
                                   id, name, email, phone, certi, exp);
            }
            System.out.println("===================================================================================================================");
            
        } catch (SQLException e) {
            System.out.println("Problem reading Staff: " + e.getMessage());
        }
    }

//SHOW ALL CUSTOMER REGISTRATION

	@Override
	public void showAllRegistrations() {
	    if (Database_Connectivity.conn == null) {
	        System.out.println("No database connection!");
	        return;
	    }

	    // Using the JOIN query
	    String sql = "SELECT r.name, r.email, r.phoneNum, r.status, c.courseName " +
	                 "FROM registrations r " +
	                 "INNER JOIN courses c ON c.id = r.course_id";
	    
	    try (Statement stmt = Database_Connectivity.conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        
	        System.out.println("\n=========================== GYM REGISTRATIONS LIST ===========================");
	        // Header for the table
	        System.out.printf("%-18s | %-12s | %-20s | %-20s | %-15s\n", 
	                          "NAME", "PHONE", "EMAIL", "COURSE", "STATUS");
	        System.out.println("------------------------------------------------------------------------------");
	        
	        while (rs.next()) {
	            // Extracting data using column names from the SQL query
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String phone = rs.getString("phoneNum");
	            String status = rs.getString("status");
	            String course = rs.getString("courseName");
	            
	            // Place the output neatly
	            System.out.printf("%-18s | %-12s | %-20s | %-20s | %-15s\n", 
	                               name, phone, email, course, status);
	        }
	        System.out.println("==============================================================================");
	        
	    } catch (SQLException e) {
	        System.out.println("Error displaying registrations: " + e.getMessage());
	    }
	}
    
    
 // --- UPDATE ---
    @Override
    // M4 change start ---
    // Added custom exception to handle when the customer to update does not exist
    public void updateCustomer(int targetId, Customer.Membership newMem) throws CustomerNotFoundException {
    // m4 change end ---
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 1. The UPDATE command targets the specific columns, and the WHERE clause protects the rest of the table
        String sql = "UPDATE customers SET membership = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
           
            pstmt.setString(1, newMem.name()); // Convert Enum to String
            pstmt.setInt(2, targetId);         // The 5th '?' is the ID in the WHERE clause

            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Success! Customer with ID " + targetId + " has been updated.");
            } else {
                // M4 change start ---
                throw new CustomerNotFoundException("Update failed: Customer with ID " + targetId + " does not exist.");
                // M4 change end ---
            }
        } catch (SQLException e) {
            System.out.println("Problem updating customer: " + e.getMessage());
        }
    }
    
    
    
    //	UPDATE REGISTRATION'STATUS BY NAME
	@Override
	public void updateRegistration(Registration.Status newcourseStatus, String name) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 1. The UPDATE command targets the specific columns, and the WHERE clause protects the rest of the table
        String sql = "UPDATE registrations SET status = ? WHERE name LIKE ?";
        
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
           
        	pstmt.setString(1, newcourseStatus.name().replace("_", " ")); // Convert Enum to String
            pstmt.setString(2, name);         // The 5th '?' is the ID in the WHERE clause

            int rowsAffected = pstmt.executeUpdate();
            // check if there is a change, if no, then throws errors
            if (rowsAffected > 0) {
                System.out.println("Success! status has been updated.");
            } else {
                
                throw new CustomerNotFoundException("Update failed: Status with " + name + " does not exist.");
               
            }
        } catch (SQLException e) {
            System.out.println("Problem updating customer: " + e.getMessage());
        }
		
		
	}
    
	
	//  UPDATE COURSES'S STATUS ( FULL OR NOT FULL )//UPDATE courses SET status = ? WHERE id = ?";
    
	
	@Override
	public void updateCoursesStatus(Course.courseStatus newcourseStatus, int courseId) throws CourseNotFoundException {
		if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 1. The UPDATE command targets the specific columns, and the WHERE clause protects the rest of the table
        String sql = "UPDATE courses SET status = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
           
            pstmt.setString(1, newcourseStatus.name()); // Convert Enum to String
            pstmt.setInt(2,  courseId);         // The 5th '?' is the ID in the WHERE clause

            int rowsAffected = pstmt.executeUpdate();
            // check if there is a change, if no, then throws errors
            if (rowsAffected > 0) {
                System.out.println("Success! status has been updated.");
            } else {
                
                throw new CourseNotFoundException("Update failed: course does not exist.");
               
            }
        } catch (SQLException e) {
            System.out.println("Problem updating customer: " + e.getMessage());
        }
	
	
	}


    

    // --- DELETE ---//
    @Override
    // M4 change start ---
    // Added custom exception to handle when the customer to delete does not exist
    public void deleteCustomer(int targetId) throws CustomerNotFoundException {
    // M4 change end ---
        // 1. Check if the database is actually connected
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 2. The SQL command. 
        String sql = "DELETE FROM customers WHERE id = ?";
        
        // 3. Prepare the statement to prevent SQL injection
        try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
            
            // 4. Replace the '?' in the SQL string with the actual targetId
            pstmt.setInt(1, targetId);

            // 5. Execute the command and see how many rows were deleted
            int rowsAffected = pstmt.executeUpdate();
            
            // 6. Show that if the customer is deleted or not
            if (rowsAffected > 0) {
                System.out.println("Success! Customer with ID:  " + targetId + " has been deleted.");
            } else {
                // M4 change start ---
                throw new CustomerNotFoundException("Delete failed: Customer with ID " + targetId + " does not exist in the system.");
                // M4 change end ---
            }
            
        } catch (SQLException e) {
            System.out.println("Problem deleting customer: " + e.getMessage());
        }  
    }


    
    
    
 
//FINANCIAL

        
 
    
        
        public void displayYearlyRevenueReport(int targetYear) {
            if (Database_Connectivity.conn == null) {
                System.out.println("Error: Database connection is not active.");
                return;
            }

            // SQL: Sum the amounts for the specific year from the monthly_revenue table
            String sql = "SELECT SUM(total_amount) AS yearly_total FROM monthly_revenue WHERE year = ?";

            try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, targetYear);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double total = rs.getDouble("yearly_total");

                    System.out.println("\n===========================================");
                    System.out.println("     ANNUAL REVENUE REPORT: " + targetYear);
                    System.out.println("===========================================");
                    System.out.printf("  Total Revenue for %d:  $%,.2f\n", targetYear, total);
                    
                    // Logic for 2026: If it's the current year, show progress
                    if (targetYear == 2026) {
                        System.out.println("  Status: Year-to-Date (YTD)");
                    }
                    
                    System.out.println("===========================================");
                }

            } catch (SQLException e) {
                System.out.println("Database Error (Yearly Report): " + e.getMessage());
            }
        }

	//ADD NEW MONTHLY REVENUE
        public void addMonthlyRevenue(MonthlyRevenue revenueObj) {
            if (Database_Connectivity.conn == null) return;

            // Change INSERT to REPLACE. 
            // This tells MariaDB: "If it exists, delete the old one and put the new one."
            String sql = "REPLACE INTO monthly_revenue (month_name, year, total_amount) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
                pstmt.setString(1, revenueObj.getMonth());
                pstmt.setInt(2, revenueObj.getYear());
                pstmt.setDouble(3, revenueObj.getAmount());

                pstmt.executeUpdate();
                System.out.println("-------------------------------------------");
                System.out.println("Successfully UPDATED revenue for " + revenueObj.getMonth() + "!");
                System.out.println("-------------------------------------------");
                
            } catch (SQLException e) {
                // You won't get 'Duplicate entry' errors anymore!
                System.out.println("Database Error: " + e.getMessage());
            }
        }

//CALCULATE EACH MONTH REVENUE

        public MonthlyRevenue calculateCurrentRevenue(int monthInt, int year) {
            double totalRevenue = 0.0;
            
            // SQL: Sum costs where the registration date matches the month and year provided
            String sql = "SELECT SUM(c.cost) AS total_revenue " +
                         "FROM registrations r " +
                         "JOIN courses c ON r.course_id = c.id " +
                         "WHERE r.status = 'ACTIVE' " +
                         "AND MONTH(r.registration_date) = ? " +
                         "AND YEAR(r.registration_date) = ?";

            try (PreparedStatement pstmt = Database_Connectivity.conn.prepareStatement(sql)) {
                pstmt.setInt(1, monthInt);
                pstmt.setInt(2, year);
                
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    totalRevenue = rs.getDouble("total_revenue");
                }
            } catch (SQLException e) {
                System.out.println("Calculation Error: " + e.getMessage());
            }

            // Convert month integer back to String for the object (e.g., 4 -> "April")
            String monthName = java.time.Month.of(monthInt).name();
            
            return new MonthlyRevenue(monthName, totalRevenue, year);
        }

        
        //DISPLAY MONTHLY REVENUE REPORT
        public void displayAllMonthlyRevenue() {
            if (Database_Connectivity.conn == null) {
                System.out.println("Error: Database connection is not active.");
                return;
            }

            // SQL: Order by year and then by id to keep months in chronological order
            String sql = "SELECT month_name, total_amount, year FROM monthly_revenue ORDER BY year DESC, id DESC";

            System.out.println("\n" + "=".repeat(50));
            System.out.printf("%-10s | %-12s | %-15s\n", "YEAR", "MONTH", "REVENUE");
            System.out.println("-".repeat(50));

            try (Statement stmt = Database_Connectivity.conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    // Map DB columns to variables
                    String month = rs.getString("month_name");
                    double amount = rs.getDouble("total_amount");
                    int year = rs.getInt("year");

                    // Create object and use its toString (or print directly for custom formatting)
                    System.out.printf("%-10d | %-12s | $%,12.2f\n", year, month, amount);
                }

                if (!hasData) {
                    System.out.println("No financial records found in the database.");
                }

            } catch (SQLException e) {
                System.out.println("Database Error (Display Revenue): " + e.getMessage());
            }
            System.out.println("=".repeat(50) + "\n");
        }

	






	



   
		
		
		
	
}























