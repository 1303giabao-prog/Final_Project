package skeleton;
// This is just a basic test to see if it works
import exceptions.DatabaseConnectionException;
import exceptions.InvalidMembershipException;
import exceptions.InvalidMenuChoiceException;
import exceptions.MemberNotFoundException;

public class ExceptionDemo {

	public static void main(String[] args) {
		ExceptionUsageGuide guide = new ExceptionUsageGuide();

		try {
			guide.validateMenuChoice(7);
		} catch (InvalidMenuChoiceException e) {
			System.out.println("Menu Error: " + e.getMessage());
		}

		try {
			guide.validateMembership("GoldPlus");
		} catch (InvalidMembershipException e) {
			System.out.println("Membership Error: " + e.getMessage());
		}

		try {
			guide.findMember(0);
		} catch (MemberNotFoundException e) {
			System.out.println("Member Error: " + e.getMessage());
		}

		try {
			guide.connectToDatabase(false);
		} catch (DatabaseConnectionException e) {
			System.out.println("Database Error: " + e.getMessage());
		}
	}
}