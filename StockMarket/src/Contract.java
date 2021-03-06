

	public class Contract {
		
		private int ad_id;
		private int contract_id;
		private String movie_title;
		private String role;
		private int year;
		private int total_payment;

		public int getADID() {
			return ad_id;
		}
		
		public void setADID(int ad_id) {
			this.ad_id = ad_id;
		}
		
		public int getContractID() {
			return contract_id;
		}
		
		public void setContractID(int contract_id) {
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
			return "Contract [ad_id=" + ad_id + ", contract_id=" + contract_id + ", movie_title=" + movie_title + ", role="
					+ role + ", year=" + year + ", total_payment=" + total_payment + "]";
		}
	
	}