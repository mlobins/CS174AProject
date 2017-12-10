
public class CustomerProfile {
	
	private String name;
	private String state;
	private String phone_number;
	private String email_address;
	private String taxid;
	private String username;
	private String password;
	private String address; //ADDED AFTER TURN-IN
	private String ssn; //ADDED AFTER TURN-IN
 
	//ADDED AFTER TURN-IN
	public String getAddress() {
		return address;
	}
	
	//ADDED AFTER TURN-IN
	public void setAddress(String address) {
		this.address = address;
	}
	
	//ADDED AFTER TURN-IN
	public String getSSN() {
		return ssn;
	}
	
	//ADDED AFTER TURN-IN
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPhoneNumber() {
		return phone_number;
	}
	
	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getEmailAddress() {
		return email_address;
	}
	
	public void setEmailAddress(String email_address) {
		this.email_address = email_address;
	}
	
	public String getTaxID() {
		return taxid;
	}
	
	public void setTaxID(String taxid) {
		this.taxid = taxid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//toString()
	@Override
	public String toString() {
		
		return "CustomerProfile [name=" + name + ", state=" + state + ", phone_number="
				+ phone_number + ", email_address=" + email_address + ", taxid=" + taxid
				+ ", username=" + username + ", password=" + password + "]";
	}		
}