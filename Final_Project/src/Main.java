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
                Customer.Membership membership = Customer.Membership.valueOf(mem);
                
                // 4. Finally, it implements the code to save to the database
                CRUD_Operation.addCustomer(name, email, phone, membership);
                break;
                 

                case 2:
                	System.out.print("Enter Customer ID to search: ");
                    
                    // 1. Grab the ID the user typed
                    int searchId = sc.nextInt(); 
                    
                    // Clear the scanner buffer (always good practice after grabbing an int)
                    sc.nextLine(); 
                    
                    // 2. Send the ID to your CRUD class to find the customer!
                    CRUD_Operation.searchCustomer(searchId);
         
                 
                    break;

                case 3:
                    System.out.print("Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                 
                   CRUD_Operation.deleteCustomer(id);
                   break;

                case 4:
                	System.out.print("Customer ID: ");
                	int sid = sc.nextInt();
                 
                    sc.nextLine();
                    System.out.println(" membership status:");
                    String memStatus= sc.nextLine();
                    Customer.Membership Membership = Customer.Membership.valueOf(memStatus);
                    CRUD_Operation.updateCustomer(sid, Membership);
            
               
                    
                    
                  
                    
                 
                    break;
                case 5:
                	System.out.print("----ALL CUSTOMERS----");
                	CRUD_Operation.displayAllcustomers();
                	
                	break;
            }

        } while (choice != 0);

        Database_Connectivity.disconnect();
        sc.close();
    }
}
