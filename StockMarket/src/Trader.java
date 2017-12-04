
import java.util.Scanner;

public class Trader {
	// trader deposit
	private static CustomerProfile customer = null;
	static int account_SID = 0;
	static int account_MID = 0;
	static int trans_ID = 0;
	static int buyType = 1;
	static int sellType = 2;
	static int depositType = 3;
	static int withdrawType = 4;
	static int accrue_interestType = 5;

	public static void traderInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Register (0) or Login? (1) Exit(2)");
			int choice = scanner.nextInt();
			switch (choice) {
			case 0:
				register();
				break;
			case 1:
				login();
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
		scanner.close();
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
		Communications.setMarketAccount(account_MID, 0, trans_ID, username);
		deposit(1000, false);
		account_MID++;
		trans_ID++;
		scanner.close();
	}

	private static void login() {
		System.out.println("Enter your username: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		customer = Communications.getCustomerProfile(username);
		System.out.println("Your username is " + username);
		scanner.close();
		// goto trader
		// add password functionality
		// get customer profile

	}

	public static void trader() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Deposit, Withdraw, Buy, and Sell are unavailable if the market is closed.");
			System.out.println("Deposit (0)\n" + "Withdraw (1)\n" + "Buy (2)\n" + "Sell (3)\n" + "Show Balance (4)\n"
					+ "Show Transaction History of Stock Account (5)\n" + "Show Profile (6)\n" + "Movie Info (7)\n"
					+ "Log Out (8)\n");
			int choice = scanner.nextInt();
			switch (choice) {
			case (0):
				if (Globals.isMarketOpen() == true) {
					System.out.println("Enter the amount you want to deposit: ");
					double deposit = scanner.nextInt();
					System.out.println("Deposit: " + deposit);
					deposit(deposit, false);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (1):
				if (Globals.isMarketOpen() == true) {
					System.out.println("Enter the amount you want to deposit: ");
					double deposit = scanner.nextInt();
					System.out.println("Deposit: " + deposit);
					deposit(deposit, false);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (2):
				if (Globals.isMarketOpen() == true) {
					System.out.println("What stock do you want to buy?: ");
					String stock_id2 = scanner.nextLine();
					System.out.println("How many stocks do you want to buy?: ");
					double stockQuantity = scanner.nextInt();
					buy(stock_id2, stockQuantity);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (3):
				if (Globals.isMarketOpen() == true) {
					System.out.println("What stock do you want to sell?: ");
					String stock_id = scanner.nextLine();
					System.out.println("How many stocks do you want to sell?: ");
					double stockQuantity = scanner.nextInt();
					sell(stock_id, stockQuantity);
				} else {
					System.out.println("Market is not open.");
				}
				break;
			case (4):
				showBalance();
				break;
			case (5):
				System.out.println("Which stock history do you want to see?: ");
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
		Communications.updateMarketAccount(marketAccount.getAccountMID(), marketAccount.getTransID(),
				marketAccount.getUsername(), change);
		if (buySell == true) {
			Communications.updateTransactionDeposit(marketAccount.getAccountMID(), depositType, change,
					customer.getUsername());
		}
	}

	private static void withdraw(double withdraw, boolean buySell) {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		double change = marketAccount.getBalance() + withdraw; // check for below 0 balance
		Communications.updateMarketAccount(marketAccount.getAccountMID(), marketAccount.getTransID(),
				marketAccount.getUsername(), change);
		if (buySell == true) {
			Communications.updateTransactionWithdraw(marketAccount.getAccountMID(), withdrawType, change,
					customer.getUsername());
		}
	}

	private static void buy(String stock_id, double stockQuantity) {
		Stocks stock = Communications.getStock(stock_id);
		double price = stock.getCurrentPrice() * stockQuantity + 20;

		StockAccounts stockAccount = Communications.getStockAccount(customer.getUsername(), stock_id,
				stock.getCurrentPrice());
		if (stockAccount == null) {
			Communications.setStockAccount(account_SID, stock_id, stockQuantity, stock.getCurrentPrice(), 0, trans_ID,
					customer.getUsername()); // add stats
			account_SID++;
			trans_ID++;
			stockAccount = Communications.getStockAccount(customer.getUsername(), stock_id, stock.getCurrentPrice());
		}
		Communications.updateStockAccountBuy(stockAccount.getAccountSID(), stockAccount.getBuyingPrice(),
				stockAccount.getBalance(), customer.getUsername()); // buying price?
		Communications.updateTransactionBuy(stockAccount.getAccountSID(), buyType, stock.getCurrentPrice(),
				stockQuantity, customer.getUsername());
		withdraw(price, true);
	}

	private static void sell(String stock_id, double stockQuantity) {
		Stocks stock = Communications.getStock(stock_id);
		double price = stock.getCurrentPrice() * stockQuantity - 20;

		StockAccounts stockAccount = Communications.getStockAccount(customer.getUsername(), stock_id,
				stock.getCurrentPrice());
		Communications.updateStockAccountSell(stockAccount.getAccountSID(), stock.getCurrentPrice(),
				stockAccount.getBalance(), customer.getUsername());
		Communications.updateTransactionSell(stockAccount.getAccountSID(), sellType, stock.getCurrentPrice(),
				stockQuantity, customer.getUsername());
		deposit(price, true);

	}

	private static void showBalance() {
		MarketAccounts marketAccount = Communications.getMarketAccount(customer.getUsername());
		System.out.println("Current Balance: $" + marketAccount.getBalance());
	}

	private static void showTransactionHistory(String stock_id) {
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