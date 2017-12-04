import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Communications {
	static Scanner scanner = new Scanner(System.in);

	public static void runQuery(String query) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.execute(query);
			System.out.printf("%s\n", query);
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

	public void createCustomerProfile() {
		String query = "CREATE TABLE CustomerProfile " + "( username VARCHAR(25) NOT NULL, "
				+ "name VARCHAR(25) NOT NULL, " + "state VACHAR(2) NOT NULL, " + "phone_number VARCHAR(10) NOT NULL, "
				+ "email_address VARCHAR(25) NOT NULL, " + "taxid VARCHAR(25) NOT NULL, "
				+ "password VARCHAR(25) NOT NULL, " + "PRIMARY KEY (username) " + ")";

		runQuery(query);
	}

	public void createMarketAccounts() {

		String query = "CREATE TABLE MarketAccounts " + "( username VARCHAR(25) NOT NULL, " + "balance DOUBLE(20, 2), "
				+ "transid INTEGER, " + "account_mid INTEGER, " + "PRIMARY KEY (account_mid) "
				+ "FOREIGN KEY (username) REFERENCES CustomerProfile)" + ")";
		runQuery(query);
	}

	public void createStockAccounts() {
		String query = "CREATE TABLE StockAccounts " + "( account_sid INTEGER, " + "stock_id CHAR(3), "
				+ "balance DOUBLE(6,3), " + "buying_price INTEGER, " + "selling_price INTEGER, " + "transid INTEGER, "
				+ "username CHAR(25), " + "PRIMARY KEY (account_sid), " +
				// "FOREIGN KEY (transid) REFERENCES TRANSACTION" +
				"FOREIGN KEY (username) REFERENCES CustomerProfile)";
		runQuery(query);
	}

	public void createStocks() {
		String query = "CREATE TABLE Stocks " + "( stock_id CHAR(3), " + "ad_name CHAR(25), "
				+ "closing_price DOUBLE(6,2), " + "current_price DOUBLE(6,2), " + "PRIMARY KEY(stock_id), "
				+ "FOREIGN KEY(ad_name) REFERENCES ActorDirectorProfile )";
		runQuery(query);
	}

	public void createActorDirectorProfile() {

		String query = "CREATE TABLE ActorDirectorProfile " + "( ad_name CHAR(25), " + "stock_id CHAR(3), "
				+ "dob INTEGER, " + "contract_id CHAR(25), " + "PRIMARY KEY(ad_name), "
				+ "FOREIGN KEY(stock_id) REFERENCES Stocks )";
		runQuery(query);

	}

	public void createContract() {
		String query = "CREATE TABLE Contract" + "( contract_id INTEGER, " + "movie_title CHAR(255), "
				+ "role CHAR(255), " + "year INTEGER, " + "total_payment DOUBLE(6,3), " + "PRIMARY KEY(contract_id) ) ";
		runQuery(query);
	}

	public void createTransactions() {
		String query = "Create Table Transactions "
				+ "( transid INTEGER, username VARCHAR(25), transaction_type INTEGER," + " account_sid INTEGER"
				+ " account_mid INTEGER" + ", stock_id CHAR(3)" + ", stockQuantity DOUBLE(6,3) "
				+ ", buying_price DOUBLE(6,2)" + ", selling_price DOUBLE (6.2) " + ", deposit DOUBLE (6.2) "
				+ ", withdraw DOUBLE (6.2) " + ", accrue_interest DOUBLE (6.2) " + "PRIMARY KEY (transid), "
                + ", dateOfTransaction DATE"
				+ "FOREIGN KEY(username) REFERENCES CustomerProfile, "
				+ "FOREIGN KEY (account_sid INTEGER) REFERENCES StockAccounts" + ")";
		runQuery(query);
	}

	public void createDate(){
		String query = "Create  TABLE Date"
				+ "( todaysDate DATE ) ;" ;
	}

	public static void setDate(String todaysDate){
		String query = "INSERT INTO Date (todaysDate)"
				+ "VALUES (" + todaysDate + " );";
	}

	public static void setCustomerProfile(String username, String name, String state, String phone_number,
			String email_address, String taxid, String password) {

		String query = "INSERT INTO CustomerProfile (username, name, state, phone_number, email_address, taxid, password)"
				+ "VALUES (" + username + ", " + name + ", " + state + ", " + phone_number + ", " + email_address + ", "
				+ taxid + ", " + password + ");";

		runQuery(query);
	}

	public static void setMarketAccount(int account_mid, double balance, int transID, String username) {
		String query = "INSERT INTO MarketAccounts (account_mid, balance, transID, username)" + "VALUES (" + account_mid
				+ ", " + balance + ", " + transID + ", " + username + ");";

		runQuery(query);
	}

	public static void setStockAccount(int account_sid, String stock_id, double balance, double buying_price,
			double selling_price, int transID, String username) {

		String query = "INSERT INTO StockAccounts (account_sid, stock_id, balance, buying_price, selling_price, transID, username)"
				+ "VALUES (" + account_sid + ", " + stock_id + ", " + balance + ", " + buying_price + ", "
				+ selling_price + ", " + transID + ", " + username + ");";

		runQuery(query);
	}

	public static void setStocks(String stock_id, String ad_name, int closing_price, int current_price) {
		String query = "INSERT INTO Stocks (stock_id, ad_name, closing_price, current_price)" + "VALUES (" + stock_id
				+ ", " + ad_name + ", " + closing_price + ", " + current_price + ");";

		runQuery(query);
	}

	public static void setActorDirectorProfile(String ad_name, String stock_id, int dob, String contract_id) {
		String query = "INSERT INTO ActorDirectorProfile (ad_name, stock_id, dob, contract_id)" + "VALUES (" + ad_name
				+ ", " + stock_id + ", " + dob + ", " + contract_id + ");";

		runQuery(query);
	}

	public static void setContract(String ad_name, int contract_id, String movie_title, String role, int year,
			int total_payment) {
		String query = "INSERT INTO Contracts (ad_name, contract_id, movie_title, role, year, total_payment)"
				+ "VALUES (" + ad_name + ", " + contract_id + ", " + movie_title + ", " + role + ", " + year + ", "
				+ total_payment + ");";

		runQuery(query);
	}

	public static void setTransaction(int transID, String username, int transaction_type, int account_sid,
			int account_mid, String stock_id, double stock_quantity, double buying_price, double selling_price,
			double deposit, double withdraw, double accrue_interest, String date) {
		String query = "INSERT INTO Transactions (transID, username, transaction_type, account_sid, account_mid,"
				+ "stock_id, stock_quantity, buying_price, selling_price, deposit, withdraw, accrue_interest, dateOfTransaction)"
				+ "VALUES (" + transID + ", " + username + ", " + transaction_type + ", " + account_sid + ", "
				+ account_mid + ", " + stock_id + "," + stock_quantity + ", " + buying_price + ", " + selling_price
				+ ", " + deposit + ", " + withdraw + ", " + accrue_interest  + ", " + date + ");";

		runQuery(query);
	}

	public static CustomerProfile getCustomerProfile(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		CustomerProfile customer = null;
		String query = "SELECT * FROM CustomerProfile WHERE username=" + username;
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
		String query = "SELECT * FROM MarketAccounts WHERE username=" + username;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				market_account = new MarketAccounts();
				market_account.setAccountMID(rs.getInt("account_mid"));
				market_account.setBalance(rs.getInt("balance"));
				market_account.setTransID(rs.getInt("transID"));
				market_account.setUsername(rs.getString("username"));
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

	public static StockAccounts getStockAccount(String username, String stock_id, double buying_price) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		StockAccounts stock_account = null;
		String query = "SELECT * FROM StockAccounts WHERE username =" + username + " AND stock_id = " + stock_id
				+ " AND buying_price = " + buying_price + ";";
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
				stock_account.setTransID((rs.getInt("transID")));
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

	public static Stocks getStock(String stock_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Stocks stock = null;
		String query = "SELECT * FROM Stocks WHERE stock_id=" + stock_id;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				stock = new Stocks();
				stock.setStockID(rs.getString("stock_id"));
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

	public static ActorDirectorProfile getActorDirectorProfile(String stock_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		ActorDirectorProfile actor_director = null;
		String query = "SELECT * FROM ActorDirectorProfile WHERE stock_id=" + stock_id;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				actor_director = new ActorDirectorProfile();
				actor_director.setADName(rs.getString("ad_name"));
				actor_director.setStockID(rs.getString("stock_id"));
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

	public static Contract getContract(String ad_name) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Contract contract = null;
		String query = "SELECT * FROM Contracts WHERE ad_name=" + ad_name;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				contract = new Contract();
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

	public static Transaction getTransaction(String username, String stock_id, String account_sid) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Transaction transaction = null;
		String query = "SELECT * FROM Transactions WHERE username =" + username + " AND stock_id = " + stock_id
				+ " AND account_sid = " + account_sid + ";";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				transaction = new Transaction();
				transaction.setTransID((rs.getInt("transID")));
				transaction.setUsername((rs.getString("username")));
				transaction.setTransactionType((rs.getInt("transaction_type")));
				transaction.setAccountSID(rs.getInt("account_sid"));
				transaction.setAccountMID(rs.getInt("account_mid"));
				transaction.setStockQuantity(rs.getDouble("stock_quantity"));
				transaction.setStockID(rs.getString("stock_id"));
				transaction.setBuyingPrice(rs.getDouble("buying_price"));
				transaction.setSellingPrice((rs.getDouble("selling_price")));
				transaction.setDeposit(rs.getDouble("deposit"));
				transaction.setWithdraw((rs.getDouble("withdraw")));
				transaction.setAccrueInterest(rs.getDouble("accrue_interest"));
				transaction.setDateOfTransaction(rs.getString( "dateOfTransaction"));
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
		return transaction;
	}

	public static void updateMarketAccount(int account_mid, int transID, String username, double deposit) {
		String query = "UPDATE MarketAccounts" + "SET  balance = " + deposit + "\n" + "WHERE username = '" + username
				+ "' ;";
		runQuery(query);
	}

	public static void updateStockAccountSell(int account_sid, double selling_price, double stockQuantity,
			String username) { // change parameters
		String query = "UPDATE StockAccounts" + "SET selling_price = " + selling_price + ", balance = " + stockQuantity
				+ "\n" + "WHERE account_sid = '" + account_sid + "' AND username = '" + username + "' ;";
		runQuery(query);
	}

	public static void updateStockAccountBuy(int account_sid, double buying_price, double stockQuantity,
			String username) {
		String query = "UPDATE StockAccounts" + "SET buying_price = " + buying_price + ", balance = " + stockQuantity
			+ "\n" + "WHERE account_sid = '" + account_sid + "' AND username = '" + username + "' ;";
		runQuery(query);
	}

	public static void insertTransactionSell(int account_sid, int transaction_type, double selling_price,
			double stock_quantity, String username) {
		String query = "INSERT INTO  Transactions( " + "selling_price, transaction_type,  stock_quantity, account_sid ,username, dateOfTransaction )"
                + " VALUES (" + selling_price + ", " + transaction_type + ", " + stock_quantity + ", " + account_sid + ", " + username +  ", " + Globals.todaysDate + " );";
		runQuery(query);
	}

	public static void insertTransactionBuy(int account_sid, int transaction_type, double buying_price,
			double stock_quantity, String username) {
        String query = "INSERT INTO  Transactions( " + "buying_price, transaction_type,  stock_quantity, account_sid ,username, dateOfTransaction )"
                + " VALUES (" + buying_price + ", " + transaction_type + ", " + stock_quantity + ", " + account_sid + ", " + username + ", " + Globals.todaysDate+ " );"
				+ account_sid + "' AND username = '" + username + "' ;";
		runQuery(query);
	}

	public static void insertTransactionDeposit(int account_mid, int transaction_type, double deposit,
			String username) {

		String query = "INSERT INTO Transactions (" + "deposit, transaction_type, account_mid,  username, dateOfTransaction ) " +
                " VALUES (" + deposit + ", " + transaction_type  + ", " + account_mid + ", " + username + ", " + Globals.todaysDate + "' ;";
		runQuery(query);
	}

	public static void insertTransactionWithdraw(int account_mid, int transaction_type, double withdraw,
			String username) {
        String query = "INSERT INTO Transactions (" + "withdraw, transaction_type, account_mid,  username, dateOfTransaction ) " +
                " VALUES (" + withdraw + ", " + transaction_type  + ", " + account_mid + ", " + username + ", " + Globals.todaysDate + "' ;";
		runQuery(query);
	}

	/*
	 * public static void updateTransactionAccrueInterest(int account_mid, int
	 * transaction_type, double buying_price, String username) { String query =
	 * "UPDATE Transactions" + "SET buying_price = " + buying_price +
	 * ", transaction_type = " + transaction_type + "\n" + "WHERE account_mid = '" +
	 * account_mid + "' AND username = '" + username + "' ;"; runQuery(query); }
	 */
	public static void updateStock(String stock_id, double current_price) {
		String query = "UPDATE Stocks" + "SET current_price = " + current_price + "\n" + "WHERE stock_id = '" + stock_id
				+ "' ;";
		runQuery(query);
	}
	
	public static void updateStockClosingPrice() {
		String query = "UPDATE Stocks" + "SET closing_price = current_price" + " ;";
		runQuery(query);
	}

}