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
		
		//System.out.printf("year = %d %n month =  %d %n  day = %d %n" ,getYear(), getMonth(), getDay());

	}
	
	public static double getNumberOfDaysInMonth(){
		double x;
		if((year % 4) == 0){
			x = 29;
		}
		else{
			x = 28; 
		}
		switch(month) {
		case 1:	return 31.0;
		case 2: return x;	// leap year
		case 3: return 31.0;
		case 4:	return 30.0;
		case 5: return 31.0;
		case 6: return 30.0;
		case 7: return 31.0;
		case 8: return 31.0;
		case 9: return 30.0;
		case 10: return 31.0;
		case 11: return 30.0;
		case 12: return 31.0;
		default: return 31.0;
		}
		
	}
}
