
public class StockAccounts {

	private int account_sid;
	private int stock_id;
	private double balance;
	private int buying_price;
	private int selling_price;
	private int transID;
	private String username;

	public int getAccountSID() {
		return account_sid;
	}

	public void setAccountSID(int account_sid) {
		this.account_sid = account_sid;
	}

	public int getStockID() {
		return stock_id;
	}

	public void setStockID(int stock_id) {
		this.stock_id = stock_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getBuyingPrice() {
		return buying_price;
	}

	public void setBuyingPrice(int buying_price) {
		this.buying_price = buying_price;
	}

	public int getSellingPrice() {
		return selling_price;
	}

	public void setSellingPrice(int selling_price) {
		this.selling_price = selling_price;
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

	// toString()
	@Override
	public String toString() {

		return "StockAccounts [account_sid=" + account_sid + ", stock_id=" + stock_id + ", balance=" + balance
				+ ", buying_price=" + buying_price + ", selling_price=" + selling_price + ", transID=" + transID
				+ ", username=" + username + "]";
	}
}
