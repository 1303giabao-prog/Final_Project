// --- M 4: J change ---
// This exception is for when something fails while connecting to the database
public class DatabaseConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException(String message) {
		super(message);
	}
}