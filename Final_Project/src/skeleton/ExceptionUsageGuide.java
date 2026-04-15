package skeleton;

import exceptions.DatabaseConnectionException;
import exceptions.InvalidMembershipException;
import exceptions.InvalidMenuChoiceException;
import exceptions.MemberNotFoundException;

public class ExceptionUsageGuide {

	// This is where InvalidMenuChoiceException would go in the menu part
	public void validateMenuChoice(int choice) throws InvalidMenuChoiceException {
		if (choice < 1 || choice > 4) {
			throw new InvalidMenuChoiceException("Please choose a valid menu option");
		}
	}

	// This is where InvalidMembershipException would go when checking membership type
	public void validateMembership(String membershipType) throws InvalidMembershipException {
		if (!membershipType.equalsIgnoreCase("Basic")
				&& !membershipType.equalsIgnoreCase("Premium")
				&& !membershipType.equalsIgnoreCase("VIP")) {
			throw new InvalidMembershipException("Invalid membership type entered");
		}
	}

	// This is where MemberNotFoundException would go when a member ID is not found
	public void findMember(int memberId) throws MemberNotFoundException {
		if (memberId <= 0) {
			throw new MemberNotFoundException("Member was not found in the system");
		}
	}

	// This is where DatabaseConnectionException would go if the database fails to connect
	public void connectToDatabase(boolean connected) throws DatabaseConnectionException {
		if (!connected) {
			throw new DatabaseConnectionException("Failed to connect to the database");
		}
	}
}