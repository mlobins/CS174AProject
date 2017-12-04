
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//import com.theopentutorials.jdbc.db.DbUtil;
//import com.theopentutorials.jdbc.db.JDBCMySQLConnection;
//import com.theopentutorials.jdbc.to.Employee;

//toDo
//create the tables in the database
//test adding to database
//time
//Transaction functions (accrue_interest)
//manager
//trader (showtransactionhistory)
//what happens if you buy 20 shares for $10 and then 10 shares for $20?
//review sql queries
//recover from exceptions

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
		}
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nCustomer (0)\n" + "Manager (1)\n" + "Debug (2)\n" + "Exit (3)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case (0):
				Trader.trader();
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
		scanner.close();
	}
}
