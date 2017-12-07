

public class ActorDirectorProfile {
	
	private int ad_id;
	private String ad_name;
	private int dob;
	
	public int getADID() {
		return ad_id;
	}
	
	public void setADID(int ad_id) {
		this.ad_id = ad_id;
	}
	
	public String getADName() {
		return ad_name;
	}
	
	public void setADName(String ad_name) {
		this.ad_name = ad_name;
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
		return "Name: " + ad_name + ", DOB: " + dob;
	}
	
	//store contracts in sql database
	//how to get them and display output?
}