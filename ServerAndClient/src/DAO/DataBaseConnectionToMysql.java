package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DataBaseConnectionToMysql implements IDatabaseConnection {
	private static String dbDriver = "com.mysql.jdbc.Driver";
	//private static String dbURL = "jdbc:Access:///" + Overall.path
	//		+ "/src/account.accdb";
	private static String dbURL = "jdbc:mysql://localhost:3306/mydb";
	private static String dbadmin = "root";
	private static String dbpassword = "82936200";
	private Connection conn = null;

	public  DataBaseConnectionToMysql() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbadmin, dbpassword);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库连接失d败", "提示",JOptionPane.PLAIN_MESSAGE);  
			e.printStackTrace();
			
		}
	}

	public Connection getConnection() throws Exception {
		return conn;
	}

	public boolean closeConnection() throws Exception {
		boolean Flag = false;
		if (conn != null) {
			try {
				conn.close();
				Flag = false;
			} catch (Exception E) {
				E.printStackTrace();
			}
		}
		return Flag;
	}

}
