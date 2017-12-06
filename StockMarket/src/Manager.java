
import java.util.Scanner;

public class Manager {
	// manager interface
	public static void manager() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nAdd Interest (0)\n" + "Generate Monthly Statement (1)\n"
					+ "Generate List of Active Customers (2)\n" + "Generate Government Drug & Tax Evasion Report (3)\n"
					+ "Generate Customer Report (4)\n" + "Delete Transactions (5)\n" + "Exit (6)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case (0):
				addInterest();
				break;
			case (1):
				System.out.println("Please enter username:");
				String username = scanner.nextLine();
				generateMonthlyStatement(username);
				break;
			case (2):
				generateActiveCustomers();
				break;
			case (3):
				generateDTER(0);
				break;
			case (4):
				generateCustomerReport();
				break;
			case (5):
				deleteTransactions(0);
				break;
			case (6):
				control = 0;
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	public static void addInterest() {
		//manager should know its the end of the month
		String query = "UPDATE MarketAccounts" + " SET balance = balance + (averageDailyBalance * 0.03 ) ;" ;
		Communications.runQuery(query);
		
		// also resets totalInterest = 0  for new month
		String query1 = "UPDATE MarketAccounts" + " SET averageDailyBalance  = 0.0 ; ";
		Communications.runQuery(query1);
				//"UPDATE MarketAccounts" + "SET  balance = " + deposit + "\n" + "WHERE username = '" + username
				//+ "' ;";"
	}

	public static void generateMonthlyStatement(String username) {
		// this works as long as all transactions delete from month to month
		String query = "SELECT * FROM Transactions" + " WHERE " + "username = " + username + ";";
		Communications.runQuery(query);
	}

	public static void generateActiveCustomers() {
		String query = "SELECT c.username " + "FROM CustomerProfile c, Transactions t "
				+ "WHERE  t.username = c.username " + "AND SUM(t.stockCount)  >= 1000;";
		Communications.runQuery(query);

	}

	public static void generateDTER(int username) {
		/*
		 * String query = " SELECT c.username " +
		 * "FROM CustomerProfile c, Transactions t " + "WHERE t.username = c.username "
		 * + "AND SUM" /////
		 */
	}

	public static void generateCustomerReport() {
		String query = "SELECT c.username, m.balance " + "FROM CustomerProfile c, MarketAccounts m "
				+ "WHERE c.username = m.username";
		Communications.runQuery(query);
	}

	public static void deleteTransactions(int username) {
		String query = "DROP FROM TABLE Transactions;";
		Communications.runQuery(query);
	}

}