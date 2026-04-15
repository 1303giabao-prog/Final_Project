package exceptions;
//Here guys, this exception is for when the membership type entered is not valid
public class InvalidMembershipException extends Exception {
	public InvalidMembershipException(String message) {
		super(message);
	}
}
