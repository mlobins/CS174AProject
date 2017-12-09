
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//toDo
//if stock account balance goes to 0 delete it (add constraint)
//add foreign key constraints
//report 

public class JDBCMySQL {
	public static void main(String[] args) {
		// test connection
		Connection connection = null;
		try {
			connection = JDBCMySQLConnection.getConnection();

		} finally {
			if (connection != null) {
				try {
					System.out.printf("Connection Successful.");
					connection.close();
				} catch (SQLException e) {
					System.out.printf("Connection Failed.");
					e.printStackTrace();
				}
			}
			/*
			 * try { Globals.initializeDate(); } catch (ParseException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			int control = 1;
			Scanner scanner = new Scanner(System.in);
			MarketControl.setTodaysDate();

			while (control == 1) {
				System.out.println("\nCustomer (0)\n" + "Manager (1)\n" + "Debug (2)\n" + "Exit (3)\n");
				int choice = scanner.nextInt();
				switch (choice) {
				case (0):
					Trader.traderInit();
					break;
				case (1):
					Manager.manager();
					break;
				case (2):
					Debug.debugInit();
					break;
				case (3):
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Choice: ");
					break;
				}
			}
		}
	}
}