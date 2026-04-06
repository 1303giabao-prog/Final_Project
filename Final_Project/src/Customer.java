public class Customer {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String membership;

    // Constructor
    public Customer(String email, String firstName, String lastName, String phoneNum, String membership) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.membership = membership;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", membership='" + membership + '\'' +
                '}';
    }
}
