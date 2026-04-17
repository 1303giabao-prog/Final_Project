//M4 J change start ---
// I added throws so the custom exception so it can be used when the customer is not found
public interface Customer_DAO {
    Customer searchCustomer(int id) throws CustomerNotFoundException;
    void addCustomer(String name, String email, String phoneNum, Customer.Membership membership);
    void displayAllcustomers(); 
    void updateCustomer(int targetId, Customer.Membership newMem) throws CustomerNotFoundException;
    void deleteCustomer(int targetId) throws CustomerNotFoundException;
}
//M4 J change end ---