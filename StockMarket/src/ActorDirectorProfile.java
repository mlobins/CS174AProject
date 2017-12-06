

public class ActorDirectorProfile {
	
	private String ad_name;
	private int dob;
	
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