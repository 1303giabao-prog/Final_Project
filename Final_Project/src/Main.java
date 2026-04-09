import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	Database_Connectivity.connect();

        Scanner sc = new Scanner(System.in);
        int choice;
//Tools to manage the gym system. Choose to modify/view/update/delete data
        do {
            System.out.println("\n---  Management System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer Email");
            System.out.println("4. Delete Customer");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("First Name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Membership: ");
                    String mem = sc.nextLine().toUpperCase(); // convert to uppercase
                    Customer.Membership membership = Customer.Membership.valueOf(mem);

                    Customer c = new Customer(email, fn, ln, phone, membership);

                 
                    break;

                case 2:
                 
                    break;

                case 3:
                    System.out.print("Customer ID: ");
                    int idU = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();
                    
                    break;

                case 4:
                    System.out.print("Customer ID: ");
                    int idD = sc.nextInt();
                 
                    break;
            }

        } while (choice != 0);

        Database_Connectivity.disconnect();
        sc.close();
    }
}
