package skeleton;

import exceptions.CourseNotFoundException;
import exceptions.DatabaseConnectionException;
import exceptions.InvalidCourseStatusException;
import exceptions.InvalidMembershipException;
import exceptions.InvalidMenuChoiceException;
import exceptions.InvalidStatusException;
import exceptions.MemberNotFoundException;

public class ExceptionUsageGuide {

	// This is where InvalidMenuChoiceException would go in the menu part
	public void validateMenuChoice(int choice) throws InvalidMenuChoiceException {
		if (choice < 1 || choice > 4) {
			throw new InvalidMenuChoiceException("Please choose a valid menu option");
		}
	}

	// This is where InvalidMembershipException would go when checking membership type
	public void validateMembership(String membershipType) throws InvalidMembershipException {
		if (!membershipType.equalsIgnoreCase("Basic")
				&& !membershipType.equalsIgnoreCase("Premium")
				&& !membershipType.equalsIgnoreCase("None")) {
			throw new InvalidMembershipException("Invalid membership type entered");
		}
	}
	public void validateStatus(String statusType) throws InvalidStatusException {
		if (!statusType.equalsIgnoreCase("Freeze")
				&& !statusType.equalsIgnoreCase("Cancelled")
				&& !statusType.equalsIgnoreCase("Active")
			&& !statusType.equalsIgnoreCase("") 
			&& !statusType.equalsIgnoreCase("Pending"))
		        {
			throw new InvalidStatusException("Invalid status type entered");
		}
	}
	
	
	public void validateCourseStatus(String statusType) throws InvalidCourseStatusException {
		if (!statusType.equalsIgnoreCase("Full")
				&& !statusType.equalsIgnoreCase("Available"))
			
		        {
			throw new InvalidCourseStatusException("Invalid course status type entered");
		}
	}

	// This is where MemberNotFoundException would go when a member ID is not found
	public void findMember(int memberId) throws MemberNotFoundException {
		if (memberId <= 0) {
			throw new MemberNotFoundException("Member was not found in the system");
		}
	}
	// Fixed findCourse method
	public void findCourse(int courseId) throws CourseNotFoundException {
	    // 1. Check for invalid input (ID cannot be 0 or negative)
	    if (courseId <= 0) {
	        throw new CourseNotFoundException("Invalid ID format: " + courseId);
	    }
	    
	    // Note: In your actual DAO/Database class, you would throw this 
	    // if rs.next() returns false after a SELECT query.
	}

	// This is where DatabaseConnectionException would go if the database fails to connect
	public void connectToDatabase(boolean connected) throws DatabaseConnectionException {
		if (!connected) {
			throw new DatabaseConnectionException("Failed to connect to the database");
		}
	}
}