// --- Member 4: J change ---
// Here guys, this exception is for when the membership entered is not valid
public class InvalidMembershipException extends Exception {
	public InvalidMembershipException(String message) {
		super(message);
	}
}