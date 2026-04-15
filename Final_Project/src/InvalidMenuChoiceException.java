// --- M 4: J change ---
// This exception is for when the user picks a menu option that does not exist
public class InvalidMenuChoiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMenuChoiceException(String message) {
		super(message);
	}
}