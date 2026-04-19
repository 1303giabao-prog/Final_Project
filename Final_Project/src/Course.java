

public class Course {
	public enum courseStatus {
	   FULL,
	   AVAILABLE
	}
	private int id;
    private String courseName;
    private int session;      // Number of sessions (e.g., 10 sessions)
    private double cost;      // Cost of the course
    private String ptName;    // The Personal Trainer's name
    private courseStatus status;
   

    // Constructor
    public Course(int id, String courseName, int session, double cost, String ptName, courseStatus status) {
    	this.id = id;
        this.courseName = courseName;
        this.session = session;
        this.cost = cost;
        this.ptName = ptName;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {    return id; }

 
    public void setId(int id) {  this.id = id;}
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getSession() { return session; }
    public void setSession(int session) { this.session = session; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public String getPtName() { return ptName; }
    public void setPtName(String ptName) { this.ptName = ptName; }
    public courseStatus getStatus() {return status; }

    public void setStatus(courseStatus status) { this.status = status; }

    // Display Course Details
    @Override
    public String toString() {
        return String.format("Course: %-15s | Sessions: %-2d | Cost: $%-6.2f | Trainer: %s", 
                             courseName, session, cost, ptName);
    }
}