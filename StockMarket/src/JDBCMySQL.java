
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//import com.theopentutorials.jdbc.db.DbUtil;
//import com.theopentutorials.jdbc.db.JDBCMySQLConnection;
//import com.theopentutorials.jdbc.to.Employee;

//toDo
//add time
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
					System.out.printf("Success!");
					connection.close();
				} catch (SQLException e) {
					System.out.printf("Exception!");
					e.printStackTrace();
				}
			}
		}
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Customer (0) or Manager (1)? Exit(2) Debug (3)");
			int choice = scanner.nextInt();
			switch (choice) {
			case (0):
				Trader.trader();
				break;
			case (1):
				Manager.manager();
				break;
			case (2):
				System.exit(0);
				break;
			case (3):
				Debug.debugInit();
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
		scanner.close();
	}
}
