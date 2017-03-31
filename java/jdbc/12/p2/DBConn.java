package p2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	String driveName="sun.jdbc.odbc.JdbcOdbcDriver";
	String url="jdbc:odbc:Student";
	String userName="sa";
	String password="sa";
	Connection conn = null;
	Statement stmt =null;
	ResultSet rs=null;
	public DBConn(){
		try {
			Class.forName(driveName);
			conn=DriverManager.getConnection(url,userName,password);
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String sql){
		try {
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int executeUpdate(String sql){
		int rowCount=0;
		try {
			rowCount = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount=0;
	}
	public void close(){
		try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
