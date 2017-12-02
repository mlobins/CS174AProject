import java.util.Scanner;


public class Trader {
	//trader deposit
	private static CustomerProfile customer = null;
	
  public static void traderInit()  {
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
  
  private static void register()  {
	  Communications.setCustomerProfile();
  }
  
  private static void login()  {
	  System.out.println("Enter your username: ");
	  Scanner scanner = new Scanner(System.in);
	  String username = scanner.nextLine();
	  customer = Communications.getCustomerProfile(username);
	  System.out.println("Your username is " + username);
	  //goto trader
	  //add password functionality
	  //get customer profile
  
  }
  
  private static void trader()  {
	  int control = 1;
	  Scanner scanner = new Scanner(System.in);
	  
	  while (control == 1) {
		  System.out.println("Deposit (0)\n"
		  					+ "Withdraw (1)\n"
		  					+ "Buy (2)\n"
		  					+ "Sell (3)\n"
		  					+ "Show Balance (4)\n"
		  					+ "Current Price (5)\n"
		  					+ "Delete Transactions (6)\n"
		  					+ "Log Out (7)\n");
		  int choice = scanner.nextInt();
		  if (choice == 0) {
			  deposit();
		  } else if (choice == 1) {
			  withdraw(0);
		  } else if (choice == 2) {
			  buy(0);
		  } else if (choice == 3) {
			  sell(0);
		  } else if (choice == 4) {
			  showBalance(0);
		  } else if (choice == 5) {
			  currentPrice(0);
		  } else if (choice == 6) {
			  movieInfo(0);
		  } else if (choice == 7) {
			  control = 0;
		  }  
	  }
  }
  
  private static void deposit()  {
	  System.out.println("Enter the amount you want to deposit: ");
	  Scanner scanner = new Scanner(System.in);
	  int deposit = scanner.nextInt();
	  System.out.println("Deposit: " + deposit);
	  
	  MarketAccounts marketAccount = Communications.getMarketAccount(0); //must be associated with customer (one logged in as)
	  int change = marketAccount.getBalance() - deposit; //check for below 0 balance
	  Communications.updateMarketAccount(marketAccount.getAccountMID(), marketAccount.getBalance(), marketAccount.getTransID(), marketAccount.getUsername(), change);
	  
  }
  
  private static void withdraw(int username)  {
	  System.out.println("HOY");
  
  }
  
  private static void buy(int username)  {
	  System.out.println("HOY");
  
  }
  
  private static void sell(int username)  {
	  System.out.println("HOY");
  
  } 
  
  private static void showBalance(int username)  {
	  System.out.println("HOY");
  
  }
  
  private static void currentPrice(int username)  {
	  System.out.println("HOY");
  
  }
  
  private static void movieInfo(int username)  {
	  System.out.println("HOY");
	  //topmovies?
	  //reviews?
  }
}