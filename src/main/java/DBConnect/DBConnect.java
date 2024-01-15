package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	public static void main(String[] args) {
		
	}
	public static Connection dbConnect() throws SQLException {
		String url = "jdbc:mariadb://172.30.1.16:3306/zerobase";
		String dbUserId = "zerobase";
		String dbPassword = "zerobase";
		
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	public static void close (Connection connection, PreparedStatement preparedStatement, ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
