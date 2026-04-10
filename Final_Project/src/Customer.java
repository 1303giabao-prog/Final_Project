public class Customer {

    // Enum for membership types
    public enum Membership {
        NONE, BASIC, PREMIUM
    }

    private String email;
    private String name;
    private String phoneNum;
    private Membership membership;

    // Constructor
    public Customer(String email, String name, String phoneNum, Membership membership) {
        this.email = email;
        this.name= name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", membership=" + membership +
                '}';
    }
}
