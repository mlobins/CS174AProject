
import java.text.ParseException;
import java.util.Scanner;

public class Debug {

	public static void debugInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nOpen Market (0)\n" + "Close Market (1)\n" + "Set Price (2)\n" + "Exit (3)\n" + "Initiate Tables (4)\n");
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
				String stock_id = scanner.next();
				System.out.printf("stock_id= %s%n", stock_id);
				scanner.nextLine();
				System.out.println("Enter the new price of the stock:  ");
				double price = scanner.nextDouble();
				setPrice(stock_id, price);
				break;
			case 3:
				openMarket();
				control = 0;
				break;
			case 4:
				initTables();
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}
//YYYY/MM/dd
	public static void openMarket() {
		
		//Initializes date if not done so, uncomment!
		/*
		try {
			Globals.initializeDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		Globals.isMarketOpen = true;
		System.out.println("Market is open.");
		String updatedDate = Communications.getDateString();
		//System.out.printf("%s%n", updatedDate);
		Globals.setTodaysDate(updatedDate);
		System.out.printf("updated date = %s", updatedDate);
		// start day
	}

	public static void closeMarket() {
		Globals.isMarketOpen = false;
		Communications.updateStockClosingPrice();
		Communications.accrueInterest();
		System.out.println("Market is closed.");
		Communications.nextDay();
		// finish day
		
		
	}

	public static void setPrice(String stock_id, double price) {
		Communications.updateStock(stock_id, price);
	}
	
	public static void initTables(){

		//Communications.createStocks();
		//Communications.createTransactions();
			//	Communications.createActorDirectorProfile();

				
		
		
		//Communications.createCustomerProfile();

				//Communications.createMarketAccounts();	

				//Communications.createStockAccounts();

				//Communications.createDate(); already created


				//Communications.createContract();
	}

}