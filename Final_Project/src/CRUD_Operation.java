import java.sql.*;
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
            
            if (rowsAffected > 0) {
                System.out.println("Success! Customer '" + name + "' added to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Problem adding customer: " + e.getMessage());
        }
    }
 // --- READ (ALL) ---
    @Override
    public void displayAllcustomers() {
        if (Database_Connectivity.conn == null) {
            System.out.println("No database connection!");
            return;
        }

        // 1. Simple SQL command to get everything
        String sql = "SELECT * FROM customers";
        
        // 2. Create a Statement and a ResultSet to hold the downloaded data
        try (Statement stmt = Database_Connectivity.conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- Gym Customer List ---");
            
            // 3. Loop through every row the database returned
            while (rs.next()) {
                // Extract the data from the current row
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phoneNum");
                String mem = rs.getString("membership");
                
                // Print it out neatly
                System.out.printf("ID: %-3d | Name: %-15s | Phone: %-12s | Tier: %-8s | Email: %s\n", 
                                  id, name, phone, mem, email);
            }
            System.out.println("-------------------------");
            
        } catch (SQLException e) {
            System.out.println("Problem reading customers: " + e.getMessage());
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
                throw new CustomerNotFoundException("Delete failed: Customer with ID " + targetId + " does not exist in the database.");
                // M4 change end ---
            }
            
        } catch (SQLException e) {
            System.out.println("Problem deleting customer: " + e.getMessage());
        }  
    }
}