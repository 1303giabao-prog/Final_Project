//M4 J change start ---
// I added throws so the custom exception so it can be used when the customer is not found

import exceptions.CourseNotFoundException;

public interface Customer_DAO {
    Customer searchCustomer(int id) throws CustomerNotFoundException;
    void addCustomer(String name, String email, String phoneNum, Customer.Membership membership);
   
    void addRegistration(String name, String email, String phoneNum, int CourseId, Registration.Status status);
    void displayAllcustomers(); 
    void deleteCustomer(int targetId) throws CustomerNotFoundException;
 
    void showAllCourses();
    void showAllTrainers();
    void showAllRegistrations();
  
    void updateCoursesStatus(Course.courseStatus status, int Id) throws CourseNotFoundException;
    void updateRegistration(Registration.Status newStatus, String name) throws CustomerNotFoundException;
    void updateCustomer(int targetId, Customer.Membership newMem) throws CustomerNotFoundException;

    public Registration searchRegistration(String name)throws CustomerNotFoundException;
	public Course searchCourse(int Id) throws CourseNotFoundException;
}
	
//M4 J change end ---