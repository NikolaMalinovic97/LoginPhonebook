package loginPhonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

private static ConnectionManager instance = null;
	
	private static final String USERNAME = "root"; //Add your username for MySQL connection
	private static final String PASSWORD = "sifran97"; //Add your password for MySQL connection
	private static final String CONN_STRING = "jdbc:mysql://localhost/simplelogin?useSSL=false&serverTimezone=UTC";
	
	private Connection connection = null;
	
	private ConnectionManager() {
		
	}

	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	public Connection getConenction() {
		if(connection == null) {
			if(openConnection()) {
				System.out.println("Konekcija otvorena!");
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}
	
	public void close() {
		System.out.println("Konekcija zatvorena!");
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
		}
	}
}
