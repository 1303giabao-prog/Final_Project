// --- M 4: J change ---
// This exception is for when something fails while connecting to the database
public class DatabaseConnectionException extends Exception {
	public DatabaseConnectionException(String message) {
		super(message);
	}
}