package p1;
import java.sql.*;
public class DB {
	public static void main(String srgs[]){
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		
			conn=DriverManager.getConnection("jdbc:odbc:AdminDB","sa","sa");
			Statement stmt = conn.createStatement();
			String sqlstr = "insert into AdminTable values ('Lily','012410')";
			stmt.executeUpdate(sqlstr);
			stmt.executeUpdate("insert into AdminTable values ('Jim','125674')");
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
