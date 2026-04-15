public interface Customer_DAO {
    Customer searchCustomer(int id);
    void addCustomer(String name, String email, String phoneNum, Customer.Membership membership);
    void displayAllcustomers(); 
    void updateCustomer(int targetId, Customer.Membership newMem);
    void deleteCustomer(int targetId);
}