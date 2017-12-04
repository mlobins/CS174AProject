
import java.util.Scanner;

public class Debug {

	public static void debugInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nOpen Market (0)\n" + "Close Market (1)\n" + "Set Price (2)\n" + "Exit (3)\n");
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
				openMarket();
				control = 0;
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	public static void openMarket() {
		Globals.isMarketOpen = true;
		System.out.println("Market is open.");
		// start day
	}

	public static void closeMarket() {
		Globals.isMarketOpen = false;
		Communications.updateStockClosingPrice();
		System.out.println("Market is closed.");
		// finish day
	}

	public static void setPrice(String stock_id, double price) {
		Communications.updateStock(stock_id, price);
	}

}