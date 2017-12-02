
public class ActorDirectorProfile {
	
	private String ad_name;
	private String stock_id;
	private int dob;
	private String contract_id;
	//switch to array for contracts?

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
	
	public String getContract_ID() {
		return contract_id;
	}
	
	public void setContract_ID(String contract_id) {
		this.contract_id = contract_id;
	}
	
	//toString()
	@Override
	public String toString() {
		//return "ActorDirectorProfile [ad_name=" + ad_name + ", stock_id=" + stock_id + ", dob="
				//+ dob + ", contract_id=" + contract_id + "]";
		String hoy = "Name: " + ad_name + ", Stock_id: " + stock_id + ", DOB: "
				+ dob;
		hoy = hoy + "\n" + showContracts();
		return hoy;
	}
	
	public String showContracts() {
		String contracts = "";
		for (int i = 0; i < 10; i++) {
			//contracts = contracts + "\n" + Contract.toString();
		}
		return contracts;
	}
	
	public class Contract {
			//add actordirector name
			//remove contractid from actordirector
		private int contract_id;
		private String movie_title;
		private String role;
		private int year;
		private int total_payment;
		
		public int getStockID() {
			return contract_id;
		}
		
		public void setStockID(int contract_id) {
			this.contract_id = contract_id;
		}
		
		public String getMovieTitle() {
			return movie_title;
		}
		
		public void setMovieTitle(String movie_title) {
			this.movie_title = movie_title;
		}
		
		public String getRole() {
			return role;
		}
		
		public void setRole(String role) {
			this.role = role;
		}
		
		public int getYear() {
			return year;
		}
		
		public void setYear(int year) {
			this.year = year;
		}
		
		public int getTotalPayment() {
			return total_payment;
		}
		
		public void setTotalPayment(int total_payment) {
			this.total_payment = total_payment;
		}
		
		//toString()
		@Override
		public String toString() {
			return "Contract [contract_id=" + contract_id + ", movie_title=" + movie_title + ", role="
					+ role + ", year=" + year + ", total_payment=" + total_payment + "]";
		}
	
	}
}