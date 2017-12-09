
import java.text.ParseException;
import java.util.Scanner;

public class Debug {

	public static void debugInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nOpen Market (0)\n" + "Close Market (1)\n" + "Set Price (2)\n" + "Exit (3)\n"
					+ "Initiate Tables (4)\n" + "Initiate Stocks (5)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case 0:
				openMarket();
				break;
			case 1:
				closeMarket();
				break;
			case 2:
				System.out.println("What stock do you want to change?: ");
				String stock_id = scanner.nextLine();
				System.out.println("Enter the new price of the stock: ");
				double price = scanner.nextDouble();
				setPrice(stock_id, price);
				break;
			case 3:
				control = 0;
				break;
			case 4:
				initTables();
				break;
			case 5:
				initStocks();
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	// YYYY/MM/dd
	public static void openMarket() {
/*
		try {
			MarketControl.initializeDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		// Communications.createDate(); // need to move to all creates location
		MarketControl.isMarketOpen = true;
		System.out.println("Market is open.");
		String updatedDate = Communications.getDateString();
		System.out.printf("%s%n", updatedDate);
		MarketControl.setTodaysDate(updatedDate);
		System.out.printf("Updated date = %s\n", updatedDate);
		// start day
	}

	public static void closeMarket() {
		MarketControl.isMarketOpen = false;
		Communications.updateStockClosingPrice();
		Communications.accrueInterest();
		System.out.println("Market is closed.");
		Communications.nextDay();
		// finish day
	}

	public static void setPrice(String stock_id, double price) {
		Communications.updateStock(stock_id, price);
	}

	public static void initTables() {

		// Communications.createTransactions();
		 Communications.createActorDirectorProfile();
		 Communications.createContract();
		 //Communications.createStocks();

		// Communications.createCustomerProfile();

		// Communications.createMarketAccounts();

		// Communications.createStockAccounts();

		// Communications.createDate(); already created

	}
	
	public static void initStocks() {
		//Communications.setStocks(String stock_id, String ad_name, int closing_price, int current_price);
		//Communications.setActorDirectorProfile(String ad_name, String stock_id, int dob, String contract_id);
		//Communications.setContract(String ad_name, int contract_id, String movie_title, String role, int year, int total_payment);
		//Communications.setActorDirectorProfile("JOHN0", 2015);
		Communications.setStocks(1, "124", "JOHN0", 0, 5);
		
		//Communications.setActorDirectorProfile("JOHN1", 2015);
		Communications.setStocks(2, "125", "JOHN1", 0, 5);
		
		//Communications.setActorDirectorProfile("JOHN2", 2015);
		Communications.setStocks(3, "126", "JOHN2", 0, 5);
		
		//Communications.setActorDirectorProfile("JOHN3", 2015);
		Communications.setStocks(4, "127", "JOHN3", 0, 5);
		
		//Communications.setActorDirectorProfile("JOHN4", 2015);
		Communications.setStocks(5, "128", "JOHN4", 0, 5);
		/*
		Communications.setContract(1, "JOHN0", "THE BIG HOY 0", "role", 2017, 666);
		Communications.setContract(2, "JOHN1", "THE BIG HOY 1", "role", 2018, 666);
		Communications.setContract(3, "JOHN2", "THE BIG HOY 2", "role", 2019, 666);
		Communications.setContract(3, "JOHN2", "THE BIG HOY 3", "role", 2016, 666);
		Communications.setContract(4, "JOHN3", "THE BIG HOY 4", "role", 2015, 666);
		Communications.setContract(4, "JOHN3", "THE BIG HOY 5", "role", 2014, 666);
		Communications.setContract(4, "JOHN3", "THE BIG HOY 6", "role", 2013, 666);
		Communications.setContract(5, "JOHN4", "THE BIG HOY 7", "role", 2012, 666);
		Communications.setContract(5, "JOHN4", "THE BIG HOY 8", "role", 2011, 666);*/
	}

}