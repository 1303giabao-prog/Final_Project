package exceptions;
//We have this exception for when the user picks a menu option that does not exist 
public class InvalidMenuChoiceException extends Exception {
	public InvalidMenuChoiceException(String message) {
		super(message);
	}
}