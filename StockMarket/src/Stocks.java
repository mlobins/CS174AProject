
public class Stocks {

	private String stock_id;
	private String ad_name;
	private int closing_price;
	private int current_price;

	public String getStockID() {
		return stock_id;
	}

	public void setStockID(String stock_id) {
		this.stock_id = stock_id;
	}

	public String getADName() {
		return ad_name;
	}

	public void setADName(String ad_name) {
		this.ad_name = ad_name;
	}

	public int getClosingPrice() {
		return closing_price;
	}

	public void setClosingPrice(int closing_price) {
		this.closing_price = closing_price;
	}

	public int getCurrentPrice() {
		return current_price;
	}

	public void setCurrentPrice(int current_price) {
		this.current_price = current_price;
	}

	// toString()
	@Override
	public String toString() {
		return "Stocks [stock_id=" + stock_id + ", ad_name=" + ad_name + ", closing_price=" + closing_price
				+ ", current_price=" + current_price + "]";
	}
}