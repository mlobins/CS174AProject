

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
//import com.theopentutorials.jdbc.db.DbUtil;
//import com.theopentutorials.jdbc.db.JDBCMySQLConnection;
//import com.theopentutorials.jdbc.to.Employee;

//toDo
//Get connection working
//We have get functions now we need set functions
//Transaction functions (buy, sell, deposit, withdraw, accrue_interest)
//Interface and its appropriate data



public class JDBCMySQL {
  public static void main(String[] args){
	  Connection connection = null;
	  try {
		  connection = JDBCMySQLConnection.getConnection();
		  
	  } finally{
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
  }
  
  public CustomerProfile getCustomerProfile(int username)  {		
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
  
  public MarketAccounts getMarketAccount(int account_mid)  {		
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
  

  
  public StockAccounts getStockAccount(int username)  {		
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
  
  public Stocks getStock(int username)  {		
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
  
  public ActorDirectorProfile getActorDirector(int username)  {		
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
	
	
}
