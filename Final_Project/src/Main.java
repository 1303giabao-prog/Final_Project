import java.util.Scanner;

public class Main {
	

	  
	// --- Helper Method to Validate Email Format ---
    public static boolean isValidEmail(String email) {
        // This pattern forces the format: [characters] @ [characters] . [characters]
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$";
        
        // .matches() returns true if the email perfectly fits the regex pattern
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
    	Customer_DAO db = new CRUD_Operation();
    	Database_Connectivity.connect();
    	

    	
    

        Scanner sc = new Scanner(System.in);
        int choice;
//Tools to manage the gym system. Choose to modify/view/update/delete data
        do {
            System.out.println("\n---  Management System ---");
            System.out.println("1. Add customer");
            System.out.println("2. Search customer");
            System.out.println("3. Delete customer");
            System.out.println("4. Update customer's information");
            System.out.println("5. View all the customers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1:
                System.out.print("Email: ");
                String email = sc.nextLine().toLowerCase();
                
                // 1. Check if the email is bad
                if (!isValidEmail(email)) {
                    System.out.println("ERROR: Invalid email format. (e.g., name@gmail.com)");
                    System.out.println("Returning to main menu...");
                    
                    // 2. This instantly ejects them from 'case 1'. 
                    // It skips all the code below and restarts your main menu!
                    break; 
                }
                
                // 3. If the email WAS good, it ignores the 'if' block and keeps asking questions
                System.out.print("Name: ");
                String name = sc.nextLine();
                
                System.out.print("Phone: ");
                String phone = sc.nextLine();
                
                System.out.print("Membership (NONE, BASIC, PREMIUM): ");
                String mem = sc.nextLine().toUpperCase();
                Customer.Membership membership;
                try {
                    membership = Customer.Membership.valueOf(mem);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid membership entered. Defaulting to NONE.");
                    membership = Customer.Membership.NONE;
                }
                
                // 4. Finally, it implements the code to save to the database
                db.addCustomer(name, email, phone, membership);
                break;
                 

                case 2:
                	System.out.print("Enter Customer ID to search: ");
                    
                    // 1. Grab the ID the user typed
                    int searchId = sc.nextInt(); 
                    
                    // Clear the scanner buffer (always good practice after grabbing an int)
                    sc.nextLine(); 
                    
                    // 2. Send the ID to your CRUD class to find the customer!
                    db.searchCustomer(searchId);
         
                 
                    break;

                case 3:
                    System.out.print("Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                 
                   db.deleteCustomer(id);
                   break;
                   // UPDATE membership status
                case 4:
                	System.out.print("Customer ID: ");
                	int sid = sc.nextInt();
                	// Show customer's information to check memebership status
                	
                	System.out.println("Customer's information");
                	db.searchCustomer(sid);
                    sc.nextLine();
                    
                    
                    
                    System.out.println("Update membership status:");
                    String memStatus= sc.nextLine().toUpperCase();
                    Customer.Membership newMembership;
                    try {
                        newMembership = Customer.Membership.valueOf(memStatus);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid membership entered. Update cancelled.");
                        break; // Exit the switch statement early
                    }
                    db.updateCustomer(sid, newMembership);
            
               
                    
                    
                  
                    
                 
                    break;
                case 5:
                	System.out.print("----ALL CUSTOMERS----");
                	db.displayAllcustomers();
                	
                	break;
            }

        } while (choice != 0);

        Database_Connectivity.disconnect();
        sc.close();
    }
}
