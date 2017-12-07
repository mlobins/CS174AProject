
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
				generateDTER();
				break;
			case (4):
				System.out.println("Please enter username: ");
				String username2 = scanner.next();
				generateCustomerReport(username2);
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

		// Start connecting to database to retrieve list all market accounts or
		// customer profile to input run a and for each market account create a
		// transaction
		// into func insertTransactionAccrueInterest( int transaction_type,
		// String username, double accrue_interest)
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<MarketAccounts> marketAccounts = new ArrayList<MarketAccounts>();
		MarketAccounts marketAccount = null;
		String query2 = "SELECT * FROM MarketAccounts;";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query2);
			while (rs.next()) {
				marketAccount = new MarketAccounts();
				marketAccount.setUsername(rs.getString("username"));
				marketAccount.setAverageDailyBalance(rs.getDouble("averageDailyBalance"));
				marketAccounts.add(marketAccount);
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

		for (int i = 0; i < marketAccounts.size(); i++) {
			Communications.insertTransactionAccrueInterest(5, marketAccounts.get(i).getUsername(),
					(0.03 * marketAccounts.get(i).getAverageDailyBalance()));
		}

		// end of Transaction Accrue interest

		// also resets totalInterest = 0 for new month
		String query1 = "UPDATE MarketAccounts" + " SET averageDailyBalance  = 0.0 ; ";
		Communications.runQuery(query1);
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
			while (rs.next()) {
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

		System.out.println("---------------------------------------------------------------");
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println("Trans ID:            " + transactions.get(i).getTransID());
			System.out.println("username:            " + transactions.get(i).getUsername());
			System.out.println("Transactiontype:     " + transactions.get(i).getTransactionType());
			System.out.println("Stock ID :           " + transactions.get(i).getStockID());
			System.out.println("Stock Quantity:      " + transactions.get(i).getStockQuantity());
			switch (transactions.get(i).getTransactionTypeNumber()) {
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

			System.out.println("---------------------------------------------------------------");
		}
	}

	public static void generateActiveCustomers() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<CustomerProfile> customers = new ArrayList<CustomerProfile>();
		CustomerProfile customer = null;

		String query = "SELECT c.username, c.name,  SUM(t.stock_quantity) AS total_amount"
				+ " FROM CustomerProfile c INNER JOIN Transactions t " + " ON c.username = t.username "
				+ " GROUP BY c.username, c.name " + " HAVING SUM(t.stock_quantity) >= 1000; ";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				customer = new CustomerProfile();
				customer.setName(rs.getString("name"));
				customer.setUsername(rs.getString("username"));
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

		System.out.println("---------------------------------------------------------------");
		System.out.printf("Amount of active customers %d %n", customers.size());
		for (int i = 0; i < customers.size(); i++) {
			System.out.printf(" Name: %s	%n Username: %s%n%n", customers.get(i).getName(),
					customers.get(i).getUsername());
		}
		System.out.println("---------------------------------------------------------------");

	}

	public static void generateDTER() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<CustomerProfile> customers = new ArrayList<CustomerProfile>();
		CustomerProfile customer = null;

		String query = "SELECT c.username, c.name, c.state,  (SUM(t.selling_price) + SUM(t.accrue_interest)) AS total_amount"
				+ " FROM CustomerProfile c INNER JOIN Transactions t " + " ON c.username = t.username "
				+ " GROUP BY c.username, c.name "
				+ " HAVING (SUM(t.selling_price) + SUM(t.accrue_interest) >= 10000); ";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				customer = new CustomerProfile();

				customer.setName(rs.getString("name"));
				customer.setUsername(rs.getString("username"));
				customer.setState(rs.getString("state"));
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

		System.out.println("---------------------------------------------------------------");
		System.out.printf("Amount of Customers on DTER: %d %n", customers.size());
		for (int i = 0; i < customers.size(); i++) {
			System.out.printf("Username: %s %nState: %s  Name: %s  %n", customers.get(i).getUsername(),
					customers.get(i).getState(), customers.get(i).getName());
		}
		System.out.println("---------------------------------------------------------------");

	}

	public static void generateCustomerReport(String usernameEntered) {
		//1. Marketaccounts associated with username
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<MarketAccounts> marketAccounts = new ArrayList<MarketAccounts>();
		MarketAccounts marketAccount = null;

		String query = "SELECT * FROM MarketAccounts m WHERE m.username = '" + usernameEntered + "';";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				marketAccount = new MarketAccounts();
				marketAccount.setAccountMID(rs.getInt("account_mid"));
				marketAccount.setBalance(rs.getDouble("balance"));
				marketAccounts.add(marketAccount);
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
		System.out.printf("%n%nMarket Accounts for %s :%n", usernameEntered);
		for (int i = 0; i < marketAccounts.size(); i++) {
			System.out.printf("----------------------------------------------------------------------%n");
			System.out.printf("account_mid : %d  balance: %f%n",  marketAccounts.get(i).getAccountMID(), marketAccounts.get(i).getBalance());  
		}
		System.out.printf("-----------------------------------------------------------------------%n%n");
		
		// stockAccounts associated with username
		ResultSet rs1 = null;
		Connection connection1 = null;
		Statement statement1 = null;

		List<StockAccounts> stockAccounts = new ArrayList<StockAccounts>();
		StockAccounts stockAccount = null;

		String query1 = "SELECT * FROM StockAccounts m  WHERE m.username = '" + usernameEntered + "';";
		try {
			connection1 = JDBCMySQLConnection.getConnection();
			statement1 = connection1.createStatement();
			rs1 = statement1.executeQuery(query1);
			System.out.println(query1);
			while (rs1.next()) {
				stockAccount = new StockAccounts();
				stockAccount.setAccountSID(rs1.getInt("account_sid"));
				stockAccount.setStockID(rs1.getString("stock_id"));
				stockAccount.setBalance(rs1.getDouble("balance"));
				stockAccount.setBuyingPrice(rs1.getDouble("buying_price"));
				stockAccount.setSellingPrice(rs1.getDouble("selling_price"));
				stockAccounts.add(stockAccount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection1 != null) {
				try {
					connection1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.printf("%nStock Accounts:%n");
		for (int i = 0; i < stockAccounts.size(); i++) {
			System.out.println("----------------------------------------------------------------------");
			System.out.printf("account_sid : %d%n stock_id = %s%n balance = %f%n Selling Price = %f%n Buying Price = %f%n "
					, stockAccounts.get(i).getAccountSID(), stockAccounts.get(i).getStockID(), stockAccounts.get(i).getBalance(),
					stockAccounts.get(i).getBuyingPrice(), stockAccounts.get(i).getSellingPrice());  
			System.out.println("----------------------------------------------------------------------");
			
		}

	}

	public static void deleteTransactions(int username) {
		String query = "DELETE FROM Transactions;";
		Communications.runQuery(query);
	}

}