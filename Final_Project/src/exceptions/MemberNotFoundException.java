package exceptions;
//This exception is for when the program cannot find the member you are looking for
public class MemberNotFoundException extends Exception {
	public MemberNotFoundException(String message) {
		super(message);
	}
}