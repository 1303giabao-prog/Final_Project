public class Customer extends Person {

    // Enum for membership types
    public enum Membership {
        NONE, BASIC, PREMIUM
    }

    private Membership membership;

    // Constructor
    public Customer(String email, String name, String phoneNum, Membership membership) {
        // 'super' passes the data up to the parent Person class
        super(name, email, phoneNum); 
        this.membership = membership;
    }

    // Getters and Setters for the specific Customer traits
    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    // toString method
    @Override
    public String toString() {
        // We use \n to move the text to the next line for a cleaner look
        return "Customer's information\n" +
               "email: " + getEmail() + "\n" +
               "name: " + getName() + "\n" +
               "phoneNum: " + getPhoneNum() + "\n" +
               "membership: " + membership;
    }
}