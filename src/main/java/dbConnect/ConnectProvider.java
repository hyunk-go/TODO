package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectProvider {
	private static String dburl = "jdbc:mysql://localhost:3306/todolist";
	private static String dbUser = "todouser"; //input dbUser
	private static String dbpasswd = "todo123!@#";  // input dbPasswd

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, dbUser, dbpasswd);
	}
}
