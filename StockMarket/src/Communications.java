import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Communications {
	static Scanner scanner = new Scanner(System.in);
	
	  public static void setCustomerProfile()  {		
			Connection connection = null;
			Statement statement = null; 

			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			System.out.println("Your username is " + username);
			
			System.out.println("Enter your name: ");
			String name = scanner.nextLine();
			System.out.println("Your name is " + name);
			
			System.out.println("Enter your state: ");
			String state = scanner.nextLine();
			System.out.println("Your state is " + state);
			
			System.out.println("Enter your phone_number: ");
			String phone_number = scanner.nextLine();
			System.out.println("Your phone_number is " + phone_number);
			
			System.out.println("Enter your email_address: ");
			String email_address = scanner.nextLine();
			System.out.println("Your email_address is " + email_address);
			
			System.out.println("Enter your taxid: ");
			String taxid = scanner.nextLine();
			System.out.println("Your taxid is " + taxid);
			
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			System.out.println("Your password is " + password);
			
			String query = "INSERT INTO CustomerProfile (username, name, state, phone_number, email_address, taxid, password)"
							+ "VALUES ("
							+ username + ", "
							+ name + ", " 
							+ state + ", " 
							+ phone_number + ", "
							+ email_address + ", "
							+ taxid + ", " 
							+ password + ");";
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

	  public static void setMarketAccount(int account_mid, int balance, int transID, String username)  {		
			Connection connection = null;
			Statement statement = null; 
			
			String query = "INSERT INTO MarketAccounts (account_mid, balance, transID, username)" 
							+ "VALUES ("
							+ account_mid + ", "
							+ balance + ", " 
							+ transID + ", " 
							+ username + ");";
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
	
	  public static void setStockAccount(int account_sid, int stock_id, double balance, int buying_price, int selling_price, int transID, String username)  {		
			Connection connection = null;
			Statement statement = null; 
			
			String query = "INSERT INTO StockAccounts (account_sid, stock_id, balance, buying_price, selling_price, transID, username)"
							+ "VALUES ("
							+ account_sid + ", "
							+ stock_id + ", " 
							+ balance + ", " 
							+ buying_price + ", "
							+ selling_price + ", "
							+ transID + ", " 
							+ username + ");";
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

	  public static void setStocks(String stock_id, String ad_name, int closing_price, int current_price)  {		
			Connection connection = null;
			Statement statement = null; 
			
			String query = "INSERT INTO Stocks (stock_id, ad_name, closing_price, current_price)"
							+ "VALUES ("
							+ stock_id + ", "
							+ ad_name + ", " 
							+ closing_price + ", " 
							+ current_price + ");";
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

	  public static void setActorDirectorProfile(String ad_name, String stock_id, int dob, String contract_id)  {		
			Connection connection = null;
			Statement statement = null; 
			
			String query = "INSERT INTO ActorDirectorProfile (ad_name, stock_id, dob, contract_id)"
							+ "VALUES ("
							+ ad_name + ", "
							+ stock_id + ", " 
							+ dob + ", " 
							+ contract_id + ");";
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
	  
	  public static CustomerProfile getCustomerProfile(String username)  {		
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
	  
	  public static MarketAccounts getMarketAccount(int account_mid)  {		
			ResultSet rs = null;
			Connection connection = null;
			Statement statement = null; 
			
			MarketAccounts market_account = null;
			String query = "SELECT * FROM MarketAccounts WHERE account_mid=" + account_mid;
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
	  
	  public static StockAccounts getStockAccount(int username)  {		
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
	  
	  public static Stocks getStock(int username)  {		
			ResultSet rs = null;
			Connection connection = null;
			Statement statement = null; 
			
			Stocks stock = null;
			String query = "SELECT * FROM Stocks WHERE username=" + username;
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
	  
	  public static ActorDirectorProfile getActorDirector(int username)  {		
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

	  public static void updateMarketAccount(int account_mid, int balance, int transID, String username, int deposit)  {		//change parameters
			Connection connection = null;
			Statement statement = null; 

			String query = "UPDATE MarketAccounts"
							+ "SET "
							+ "WHERE ;";
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

}
