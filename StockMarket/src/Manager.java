import java.util.Scanner;

public class Manager {
	// manager interface
	public static void manager() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Add Interest (0)\n" + "Generate Monthly Statement (1)\n"
					+ "Generate List of Active Customers (2)\n" + "Generate Government Drug & Tax Evasion Report (3)\n"
					+ "Generate Customer Report (4)\n" + "Delete Transactions (5)\n" + "Exit (6)\n");
			int choice = scanner.nextInt();
			if (choice == 0) {
				addInterest(0);
			} else if (choice == 1) {
				generateMonthlyStatement(0);
			} else if (choice == 2) {
				generateActiveCustomers(0);
			} else if (choice == 3) {
				generateDTER(0);
			} else if (choice == 4) {
				generateCustomerReport(0);
			} else if (choice == 5) {
				deleteTransactions(0);
			} else if (choice == 6) {
				control = 0;
			}
		}
	}

	public static void addInterest(int username) {
		System.out.println("HOY");
	}

	public static void generateMonthlyStatement(int username) {
		System.out.println("HOY");
	}

	public static void generateActiveCustomers(int username) {
		System.out.println("HOY");
	}

	public static void generateDTER(int username) {
		System.out.println("HOY");
	}

	public static void generateCustomerReport(int username) {
		System.out.println("HOY");
	}

	public static void deleteTransactions(int username) {
		System.out.println("HOY");
	}

}