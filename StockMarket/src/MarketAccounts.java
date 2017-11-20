
public class MarketAccounts {
	
	private int account_mid;
	private int balance;
	private int transID;
	private String username;
	
	public int getAccountMID() {
		return account_mid;
	}
	
	public void setAccountMID(int account_mid) {
		this.account_mid = account_mid;
	}

	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getTransID() {
		return transID;
	}
	
	public void setTransID(int transID) {
		this.transID = transID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	//toString()
	@Override
	public String toString() {
		return "MarketAccounts [account_mid=" + account_mid + ", balance=" + balance + ", transID="
				+ transID + ", username=" + username + "]";
	}		
}
