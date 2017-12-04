
//Step 1: Use interfaces from java.sql package 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCMySQLConnection {
	//static reference to itself
	private static JDBCMySQLConnection instance = new JDBCMySQLConnection();
	public static final String URL = "jdbc:mysql://cs174a.engr.ucsb.edu:3306/mlobinsDB";
	public static final String USER = "mlobins";
	public static final String PASSWORD = "442";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 

	private static JDBCMySQLConnection instanceMOV = new JDBCMySQLConnection();
	public static final String URLMOV = "jdbc:mysql://cs174a.engr.ucsb.edu:3306/moviesDB";
	
	//private constructor
	private JDBCMySQLConnection() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}	
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
	
	private Connection createConnectionMOV() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URLMOV, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}	
	
	public static Connection getConnectionMOV() {
		return instanceMOV.createConnectionMOV();
	}
	
	
	

}
