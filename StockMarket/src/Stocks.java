

public class Stocks {

	private String stock_id;
	private int ad_id;
	private double closing_price;
	private double current_price;

	public String getStockID() {
		return stock_id;
	}

	public void setStockID(String stock_id) {
		this.stock_id = stock_id;
	}
	
	public int getADID() {
		return ad_id;
	}
	
	public void setADID(int ad_id) {
		this.ad_id = ad_id;
	}

	public double getClosingPrice() {
		return closing_price;
	}

	public void setClosingPrice(double closing_price) {
		this.closing_price = closing_price;
	}

	public double getCurrentPrice() {
		return current_price;
	}

	public void setCurrentPrice(double current_price) {
		this.current_price = current_price;
	}

	// toString()
	@Override
	public String toString() {
		return "Stocks [stock_id=" + stock_id + ", closing_price=" + closing_price
				+ ", current_price=" + current_price + "]";
	}
}