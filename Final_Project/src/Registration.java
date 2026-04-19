
// Inheritance: Registration now has name, email, and phoneNum from Person



public class Registration extends Person {

	public enum Status {
	    ACTIVE,    // Currently attending
	    FREEZE,    // On a break (no time)
	    CANCELLED, // No longer a member
	    END   ,    // No longer a member
	    PENDING
	}
	private Status status;
	private int CourseId;
  

    // Constructor
    public Registration(String name, String email, String phoneNum, Status status, int CourseId) {
        // 'super' sends the person details to the Person constructor
        super(name, email, phoneNum);
        this.status = status;
        this.CourseId = CourseId;
    }

    // Getter and Setter for the specific attribute
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

   
 // Getter for CourseId
    public int getCourseId() {
        return CourseId;
    }

    // Setter for CourseId
    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    // toString method utilizing the parent's getName() method
    @Override
    public String toString() {
        return String.format("Registration [%s] | Course ID: %d | Customer: %s (Email: %s)", 
                             status, CourseId, getName(), getEmail());
    }
}