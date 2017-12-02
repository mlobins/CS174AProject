import java.util.Scanner;

public class Trader {
	// trader deposit
	private static CustomerProfile customer = null;

	public static void traderInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Register (0) or Login? (1) Exit(2)");
			int choice = scanner.nextInt();
			if (choice == 0) {
				register();
			} else if (choice == 1) {
				login();
				trader();
			} else if (choice == 2) {
				control = 0;
			}
		}
	}

	private static void register() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username: ");
		String username = scanner.nextLine();
		System.out.println("Your username is " + username);

		System.out.println("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println("Your name is " + name);

		System.out.println("Enter your state: ");
		String state = scanner.nextLine();
		System.out.println("Your state is " + state);

		System.out.println("Enter your phone_number: ");
		String phone_number = scanner.nextLine();
		System.out.println("Your phone_number is " + phone_number);

		System.out.println("Enter your email_address: ");
		String email_address = scanner.nextLine();
		System.out.println("Your email_address is " + email_address);

		System.out.println("Enter your taxid: ");
		String taxid = scanner.nextLine();
		System.out.println("Your taxid is " + taxid);

		System.out.println("Enter your password: ");
		String password = scanner.nextLine();
		System.out.println("Your password is " + password);
		Communications.setCustomerProfile(username, name, state, phone_number, email_address, taxid, password);
		int account_mid = 0;
		int transID = 0; // for transaction tables?
		Communications.setMarketAccount(account_mid, 0, transID, username);
	}

	private static void login() {
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		customer = Communications.getCustomerProfile(username);
		System.out.println("Your username is " + username);
		// goto trader
		// add password functionality
		// get customer profile

	}

	private static void trader() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Deposit (0)\n" + "Withdraw (1)\n" + "Buy (2)\n" + "Sell (3)\n" + "Show Balance (4)\n"
					+ "Show Transaction History of stock account (5)\n" + "Show Profile (6)\n"
					+ "Delete Transactions (7)\n" + "Log Out (8)\n");
			int choice = scanner.nextInt();
			if (choice == 0) {
				System.out.println("Enter the amount you want to deposit: ");
				int deposit = scanner.nextInt();
				System.out.println("Deposit: " + deposit);
				deposit(deposit);
			} else if (choice == 1) {
				System.out.println("Enter the amount you want to withdraw: ");
				int withdraw = scanner.nextInt();
				System.out.println("Withdraw: " + withdraw);
				withdraw(withdraw);
			} else if (choice == 2) {
				System.out.println("What stock do you want to buy?: ");
				int stock_id = scanner.nextInt();
				System.out.println("How many stocks do you want to buy?: ");
				int stockQuantity = scanner.nextInt();
				buy(stock_id, stockQuantity);
			} else if (choice == 3) {
				System.out.println("What stock do you want to sell?: ");
				int stock_id = scanner.nextInt();
				System.out.println("How many stocks do you want to sell?: ");
				int stockQuantity = scanner.nextInt();
				sell(stock_id, stockQuantity);
			} else if (choice == 4) {
				showBalance();
			} else if (choice == 5) {
				System.out.println("Which stock history do you want to see?: ");
				int stock_id = scanner.nextInt();
				showTransactionHistory(stock_id);
			} else if (choice == 6) {
				System.out.println("Which profile do you want to see?: ");
				int stock_id = scanner.nextInt();
				showProfile(stock_id);
			} else if (choice == 7) {
				movieInfo(0);
			} else if (choice == 8) {
				control = 0;
			}
		}
	}

	private static void deposit(int deposit) {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		int change = marketAccount.getBalance() + deposit; // check for below 0 balance
		Communications.updateMarketAccount(marketAccount.getAccountMID(), marketAccount.getBalance(),
				marketAccount.getTransID(), marketAccount.getUsername(), change);
	}

	private static void withdraw(int withdraw) {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		int change = marketAccount.getBalance() + withdraw; // check for below 0 balance
		Communications.updateMarketAccount(marketAccount.getAccountMID(), marketAccount.getBalance(),
				marketAccount.getTransID(), marketAccount.getUsername(), change);
	}

	private static void buy(int stock_id, int stockQuantity) {
		Stocks stock = Communications.getStock(stock_id);
		int price = stock.getCurrentPrice() * stockQuantity + 20;

		StockAccounts stockAccount = Communications.getStockAccount(customer.getUsername());
		if (stockAccount == null) {
			Communications.setStockAccount(0, 0, 0, 0, 0, 0, "0"); // add stats
			stockAccount = Communications.getStockAccount(customer.getUsername()); // multiple stock accounts possible
		} else {
			// Communications.updateStockAccount();
		}
		withdraw(price);
	}

	private static void sell(int stock_id, int stockQuantity) {
		Stocks stock = Communications.getStock(stock_id);
		int price = stock.getCurrentPrice() * stockQuantity - 20;

		StockAccounts stockAccount = Communications.getStockAccount(customer.getUsername());
		if (stockAccount == null) {
			Communications.setStockAccount(0, 0, 0, 0, 0, 0, "0"); // add stats
			stockAccount = Communications.getStockAccount(customer.getUsername());
		} else {
			// Communications.updateStockAccount();
		}
		deposit(price);

	}

	private static void showBalance() {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		System.out.println("Current Balance: $" + marketAccount.getBalance());
	}

	private static void showTransactionHistory(int stock_id) {
	}

	private static void showProfile(int stock_id) {
		Stocks stock = Communications.getStock(stock_id);
		System.out.println("Current Price: " + stock.getCurrentPrice());
		ActorDirectorProfile profile = Communications.getActorDirectorProfile(stock_id);
		System.out.println(profile.toString());
	}

	private static void movieInfo(int username) {
		System.out.println("HOY");
		// topmovies?
		// reviews?
	}
}