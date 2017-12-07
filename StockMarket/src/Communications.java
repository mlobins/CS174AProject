import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//decide when to nextday() , close or open market?
//when open have to update global variable date to current date in database
public class Communications {
	static Scanner scanner = new Scanner(System.in);

	public static void runQuery(String query) {
		Connection connection = null;
		Statement statement = null;
		try {
			System.out.printf("%s\n", query);
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.execute(query);
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
	}

	public static void createCustomerProfile() {
		String query = "CREATE TABLE CustomerProfile " + "( username VARCHAR(25) NOT NULL PRIMARY KEY, "
				+ "name VARCHAR(25), " + "state VARCHAR(2) NOT NULL, " + "phone_number VARCHAR(10) NOT NULL, "
				+ "email_address VARCHAR(25) NOT NULL, " + "taxid VARCHAR(25) NOT NULL, "
				+ "password VARCHAR(25) NOT NULL " + ") ;";
		System.out.printf("%s", query);
		runQuery(query);
	}

	public static void createMarketAccounts() {

		String query = "CREATE TABLE MarketAccounts " + "( username VARCHAR(25) NOT NULL, " + "balance DOUBLE(20, 2), "
				+ "account_mid INTEGER AUTO_INCREMENT, " + "averageDailyBalance INTEGER, "
				+ "PRIMARY KEY (account_mid) " + ", FOREIGN KEY (username) REFERENCES CustomerProfile (username)"
				+ ");";
		System.out.printf("%s%n", query);
		runQuery(query);
	}

	public static void createStockAccounts() {
		String query = "CREATE TABLE StockAccounts " + "( account_sid INTEGER AUTO_INCREMENT, " + "stock_id CHAR(3), "
				+ "balance DOUBLE(6,3), " + "buying_price INTEGER, " + "selling_price INTEGER, "
				+ "username VARCHAR(25), " + "PRIMARY KEY (account_sid), " +
				// "FOREIGN KEY (stock_id) REFERENCES Stocks (stock_id)"
				"FOREIGN KEY (username) REFERENCES CustomerProfile (username)) ;";
		System.out.printf("%s", query);
		runQuery(query);
	}

	public static void createStocks() {
		String query = "CREATE TABLE Stocks " + "( stock_id CHAR(3), " + "ad_id INTEGER, ad_name CHAR(25), "
				+ "closing_price DOUBLE(6,2), " + "current_price DOUBLE(6,2), " + "PRIMARY KEY(stock_id) " +
				// FOREIGN KEY(ad_id) REFERENCES ActorDirectorProfile (ad_id)
				");";
		System.out.printf("%s", query);
		runQuery(query);
	}

	public static void createActorDirectorProfile() {
		String query = "CREATE TABLE ActorDirectorProfile " + "( ad_id INTEGER AUTO_INCREMENT, ad_name CHAR(25), "
				+ "dob INTEGER, " + "PRIMARY KEY(ad_id) );";
		System.out.printf("%s", query);
		runQuery(query);
	}

	public static void createContract() {
		String query = "CREATE TABLE Contract" + "( contract_id INTEGER AUTO_INCREMENT, " + "movie_title CHAR(255), "
				+ "ad_id INTEGER, ad_name CHAR(25), " + "role CHAR(255), " + "year INTEGER, "
				+ "total_payment DOUBLE(6,3), " + "PRIMARY KEY(contract_id),"
				+ " FOREIGN KEY (ad_id) REFERENCES ActorDirectorProfile (ad_id)" + ");";
		runQuery(query);
	}

	public static void createTransactions() {
		String query = "Create Table Transactions "
				+ "( transid INTEGER AUTO_INCREMENT, username VARCHAR(25), transaction_type INTEGER, "
				+ "stock_id CHAR(3), " + "stock_quantity DOUBLE(6,3), " + "buying_price DOUBLE(6,2), "
				+ "selling_price DOUBLE (6,2), " + "deposit DOUBLE (6,2), " + "withdraw DOUBLE (6,2), "
				+ "accrue_interest DOUBLE (6,2), " + "dateOfTransaction DATE, " + "PRIMARY KEY (transid) " + ");";
		System.out.printf("%s", query);
		runQuery(query);
	}

	public static void createDate() {
		String query = "Create TABLE Date" + "( todaysDate DATE ) ;";
		runQuery(query);
	}

	public static void setDate(String todaysDate) {
		String query = "INSERT INTO Date (todaysDate)" + "VALUES (" + todaysDate + " );";
		runQuery(query);
	}

	public static String getDateString() {
		String date = null;
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		// String query = "SELECT EXTRACT(YEAR_MONTH FROM Date);";
		String query = "SELECT * FROM Date";
		// "SELECT CONVERT(varchar(10), todaysDate, 120) FROM Date;";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				date = rs.getString("todaysDate");
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
		return date;

	}

	public static void accrueInterest() {
		String query = "UPDATE MarketAccounts SET averageDailyBalance = ( averageDailyBalance + (balance / "
				+ MarketControl.getNumberOfDaysInMonth() + " ) );";
		runQuery(query);
	}

	public static void nextDay() {
		String query = "UPDATE Date SET todaysDate = DATE_ADD( todaysDate, INTERVAL 1 DAY) ;";
		runQuery(query);
	}

	public static void setCustomerProfile(String username, String name, String state, String phone_number,
			String email_address, String taxid, String password) {
		String query = "INSERT INTO CustomerProfile (username, name, state, phone_number, email_address, taxid, password)"
				+ " VALUES (" + "\"" + username + "\"" + "," + "\"" + name + "\"" + "," + "\"" + state + "\"" + ","
				+ "\"" + phone_number + "\"" + "," + "\"" + email_address + "\"" + "," + "\"" + taxid + "\"" + ","
				+ "\"" + password + "\"" + ");";
		runQuery(query);
	}

	public static void setMarketAccount(double balance, String username) {
		String query = "INSERT INTO MarketAccounts (balance, username) " + "VALUES (" + "\"" + balance + "\"" + ","
				+ "\"" + username + "\"" + ");";
		runQuery(query);
	}

	public static void setStockAccount(String stock_id, double balance, double buying_price, double selling_price,
			String username) {
		String query = "INSERT INTO StockAccounts (stock_id, balance, buying_price, selling_price, username) "
				+ "VALUES (" + "\"" + stock_id + "\"" + "," + "\"" + balance + "\"" + "," + "\"" + buying_price + "\""
				+ "," + "\"" + selling_price + "\"" + "," + "\"" + username + "\"" + ");";
		runQuery(query);
	}

	public static void setStocks(int ad_id, String stock_id, String ad_name, int closing_price, int current_price) {
		String query = "INSERT INTO Stocks (ad_id, stock_id, ad_name, closing_price, current_price) " + "VALUES (" + "\""
				+ ad_id + "\"" + "," + "\"" + stock_id + "\"" + "," + "\"" + ad_name + "\"" + "," + "\"" + closing_price
				+ "\"" + "," + "\"" + current_price + "\"" + ");";
		runQuery(query);
	}

	public static void setActorDirectorProfile(String ad_name, int dob) {
		String query = "INSERT INTO ActorDirectorProfile (ad_name, dob) " + "VALUES (" + "\"" + ad_name + "\"" + ", "
				+ "\"" + dob + "\"" + ");";
		runQuery(query);
	}

	public static void setContract(int ad_id, String ad_name, String movie_title, String role, int year,
			int total_payment) {
		String query = "INSERT INTO Contract (ad_id, ad_name, movie_title, role, year, total_payment) " + "VALUES ("
				+ "\"" + ad_id + "\"" + "," + "\"" + ad_name + "\"" + "," + "\"" + movie_title + "\"" + "," + "\""
				+ role + "\"" + "," + "\"" + year + "\"" + "," + "\"" + total_payment + "\"" + ");";
		runQuery(query);
	}

	public static void setTransaction(int transID, String username, int transaction_type, String stock_id,
			double stock_quantity, double buying_price, double selling_price, double deposit, double withdraw,
			double accrue_interest, String date) {
		String query = "INSERT INTO Transactions (transID, username, transaction_type, "
				+ "stock_id, stock_quantity, buying_price, selling_price, deposit, withdraw, accrue_interest, dateOfTransaction)"
				+ "VALUES (" + "\"" + transID + "\"" + "," + "\"" + username + "\"" + "," + "\"" + transaction_type
				+ "\"" + "," + "," + "\"" + stock_id + "," + "\"" + stock_quantity + "\"" + "," + "\"" + buying_price
				+ "\"" + "," + "\"" + selling_price + "\"" + "," + "\"" + deposit + "\"" + "," + "\"" + withdraw + "\""
				+ "," + "\"" + accrue_interest + "\"" + "," + "\"" + date + "\"" + ");";
		runQuery(query);
	}

	public static CustomerProfile getCustomerProfile(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		CustomerProfile customer = null;
		String query = "SELECT * FROM CustomerProfile WHERE username = " + "\"" + username + "\"";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				customer = new CustomerProfile();
				customer.setName(rs.getString("name"));
				customer.setState(rs.getString("state"));
				customer.setPhoneNumber(rs.getString("phone_number"));
				customer.setEmailAddress(rs.getString("email_address"));
				customer.setTaxID((rs.getString("taxid")));
				customer.setUsername((rs.getString("username")));
				customer.setPassword((rs.getString("password")));
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
		return customer;
	}

	public static MarketAccounts getMarketAccount(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		MarketAccounts market_account = null;
		String query = "SELECT * FROM MarketAccounts WHERE username = " + "'" + username + "'";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				market_account = new MarketAccounts();
				market_account.setAccountMID(rs.getInt("account_mid"));
				market_account.setBalance(rs.getInt("balance"));
				market_account.setUsername(rs.getString("username"));
				market_account.setAverageDailyBalance(rs.getDouble("averageDailyBalance"));
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
		return market_account;
	}

	public static StockAccounts getStockAccount(int account_sid) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		StockAccounts stock_account = null;
		String query = "SELECT * FROM StockAccounts WHERE account_sid = " + "\"" + account_sid + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				stock_account = new StockAccounts();
				stock_account.setAccountSID(rs.getInt("account_sid"));
				stock_account.setStockID(rs.getString("stock_id"));
				stock_account.setBalance(rs.getInt("balance"));
				stock_account.setBuyingPrice(rs.getInt("buying_price"));
				stock_account.setSellingPrice((rs.getInt("selling_price")));
				stock_account.setUsername((rs.getString("username")));
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
		return stock_account;
	}

	public static List<StockAccounts> getStockAccounts(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		List<StockAccounts> stock_accounts = new ArrayList<StockAccounts>();
		StockAccounts stock_account = null;
		String query = "SELECT * FROM StockAccounts WHERE username = " + "\"" + username + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				stock_account = new StockAccounts();
				stock_account.setAccountSID(rs.getInt("account_sid"));
				stock_account.setStockID(rs.getString("stock_id"));
				stock_account.setBalance(rs.getInt("balance"));
				stock_account.setBuyingPrice(rs.getInt("buying_price"));
				stock_account.setSellingPrice((rs.getInt("selling_price")));
				stock_account.setUsername((rs.getString("username")));
				stock_accounts.add(stock_account);
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
		return stock_accounts;
	}

	public static Stocks getStock(String stock_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Stocks stock = null;
		String query = "SELECT * FROM Stocks WHERE stock_id = " + "\"" + stock_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				stock = new Stocks();
				stock.setStockID(rs.getString("stock_id"));
				stock.setADID(rs.getInt("ad_id"));
				stock.setADName(rs.getString("ad_name"));
				stock.setClosingPrice(rs.getInt("closing_price"));
				stock.setCurrentPrice(rs.getInt("current_price"));
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
		return stock;
	}

	public static Stocks getStock(int ad_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Stocks stock = null;
		String query = "SELECT * FROM Stocks WHERE ad_id = " + "\"" + ad_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				stock = new Stocks();
				stock.setStockID(rs.getString("stock_id"));
				stock.setADID(rs.getInt("ad_id"));
				stock.setADName(rs.getString("ad_name"));
				stock.setClosingPrice(rs.getInt("closing_price"));
				stock.setCurrentPrice(rs.getInt("current_price"));
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
		return stock;
	}

	public static List<Stocks> getStocks() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Stocks> stocks = new ArrayList<Stocks>();
		Stocks stock = null;
		String query = "SELECT * FROM Stocks" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				stock = new Stocks();
				stock.setStockID(rs.getString("stock_id"));
				stock.setADID(rs.getInt("ad_id"));
				stock.setADName(rs.getString("ad_name"));
				stock.setClosingPrice(rs.getInt("closing_price"));
				stock.setCurrentPrice(rs.getInt("current_price"));
				stocks.add(stock);
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
		return stocks;
	}

	public static ActorDirectorProfile getActorDirectorProfile(int ad_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		ActorDirectorProfile actor_director = null;
		String query = "SELECT * FROM ActorDirectorProfile WHERE ad_id = " + "\"" + ad_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				actor_director = new ActorDirectorProfile();
				actor_director.setADID(rs.getInt("ad_id"));
				;
				actor_director.setADName(rs.getString("ad_name"));
				actor_director.setDOB(rs.getInt("dob"));
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
		return actor_director;
	}

	public static List<ActorDirectorProfile> getActorDirectorProfiles() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<ActorDirectorProfile> actor_directors = new ArrayList<ActorDirectorProfile>();
		ActorDirectorProfile actor_director = null;
		String query = "SELECT * FROM ActorDirectorProfile" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				actor_director = new ActorDirectorProfile();
				actor_director.setADID(rs.getInt("ad_id"));
				;
				actor_director.setADName(rs.getString("ad_name"));
				actor_director.setDOB(rs.getInt("dob"));
				actor_directors.add(actor_director);
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
		return actor_directors;
	}

	public static Contract getContract(int ad_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Contract contract = null;
		String query = "SELECT * FROM Contract WHERE ad_id = " + "\"" + ad_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				contract = new Contract();
				contract.setADID(rs.getInt("ad_id"));
				contract.setADName(rs.getString("ad_name"));
				contract.setContractID(rs.getInt("contract_id"));
				contract.setMovieTitle(rs.getString("movie_title"));
				contract.setRole(rs.getString("role"));
				contract.setYear(rs.getInt("year"));
				contract.setTotalPayment(rs.getInt("total_payment"));
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
		return contract;
	}

	public static List<Contract> getContracts(int ad_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Contract> contracts = new ArrayList<Contract>();
		Contract contract = null;
		String query = "SELECT * FROM Contract WHERE ad_id = " + "\"" + ad_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				contract = new Contract();
				contract.setADID(rs.getInt("ad_id"));
				contract.setADName(rs.getString("ad_name"));
				contract.setContractID(rs.getInt("contract_id"));
				contract.setMovieTitle(rs.getString("movie_title"));
				contract.setRole(rs.getString("role"));
				contract.setYear(rs.getInt("year"));
				contract.setTotalPayment(rs.getInt("total_payment"));
				contracts.add(contract);
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
		return contracts;
	}

	public static List<Transaction> getTransactions(String username, String stock_id, int transaction_type) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = null;
		String query = "SELECT * FROM Transactions WHERE username = " + "\"" + username + "\"" + " AND stock_id = "
				+ "\"" + stock_id + "\"" + " AND transaction_type = " + "\"" + transaction_type + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				transaction = new Transaction();
				transaction.setTransID((rs.getInt("transID")));
				transaction.setUsername((rs.getString("username")));
				transaction.setTransactionType((rs.getInt("transaction_type")));
				transaction.setStockQuantity(rs.getDouble("stock_quantity"));
				transaction.setStockID(rs.getString("stock_id"));
				transaction.setBuyingPrice(rs.getDouble("buying_price"));
				transaction.setSellingPrice((rs.getDouble("selling_price")));
				transaction.setDeposit(rs.getDouble("deposit"));
				transaction.setWithdraw((rs.getDouble("withdraw")));
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
		return transactions;
	}

	public static List<Transaction> getTransactionsHistory(String username, String stock_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = null;
		String query = "SELECT * FROM Transactions WHERE username = " + "\"" + username + "\"" + " AND stock_id = "
				+ "\"" + stock_id + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				transaction = new Transaction();
				transaction.setTransID((rs.getInt("transID")));
				transaction.setUsername((rs.getString("username")));
				transaction.setTransactionType((rs.getInt("transaction_type")));
				transaction.setStockID(rs.getString("stock_id"));
				transaction.setStockQuantity(rs.getDouble("stock_quantity"));
				transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));
				transaction.setWithdraw((rs.getDouble("withdraw")));
				transaction.setBuyingPrice(rs.getDouble("buying_price"));
				transaction.setSellingPrice((rs.getDouble("selling_price")));
				transaction.setDeposit(rs.getDouble("deposit"));
				transaction.setAccrueInterest(rs.getDouble("accrue_interest"));
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
		return transactions;
	}

	public static List<Transaction> getTransactionsHistory(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = null;
		String query = "SELECT * FROM Transactions WHERE username = " + "\"" + username + "\"" + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				transaction = new Transaction();
				transaction.setTransID((rs.getInt("transID")));
				transaction.setUsername((rs.getString("username")));
				transaction.setTransactionType((rs.getInt("transaction_type")));
				transaction.setStockID(rs.getString("stock_id"));
				transaction.setStockQuantity(rs.getDouble("stock_quantity"));
				transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));
				transaction.setWithdraw((rs.getDouble("withdraw")));
				transaction.setBuyingPrice(rs.getDouble("buying_price"));
				transaction.setSellingPrice((rs.getDouble("selling_price")));
				transaction.setDeposit(rs.getDouble("deposit"));
				transaction.setAccrueInterest(rs.getDouble("accrue_interest"));
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
		return transactions;
	}

	public static void updateMarketAccount(String username, double deposit) {
		String query = "UPDATE MarketAccounts " + "SET balance = " + deposit + " WHERE username = " + "'" + username
				+ "'" + ";";
		runQuery(query);
	}

	public static void updateStockAccountSell(int account_sid, double selling_price, double stockQuantity) {
		String query = "UPDATE StockAccounts " + "SET selling_price = " + selling_price + ", balance = " + stockQuantity
				+ " WHERE account_sid = " + "'" + account_sid + "'" + ";";
		runQuery(query);
	}

	public static void updateStockAccountBuy(int account_sid, double buying_price, double stockQuantity) {
		String query = "UPDATE StockAccounts " + "SET buying_price = " + buying_price + ", balance = " + stockQuantity
				+ "WHERE account_sid = " + "'" + account_sid + "'" + " ;";
		runQuery(query);
	}

	// stock_id
	public static void insertTransactionSell(int transaction_type, double selling_price, double stock_quantity,
			String username, String stock_id) {
		String query = "INSERT INTO Transactions( "
				+ "selling_price, transaction_type, stock_quantity, username, dateOfTransaction, stock_id )"
				+ " VALUES (" + selling_price + ", " + transaction_type + ", " + stock_quantity + ", " + "'" + username
				+ "'" + ", " + "'" + MarketControl.todaysDate + "', '" + stock_id + "' );";
		runQuery(query);
	}

	public static void insertTransactionBuy(int transaction_type, double buying_price, double stock_quantity,
			String username, String stock_id) {
		String query = "INSERT INTO Transactions (buying_price, transaction_type,  stock_quantity, username, dateOfTransaction, stock_id )"
				+ " VALUES (" + buying_price + ", " + transaction_type + ", " + stock_quantity + ", " + "'" + username
				+ "'" + ", '" + MarketControl.todaysDate + "', '" + stock_id + "' );";
		runQuery(query);
	}

	public static void insertTransactionDeposit(int transaction_type, double deposit, String username) {

		String query = "INSERT INTO Transactions (" + "deposit, transaction_type,  username, dateOfTransaction) "
				+ "VALUES (" + "'" + deposit + "'" + ", " + "'" + transaction_type + "'" + ", " + "'" + username + "'"
				+ ", " + "'" + MarketControl.todaysDate + "');";
		runQuery(query);
	}

	public static void insertTransactionWithdraw(int transaction_type, double withdraw, String username) {
		String query = "INSERT INTO Transactions ( withdraw, transaction_type, username, dateOfTransaction ) "
				+ " VALUES (" + withdraw + ", " + transaction_type + ", '" + username + "', '"
				+ MarketControl.todaysDate + "');";
		runQuery(query);
	}

	public static void insertTransactionAccrueInterest(int transaction_type, String username, double accrue_interest) {
		String query = "INSERT INTO Transactions ( username, transaction_type, accrue_interest, dateOfTransaction) VALUES ( '"
				+ username + "', '" + transaction_type + "' , '" + accrue_interest + "', '"
				+ MarketControl.getTodaysDate() + "');";
		System.out.println(query);
		runQuery(query);

	}

	public static void updateStock(String stock_id, double current_price) {
		String query = "UPDATE Stocks " + "SET current_price = " + current_price + " WHERE stock_id = '" + stock_id
				+ "';";
		runQuery(query);
	}

	public static void updateStockClosingPrice() {
		String query = "UPDATE Stocks " + "SET closing_price = current_price " + ";";
		runQuery(query);
	}

}