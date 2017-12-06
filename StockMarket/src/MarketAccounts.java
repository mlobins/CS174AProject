
public class MarketAccounts {

	private int account_mid;
	private double balance;
	private String username;
	private double averageDailyBalance;

	public double getAverageDailyBalance() {
		return averageDailyBalance;
	}

	public void setAverageDailyBalance(double AverageDailyBalance) {
		averageDailyBalance = AverageDailyBalance;
	}

	public int getAccountMID() {
		return account_mid;
	}

	public void setAccountMID(int account_mid) {
		this.account_mid = account_mid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// toString()
	@Override
	public String toString() {
		return "MarketAccounts [account_mid=" + account_mid + ", balance=" + balance + ", username=" + username
				+ ", averageDailyBalance=" + averageDailyBalance + "]";
	}
}
