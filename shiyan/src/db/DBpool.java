package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBpool {
	private static Connection conn;
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
	public static final String DBUSER = "root";
	public static final String DBPASS = "root";

	public static Connection getConnection() {
		try {
			Class.forName(DBDRIVER).newInstance();
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			//System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接错误");

		}

		return conn;
	}

	public static void main(String[] args) {
		DBpool.getConnection();
	}
}
