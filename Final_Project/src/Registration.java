
// Inheritance: Registration now has name, email, and phoneNum from Person



public class Registration extends Person {

	public enum Status {
	    ACTIVE,    // Currently attending
	    FREEZE,    // On a break (no time)
	    CANCELLED, // No longer a member
	    END   ,    // No longer a member
	    PENDING    // NOT START THE COURSE YET
	}
	private Status status;
	private int CourseId;
	private int registration_date;

    // Constructor
    public Registration(String name, String email, String phoneNum, Status status, int CourseId, int registration_date) {
        // 'super' sends the person details to the Person constructor
        super(name, email, phoneNum);
        this.status = status;
        this.CourseId = CourseId;
        this.registration_date = registration_date;
    }

    // Getter and Setter for the specific attribute
    public int getDate() {  return registration_date; }

    public void setDate( int registration_date) {  this.registration_date = registration_date;  }
    public Status getStatus() {  return status; }

    public void setStatus(Status status) {  this.status = status;  }

   

    public int getCourseId() { return CourseId; }

   
    public void setCourseId(int CourseId) { this.CourseId = CourseId; }

    // toString method for output format
    @Override
    public String toString() {
        return String.format("Status: %s | Course ID: %d | Customer: %s | Email: %s|", 
                             status, CourseId, getName(), getEmail());
    }
}