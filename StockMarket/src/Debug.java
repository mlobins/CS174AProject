import java.util.Scanner;

public class Debug {

	public static void debugInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Open Market (0)\n" + "Close Market (1)\n" + "Set Price (2)\n" + "Set New Date (3)\n" + "Exit (4)");
			int choice = scanner.nextInt();
			if (choice == 0) {
				openMarket();
			} else if (choice == 1) {
				closeMarket();
			} else if (choice == 2) {
				System.out.println("What stock do you want to change?: ");
				String stock_id = scanner.nextLine();
				System.out.println("Enter the new price of the stock: ");
				double price = scanner.nextDouble();
				setPrice(stock_id, price);
			} else if (choice == 3) {
				System.out.println("Choose the new date: ");
				double date = scanner.nextDouble();
				setNewDate(date);
			} else if (choice == 3) {
				control = 0;
			}
		}
		scanner.close();
	}

	public static void openMarket() {
		GlobalVariables.isMarketOpen = true;
	}

	public static void closeMarket() {
		GlobalVariables.isMarketOpen = false;
	}

	public static void setPrice(String stock_id, double price) {
		Communications.updateStock(stock_id, price);
	}

	public static void setNewDate(double date) {
		GlobalVariables.date = date;
	}

}