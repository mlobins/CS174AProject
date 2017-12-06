
import java.util.List;
import java.util.Scanner;

public class Trader {
	// trader deposit
	private static CustomerProfile customer = null;
	static int buyType = 1;
	static int sellType = 2;
	static int depositType = 3;
	static int withdrawType = 4;
	static int accrue_interestType = 5;

	public static void traderInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nRegister (0)\n" + "Login? (1)\n" + "Exit(2)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case 0:
				register();
				break;
			case 1:
				control = login();
				trader();
				break;
			case 2:
				control = 0;
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	private static void register() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username: ");
		String username = scanner.nextLine();

		System.out.println("Enter your name: ");
		String name = scanner.nextLine();

		System.out.println("Enter your state: ");
		String state = scanner.nextLine();

		System.out.println("Enter your phone_number: ");
		String phone_number = scanner.nextLine();

		System.out.println("Enter your email_address: ");
		String email_address = scanner.nextLine();

		System.out.println("Enter your taxid: ");
		String taxid = scanner.nextLine();

		System.out.println("Enter your password: ");
		String password = scanner.nextLine();
		Communications.setCustomerProfile(username, name, state, phone_number, email_address, taxid, password);
		customer = Communications.getCustomerProfile(username);
		Communications.setMarketAccount(0, username);
		deposit(1000, false);
		customer = null;
	}

	private static int login() {
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		customer = Communications.getCustomerProfile(username);
		System.out.println("Your username is " + username);
		if (username == customer.getUsername()) {// && password == customer.getPassword()) {
			return 1;
		} else
			return 0;
		// add password functionality
		// any username can be inputted check!
	}

	public static void trader() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("\nDeposit, Withdraw, Buy, and Sell are unavailable if the market is closed.");
			System.out.println("The market is " + MarketControl.isMarketOpenText());
			System.out.println("Deposit (0)\n" + "Withdraw (1)\n" + "Buy (2)\n" + "Sell (3)\n" + "Show Balance (4)\n"
					+ "Show Transaction History of Stock Account (5)\n" + "Show Profile (6)\n" + "Movie Info (7)\n"
					+ "Log Out (8)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case (0):
				if (MarketControl.isMarketOpen() == true) {
					System.out.println("Enter the amount you want to deposit: ");
					double deposit = scanner.nextInt();
					System.out.println("Deposit: " + deposit);
					deposit(deposit, false);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (1):
				if (MarketControl.isMarketOpen() == true) {
					System.out.println("Enter the amount you want to deposit: ");
					double withdraw = scanner.nextInt();
					System.out.println("Deposit: " + withdraw);
					withdraw(withdraw, false);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (2):
				if (MarketControl.isMarketOpen() == true) {
					buyDisplay();
					System.out.println("How many stocks do you want to buy?: ");
					double stockQuantity = scanner.nextInt();
					scanner.nextLine();
					System.out.println("What stock do you want to buy?: ");
					String stock_id2 = scanner.nextLine();
					buy(stock_id2, stockQuantity);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (3):
				if (MarketControl.isMarketOpen() == true) {
					sellDisplay();
					System.out.println("How many stocks do you want to sell?: ");
					double stockQuantity = scanner.nextInt();
					System.out.println("Enter Buying Price.");
					double buyingPrice = scanner.nextInt();
					scanner.nextLine();
					System.out.println("What stock do you want to sell?: ");
					String stock_id = scanner.nextLine();
					sell(stock_id, stockQuantity, buyingPrice);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (4):
				showBalance();
				break;
			case (5):
				System.out.println("Which stock account history do you want to see?: ");
				String stock_id = scanner.nextLine();
				showTransactionHistory(stock_id);
				break;
			case (6):
				System.out.println("Which profile do you want to see?: ");
				String stock_id6 = scanner.nextLine();
				showProfile(stock_id6);
				break;
			case (7):
				movieInfo();
				break;
			case (8):
				control = 0;
				customer = null;
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	private static void deposit(double deposit, boolean buySell) {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		double change = marketAccount.getBalance() + deposit; // check for below 0 balance
		Communications.updateMarketAccount(marketAccount.getUsername(), change);
		if (buySell == false) {
			Communications.insertTransactionDeposit(depositType, change, customer.getUsername());
		}
	}

	private static void withdraw(double withdraw, boolean buySell) {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		double change = marketAccount.getBalance() + withdraw; // check for below 0 balance
		Communications.updateMarketAccount(marketAccount.getUsername(), change);
		if (buySell == false) {
			Communications.insertTransactionWithdraw(withdrawType, change, customer.getUsername());
		}
	}

	private static void buy(String stock_id, double stockQuantity) {
		Stocks stock = Communications.getStock(stock_id);
		double price = stock.getCurrentPrice() * stockQuantity + 20;

		Communications.setStockAccount(stock_id, stockQuantity, stock.getCurrentPrice(), 0, customer.getUsername());
		Communications.insertTransactionBuy(buyType, stock.getCurrentPrice(), stockQuantity, customer.getUsername());
		withdraw(price, true);
	}

	private static void buyDisplay() {
		List<Stocks> stocks = Communications.getStocks();
		System.out.println("----------------------------");
		for (int i = 0; i < stocks.size(); i++) {
			System.out.println("Stock ID: 				" + stocks.get(i).getStockID());
			System.out.println("Actor/Director Name: 	" + stocks.get(i).getADName());
			System.out.println("Current Price: 			" + stocks.get(i).getCurrentPrice());
			System.out.println("----------------------------");
		}
	}

	private static void sellDisplay() {
		List<StockAccounts> stockAccounts = Communications.getStockAccounts(customer.getUsername());
		System.out.println("----------------------------");
		for (int i = 0; i < stockAccounts.size(); i++) {
			System.out.println("Stock ID: 		" + stockAccounts.get(i).getAccountSID());
			System.out.println("Buying Price: 	" + stockAccounts.get(i).getBuyingPrice());
			System.out.println("Stock Quantity: " + stockAccounts.get(i).getBalance());
			System.out.println("----------------------------");
		}
	}

	private static void sell(String stock_id, double stockQuantity, double buying_price) {
		Stocks stock = Communications.getStock(stock_id);
		double price = stock.getCurrentPrice() * stockQuantity - 20;
		double balance = Communications.getStockAccount(customer.getUsername(), stock_id, buying_price).getBalance()
				- stockQuantity;
		// enter buy price manually
		// update stockaccount to hold new balance, selling price
		// check if sell amount is valid

		Communications.updateStockAccountSell(stock.getCurrentPrice(), buying_price, balance, customer.getUsername());
		Communications.insertTransactionSell(sellType, stock.getCurrentPrice(), stockQuantity, customer.getUsername());
		deposit(price, true);

	}

	private static void showBalance() {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		System.out.println("Current Balance: $" + marketAccount.getBalance());
	}

	private static void showTransactionHistory(String stock_id) {
		List<Transaction> transactions = Communications.getTransactionsHistory(customer.getUsername());
		System.out.println("----------------------------");
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println("Transaction ID: 		" + transactions.get(i).getTransID());
			System.out.println("Username: 				" + transactions.get(i).getUsername());
			System.out.println("Transaction Type: 		" + transactions.get(i).getTransactionType());
			System.out.println("Stock ID: 				" + transactions.get(i).getStockID());
			System.out.println("Stock Quantity: 		" + transactions.get(i).getStockQuantity());
			System.out.println("Buying Price: 			" + transactions.get(i).getBuyingPrice());
			System.out.println("Selling Price: 			" + transactions.get(i).getSellingPrice());
			System.out.println("Deposit: 				" + transactions.get(i).getDeposit());
			System.out.println("Withdraw: 				" + transactions.get(i).getWithdraw());
			System.out.println("Accrue Interest: 		" + transactions.get(i).getAccrueInterest());
			System.out.println("Date Of Transaction: 	" + transactions.get(i).getDateOfTransaction());
			System.out.println("----------------------------");
		}
	}

	private static void showProfile(String stock_id) {
		Stocks stock = Communications.getStock(stock_id);
		System.out.println("Current Price: $" + stock.getCurrentPrice());
		ActorDirectorProfile profile = Communications.getActorDirectorProfile(stock_id);
		System.out.println(profile.toString());
	}

	private static void movieInfo() {
		Movies.movieInit();
	}
}