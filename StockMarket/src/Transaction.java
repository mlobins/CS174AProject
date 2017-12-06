
public class Transaction {

	private int transID;
	private String username;
	private int transaction_type;
	private String stock_id;
	private double stock_quantity;
	private double buying_price;
	private double selling_price;
	private double deposit;
	private double withdraw;
	private double accrue_interest;
	private String dateOfTransaction;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTransactionTypeNumber() {
		return transaction_type;
	}

	public String getTransactionType() {
		switch (transaction_type) {
		case 1:
			return "Buy";
		case 2:
			return "Sell";
		case 3:
			return "Withdraw";
		case 4:
			return "Deposit";
		case 5:
			return "Accrue_Interest";
		default:
			return "Invalid";
		}
	}

	public void setTransactionType(int type) {
		this.transaction_type = type;
	}

	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(String date) {
		this.dateOfTransaction = date;
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

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}

	public double getAccrueInterest() {
		return accrue_interest;
	}

	public void setAccrueInterest(double accrue_interest) {
		this.accrue_interest = accrue_interest;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public double getStockQuantity() {
		return stock_quantity;
	}

	public void setStockQuantity(double stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	// toString()
	@Override
	public String toString() {

		return "Transaction [transID=" + transID + ", username=" + username + ", transaction_type=" + transaction_type
				+ ", stock_id=" + stock_id + ", buying_price=" + buying_price + ", selling_price=" + selling_price
				+ ", deposit=" + deposit + ", withdraw=" + withdraw + ", accrue_interest=" + accrue_interest + "]";
	}

}