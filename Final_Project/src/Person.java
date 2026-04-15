public abstract class Person {
    private String name;
    private String email;
    private String phoneNum;

    // Constructor
    public Person(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}