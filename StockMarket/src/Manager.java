
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
				String username = scanner.next();
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
		// manager should know its the end of the month
		String query = "UPDATE MarketAccounts" + " SET balance = balance + (averageDailyBalance * 0.03 ) ;";
		Communications.runQuery(query);
		
		//Start connecting to database to retrieve list all market accounts or customer profile to input run a and for each market account create a transaction
		//into func insertTransactionAccrueInterest( int transaction_type, String username, double accrue_interest)
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		
		
		
		
		// also resets totalInterest = 0 for new month
		String query1 = "UPDATE MarketAccounts" + " SET averageDailyBalance  = 0.0 ; ";
		Communications.runQuery(query1);
		// "UPDATE MarketAccounts" + "SET balance = " + deposit + "\n" + "WHERE
		// username = '" + username
		// + "' ;";"
	}

	public static void generateMonthlyStatement(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = null;
		String query = "SELECT * FROM Transactions" + " WHERE " + "username = '" + username + "';";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				transaction = new Transaction();
				transaction.setTransID(rs.getInt("transid"));
				transaction.setUsername(rs.getString("username"));
				transaction.setTransactionType(rs.getInt("transaction_type"));
				transaction.setStockID(rs.getString("stock_id"));
				transaction.setStockQuantity(rs.getDouble("stock_quantity"));
				transaction.setBuyingPrice(rs.getDouble("buying_price"));
				transaction.setSellingPrice(rs.getDouble("selling_price"));
				transaction.setDeposit(rs.getDouble("deposit"));
				transaction.setWithdraw(rs.getDouble("withdraw"));
				transaction.setAccrueInterest(rs.getDouble("accrue_interest"));
				transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));	
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		System.out.println("----------------------------");
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println("Trans ID:            " + transactions.get(i).getTransID());
			System.out.println("username:            "  + transactions.get(i).getUsername());
			System.out.println("Transactiontype:     " + transactions.get(i).getTransactionType());
			System.out.println("Stock ID :           " + transactions.get(i).getStockID());
			System.out.println("Stock Quantity:      " + transactions.get(i).getStockQuantity());
			switch(transactions.get(i).getTransactionTypeNumber()){
			case 1:			
				System.out.println("Buying Price:        " + transactions.get(i).getBuyingPrice());
				break;
			case 2:
				System.out.println("Selling Price:       " + transactions.get(i).getSellingPrice());
				break;
			case 3:
				System.out.println("WithDraw:            " + transactions.get(i).getWithdraw());
				break;
			case 4:
				System.out.println("Deposit:             " + transactions.get(i).getDeposit());
				break;
			case 5:
				System.out.println("Accrue Interest:     " + transactions.get(i).getAccrueInterest());
				break;
			default:
				System.out.print("");	
			}

			
			System.out.println("Date Of Transaction: " + transactions.get(i).getDateOfTransaction());

			System.out.println("----------------------------");
		}
	}

	public static void generateActiveCustomers() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<CustomerProfile> customers = new ArrayList<CustomerProfile>();
		CustomerProfile customer = null;
		//String query = "SELECT * " + "FROM CustomerProfile c, Transactions t "
			//+ "WHERE  t.username = c.username " + "HAVING SUM(t.stock_quantity) >= 1000;";
		//String query = "SELECT * " + "FROM CustomerProfile c, Transactions t " + "WHERE  SUM(t.stock_quantity) >= 1000 " + "AND t.username = c.username; ";
		//String query = "SELECT SUM(t.stock_quantity) >= 1000, c.username FROM CustomerProfile c, Transactions t WHERE t.username = c.username; ";
		String query = "SELECT c.username, c.name,  SUM(t.stock_quantity) AS total_amount" +
					" FROM CustomerProfile c INNER JOIN Transactions t " +
					" ON c.username = t.username " +
					" GROUP BY c.username, c.name " +
					" HAVING SUM(t.stock_quantity) >= 1000; "; 
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			System.out.println(query);
			while(rs.next()){
				customer = new CustomerProfile();
				customer.setName(rs.getString("name"));	
				customer.setUsername(rs.getString("username"));

				//customer.setState(rs.getString("state"));
				//customer.setPhoneNumber(rs.getString("phone_number"));
				//customer.setEmailAddress(rs.getString("email_address"));
				//customer.setTaxID(rs.getString("taxid"));
				//customer.setPassword(rs.getString("password"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("----------------------------");
		System.out.printf("Amount of active customers %d %n" , customers.size());
		for (int i = 0; i < customers.size(); i++) {
			System.out.println("Name:   " + customers.get(i).getName());
		}
		System.out.println("----------------------------");

		

	}

	public static void generateDTER(int username) {
		/*
		 * String query = " SELECT c.username " +
		 * "FROM CustomerProfile c, Transactions t " +
		 * "WHERE t.username = c.username " + "AND SUM" /////
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