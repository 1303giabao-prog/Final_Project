// --- M 4: J change ---
// This exception is for when the program cannot find the customer
public class CustomerNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}