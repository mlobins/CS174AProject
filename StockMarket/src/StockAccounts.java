
public class StockAccounts {

	private int account_sid;
	private String stock_id;
	private double balance;
	private double buying_price;
	private double selling_price;
	private String username;

	public int getAccountSID() {
		return account_sid;
	}

	public void setAccountSID(int account_sid) {
		this.account_sid = account_sid;
	}

	public String getStockID() {
		return stock_id;
	}

	public void setStockID(String stock_id) {
		this.stock_id = stock_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBuyingPrice() {
		return buying_price;
	}

	public void setBuyingPrice(double buying_price) {
		this.buying_price = buying_price;
	}

	public double getSellingPrice() {
		return selling_price;
	}

	public void setSellingPrice(double selling_price) {
		this.selling_price = selling_price;
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

		return "StockAccounts [account_sid=" + account_sid + ", stock_id=" + stock_id + ", balance=" + balance
				+ ", buying_price=" + buying_price + ", selling_price=" + selling_price + ", username=" + username
				+ "]";
	}
}
