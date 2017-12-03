
public class Transaction {

	private int transID;
	private String username;
	private int account_sid;
	private String stock_id;
	private double buying_price;
	private double selling_price;

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

		return "Transaction [account_sid=" + account_sid + ", stock_id=" + stock_id + ", buying_price=" + buying_price
				+ ", selling_price=" + selling_price + ", transID=" + transID + ", username=" + username + "]";
	}

}