package exceptions;
//This exception is for when the program cannot find the course you are looking for
public class CourseNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);
	}
}