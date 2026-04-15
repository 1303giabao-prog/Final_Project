// --- Member 4: J change ---
// Here guys, this exception is for when the membership entered is not valid
public class InvalidMembershipException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMembershipException(String message) {
		super(message);
	}
}