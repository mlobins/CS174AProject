import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Globals {
	static boolean isMarketOpen = false;
	static String todaysDate;
	static int year, month, day;
	
	public static void initializeDate() throws ParseException{
		System.out.printf("Please enter today's date in format: YYYYMMDD\n");
		Scanner scanner = new Scanner(System.in);
		todaysDate = scanner.nextLine();
		Communications.setDate(todaysDate);
		
	}
	
	public static boolean isMarketOpen( ) {
		return isMarketOpen;
	}
	
	public String getTodaysDate() {
		return todaysDate;
	}
	
	public static int getYear() {
		return year;
	}
	
	public static void setYear(int Year){
		year = Year;
	}
	
	public static int getMonth() {
		return month;
	}
	
	public static void setMonth(int Month){
		month = Month;
	}
	
	public static int getDay() {
		return day;
	}
	
	public static void setDay(int Day){
		day = Day;
	}

	public static void setTodaysDate(String Date) {
		todaysDate = Date;
		String yearMonthDay[] = todaysDate.split("-");
		setYear(Integer.parseInt(yearMonthDay[0]));
		setMonth(Integer.parseInt(yearMonthDay[1]));
		setDay(Integer.parseInt(yearMonthDay[2]));

	}
}
