import java.util.Scanner;

public class Globals {
	static boolean isMarketOpen = false;
	static String todaysDate;
	public void initializeDate( ){
		System.out.printf("Please enter today's date in format\n 2008-12-30\n");
		Scanner scanner = new Scanner(System.in);
		todaysDate = scanner.nextLine();
		Communications.setDate(todaysDate);

	}
	
	public static boolean isMarketOpen( ) {
		return isMarketOpen;
	}
}
