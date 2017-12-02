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
				+ "transid INTEGER, " + "account_mid INTEGER, " + "PRIMARY KEY (account_mid) " + ")";
		runQuery(query);
	}

	public void createStockAccounts() {
		String query = "CREATE TABLE StockAccounts " + "( account_sid INTEGER, " + "stock_id INTEGER, "
				+ "balance DOUBLE(6,2), " + "buying_price INTEGER, " + "selling_price INTEGER, " + "transid INTEGER, "
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

	public static void setCustomerProfile(String username, String name, String state, String phone_number,
			String email_address, String taxid, String password) {

		String query = "INSERT INTO CustomerProfile (username, name, state, phone_number, email_address, taxid, password)"
				+ "VALUES (" + username + ", " + name + ", " + state + ", " + phone_number + ", " + email_address + ", "
				+ taxid + ", " + password + ");";

		runQuery(query);
	}

	public static void setMarketAccount(int account_mid, int balance, int transID, String username) {
		String query = "INSERT INTO MarketAccounts (account_mid, balance, transID, username)" + "VALUES (" + account_mid
				+ ", " + balance + ", " + transID + ", " + username + ");";

		runQuery(query);
	}

	public static void setStockAccount(int account_sid, int stock_id, double balance, int buying_price,
			int selling_price, int transID, String username) {

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

	public static StockAccounts getStockAccount(String username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		StockAccounts stock_account = null;
		String query = "SELECT * FROM StockAccounts WHERE username=" + username;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				stock_account = new StockAccounts();
				stock_account.setAccountSID(rs.getInt("account_sid"));
				stock_account.setStockID(rs.getInt("stock_id"));
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

	public static Stocks getStock(int stock_id) {
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

	public static ActorDirectorProfile getActorDirectorProfile(int username) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		ActorDirectorProfile actor_director = null;
		String query = "SELECT * FROM ActorDirectorProfile WHERE username=" + username;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				actor_director = new ActorDirectorProfile();
				actor_director.setADName(rs.getString("ad_name"));
				actor_director.setStockID(rs.getString("stock_id"));
				actor_director.setDOB(rs.getInt("dob"));
				actor_director.setContract_ID(rs.getString("contract_id"));
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

	public static void updateMarketAccount(int account_mid, int transID, String username, int deposit)  {		//change parameters
		String query = "UPDATE MarketAccounts"
						+ "SET  balance = " + deposit +  "\n"
						+ "WHERE username = '" + username + "' ;";
		runQuery(query);
		
		// add to transaction table too.
		
	}

	public static void updateStockAccount(int account_mid, double balance, int transID, String username, int deposit) { // change parameters
		String query = "UPDATE MarketAccounts" + "SET " + "WHERE ;";

		runQuery(query);
	}

}
