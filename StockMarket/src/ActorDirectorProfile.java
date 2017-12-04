

public class ActorDirectorProfile {
	
	private String ad_name;
	private String stock_id;
	private int dob;
	
	public String getADName() {
		return ad_name;
	}
	
	public void setADName(String ad_name) {
		this.ad_name = ad_name;
	}
	
	public String getStockID() {
		return stock_id;
	}
	
	public void setStockID(String stock_id) {
		this.stock_id = stock_id;
	}
	
	public int getDOB() {
		return dob;
	}
	
	public void setDOB(int dob) {
		this.dob = dob;
	}
	
	//toString()
	@Override
	public String toString() {
		//return "ActorDirectorProfile [ad_name=" + ad_name + ", stock_id=" + stock_id + ", dob="
				//+ dob + ", contract_id=" + contract_id + "]";
		return "Name: " + ad_name + ", Stock_id: " + stock_id + ", DOB: " + dob;
	}
	
	//store contracts in sql database
	//how to get them and display output?
}