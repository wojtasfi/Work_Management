package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private Connection con;

	
	public Connection Connect() throws Exception{

		
		if (con != null)
			return con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String Url = "jdbc:mysql://localhost:3306/work_management";
		con = DriverManager.getConnection(Url, "root", "pollop123");

		return con;
		
	}
	
	

}
