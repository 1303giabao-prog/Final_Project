// --- M 4: J change ---
// This exception is for when the program cannot find the customer
public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException(String message) {
		super(message);
	}
}