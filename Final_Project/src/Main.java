import java.util.Scanner;

import exceptions.CourseNotFoundException;
import exceptions.InvalidCourseStatusException;
import exceptions.InvalidStatusException;

public class Main {
	
//VALIDATION METHOD
	public static boolean isValidPhone(String phone) {
	    // This pattern allows:
	    // 1234567890 (10 digits) 
	    // OR 123-456-7890 (with hyphens)
	    String phoneRegex = "^(\\d{10}|\\d{3}-\\d{3}-\\d{4})$";
	    
	    return phone.matches(phoneRegex);
	}
	  
	// --- Helper Method to Validate Email Format ---
	public static boolean isValidEmail(String email) {
	    // This pattern forces the format: [anything] @ gmail . com
	    // The \\. is used because a single dot in regex means "any character"
	    String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
	    
	    return email.matches(emailRegex.toLowerCase());
	}

    // M4 change start ---
    // Helper method to validate menu choice
    public static void validateMenuChoice(int choice) throws InvalidMenuChoiceException {
        if (choice < 0 || choice > 10) {
            throw new InvalidMenuChoiceException("Please choose a valid menu option from 0 to 10");
        }
    }

    // Helper method to validate membership input
    public static Customer.Membership validateMembershipInput(String mem) throws InvalidMembershipException {
        try {
            return Customer.Membership.valueOf(mem.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidMembershipException("Invalid membership entered");
        }
    }
 // Helper method to validate status input
    public static Registration.Status validateStatusInput(String sta) throws InvalidStatusException {
        try {
            return Registration.Status.valueOf(sta.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status entered");
        }
    }
   
    
    public static Course.courseStatus validateCourseStatusInput(String status) throws InvalidCourseStatusException {
        try {
            // .trim() removes extra spaces at the ends
            // .replace(" ", "_") turns "NOT FULL" into "NOT_FULL"
            String fixedInput = status.trim().toUpperCase();
            return Course.courseStatus.valueOf(fixedInput);
        } catch (IllegalArgumentException e) {
            throw new InvalidCourseStatusException("Invalid course status entered. Use FULL or AVAILABLE.");
        }
    }

    public static void main(String[] args)  {
    	Customer_DAO db = new CRUD_Operation();
    	// M4 change start ---
    	try {
    		Database_Connectivity.connect();
    	} catch (DatabaseConnectionException e) {
    		System.out.println("Database Error: " + e.getMessage());
    		return;
    	}
    	// M4 change end ---
    	
    	
    	
    	
    	

   //VALIDATION METHOD
    

        Scanner sc = new Scanner(System.in);
        // M4 change start ---
        int choice = -1;
        // M4 change end ---
        
//Tools to manage the gym system. Choose to modify/view/update/delete data
        do {
        	// M4 change start ---
        	try {
        	// M4 change end ---
            System.out.println("\n---  Management System ---");
            System.out.println("1. Add customer");
            System.out.println("2. Delete customer");
            System.out.println("3. Update customer's information");
            System.out.println("4. View all the customers");
            System.out.println("5. View all the courses");
            System.out.println("6. View all the personal trainers");
            System.out.println("7. View all the registrations");
            System.out.println("8. Add new registration");
            System.out.println("9. Update registration's status");
            System.out.println("10. Update course's status");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            // M4 change start--
            validateMenuChoice(choice);
            //M4 change end ---

            switch (choice) {
            case 1:
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine().toLowerCase();
                
                // Check If the email WAS good. If good, continue.
                if (!isValidEmail(email)) {
                    System.out.println("ERROR: Invalid email format. (e.g., name@gmail.com)");
                    System.out.println("Returning to main menu...");
                    
                    // 2. This instantly ejects them from 'case 1'. 
                    // It skips all the code below and restarts your main menu!
                    break; 
                }
           
              
                //Check if phone number is valid, if it is, continue.
                System.out.print("Phone number: ");
                String phone = sc.nextLine();
                if (!isValidPhone(phone)) {
                	 System.out.println("ERROR: Invalid phone number. (e.g.,012-345-6789)");
                     System.out.println("Returning to main menu...");
                     
                     break;
                }
                	
                
                System.out.print("Membership (NONE, BASIC, PREMIUM): ");
                String mem = sc.nextLine().toUpperCase();
                
                //  M4 change start ---
                Customer.Membership membership = validateMembershipInput(mem);
             // M4 change end ---
                
                // 4. Finally, it implements the code to save to the database
                db.addCustomer(name, email, phone, membership);
                break;
                 

              
                    

                case 2:
                	System.out.print("Enter Customer ID to Delete: ");
                	int id = sc.nextInt();
                	sc.nextLine(); // Clear the buffer

                	// 1. Capture the customer so we can check if they exist
                	Customer found = db.searchCustomer(id);

                	// 2. Only ask for confirmation if the customer was actually found
                	if (found != null) {
                	    System.out.print("\n Are you sure you want to delete this customer? (yes/no): ");
                	    String confirm = sc.nextLine().toLowerCase();

                	    if (confirm.equals("yes")) {
                	        db.deleteCustomer(id); 
                	      
                	    } else {
                	        System.out.println("Operation cancelled. Returning to main menu...");
                	    }
                	} else {
                	    // If searchCustomer returned null, it already printed "Not Found", 
                	    // so we just jump back to the menu.
                	}
                	break; // Exit the case
                	   
                  
                   // UPDATE membership status
                case 3:
                    System.out.print("Customer ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine(); // Clear buffer

                    // 1. Capture the customer object so we can see their CURRENT status
                    Customer currentCustomer = db.searchCustomer(sid);

                    if (currentCustomer != null) {
                        System.out.println("Update membership status (NONE, BASIC, PREMIUM):");
                        String memStatus = sc.nextLine().toUpperCase();

                        try {
                            // 2. Validate the string input into an Enum
                            Customer.Membership newMembership = validateMembershipInput(memStatus);

                            // 3. Check if the new status is the same as the current one
                            if (currentCustomer.getMembership() == newMembership) {
                                // Manually throw your custom exception if nothing is actually changing
                                throw new InvalidMembershipException("Error: Customer is already a " + newMembership + " member.");
                            }

                            // 4. Only update if the status is actually different
                            db.updateCustomer(sid, newMembership);

                        } catch (InvalidMembershipException e) {
                            // This will be caught by your catch block at the bottom of the switch!
                            System.out.println("Membership Error: " + e.getMessage());
                        }
                    }
                    break;
                case 4:
                	// DISPLAY ALL CUSTOMERS
                	db.displayAllcustomers();
                	
                	break;
                case 5:
                	//DISPLAY ALL COURSES
                	db.showAllCourses();
                	break;
                case 6:
                	//DISPLAY ALL TRAINERS ( STAFFS)
                	db.showAllTrainers();
                	break;
                case 7:
                	//DISPLAY ALL CUSTOMER REGISTRATION
                	db.showAllRegistrations();
                	break;
                case 8:// ADD NEW REGIS
                    System.out.print("Name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Email: ");
                    String email1 = sc.nextLine().toLowerCase();
                    
                    // Check If the email WAS good. If good, continue.
                    if (!isValidEmail(email1)) {
                        System.out.println("ERROR: Invalid email format. (e.g., name@gmail.com)");
                        System.out.println("Returning to main menu...");
                        
                        // 2. This instantly ejects them from 'case 1'. 
                        // It skips all the code below and restarts your main menu!
                        break; 
                    }
               
                  
                    //Check if phone number is valid, if it is, continue.
                    System.out.print("Phone number: ");
                    String phone1 = sc.nextLine();
                    if (!isValidPhone(phone1)) {
                    	 System.out.println("ERROR: Invalid phone number. (e.g.,012-345-6789)");
                         System.out.println("Returning to main menu...");
                         
                         break;
                    }
                    System.out.print("course ID: ");
                    int CourseId = sc.nextInt();
                    sc.nextLine();
                    	
                    
                    System.out.print("Status (ACTIVE, FREEZE, CANCELLED, END, PENDING): ");
                    String sta = sc.nextLine().toUpperCase();// to make the convert to consistent format (uppercase)
                    
                  //to validate the status
                    Registration.Status status = validateStatusInput(sta);
                   
              
                    
                    // 4. Finally, it implements the code to save to the database
                    db.addRegistration(name1, email1, phone1, CourseId, status);
                    break;
                case 9://UPDATE REGIS STATUS
                    System.out.print("Customer's name: ");
                    String Name = sc.nextLine();
                  // Clear buffer

                    // 1. Capture the customer object so we can see their CURRENT status
                    Registration Customer = db.searchRegistration(Name);

                    if (Customer != null) {
                        System.out.println("Update course status (ACTIVE, PENDING, CANCELLED, END, FREEZE):");
                        String regStatus = sc.nextLine().toUpperCase();

                        try {
                            // 2. Validate the string input into an Enum
                            Registration.Status newStatus = validateStatusInput(regStatus);

                            // 3. Check if the new status is the same as the current one
                            if (Customer.getStatus() == newStatus) {
                                // Manually throw your custom exception if nothing is actually changing
                                throw new InvalidStatusException("Error: Customer is already a " + newStatus + " member.");
                            }

                            // 4. Only update if the status is actually different
                            db.updateRegistration(newStatus, Name);

                        } catch (InvalidStatusException e) {
                            // This will be caught by your catch block at the bottom of the switch!
                            System.out.println("Status Error: " + e.getMessage());
                        }
                    }
                    break;
                case 10://UPDATE COURSE STATUS
                    System.out.print("Course ID: ");
                    int courseid = sc.nextInt();
                    sc.nextLine(); // Clear buffer

                    // 1. Capture the customer object so we can see their CURRENT status
                    Course course = db.searchCourse(courseid);

                    if (course != null) {
                        System.out.println("Update course status (FULL, AVAILABLE):");
                        String courseStatus = sc.nextLine().toUpperCase();

                        try {
                            // 2. Validate the string input into an Enum
                            Course.courseStatus newSta = validateCourseStatusInput(courseStatus);

                            // 3. Check if the new status is the same as the current one
                            if (course.getStatus() == newSta) {
                                // Manually throw your custom exception if nothing is actually changing
                                throw new InvalidCourseStatusException("Error: Course is already a " + newSta + ".");
                            }

                            // 4. Only update if the status is actually different
                            db.updateCoursesStatus(newSta, courseid);

                        } catch (InvalidCourseStatusException e) {
                            // This will be caught by your catch block at the bottom of the switch!
                            System.out.println("Course status Error: " + e.getMessage());
                        }
                    }
                    
                	

            }
           
            
            // M4 change start ---
        	} catch (InvalidMenuChoiceException e) {
        		System.out.println("Menu Error: " + e.getMessage());
        	} catch (InvalidMembershipException e) {
        		System.out.println("Membership Error: " + e.getMessage());
        	} catch (CustomerNotFoundException e) {
        		System.out.println("Customer Error: " + e.getMessage());
        	}catch(InvalidStatusException e) {
        		System.out.println("Status Error:"+ e.getMessage());
        	}catch (CourseNotFoundException e) {
        		System.out.println("Status Error:"+ e.getMessage());
        	}
        	// M4 change end ---

        } while (choice != 0);

        Database_Connectivity.disconnect();
        sc.close();
    }
}