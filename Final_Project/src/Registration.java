
// Inheritance: Registration now has name, email, and phoneNum from Person
public class Registration extends Person {
    private String status; // e.g., "Active", "Pending", "Confirmed"

    // Constructor
    public Registration(String name, String email, String phoneNum, String status) {
        // 'super' sends the person details to the Person constructor
        super(name, email, phoneNum);
        this.status = status;
    }

    // Getter and Setter for the specific attribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method utilizing the parent's getName() method
    @Override
    public String toString() {
        return String.format("Registration [%s] for Customer: %s (Email: %s)", 
                             status, getName(), getEmail());
    }
}