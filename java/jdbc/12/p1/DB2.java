package p1;
import java.sql.*;
public class DB2 {
	public static void main(String srgs[]){
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		
			conn=DriverManager.getConnection("jdbc:odbc:AdminDB","sa","sa");
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from AdminTable");
			while(rs.next()){
				System.out.println("管理员姓名"+rs.getString("name")+"\t"+"密码"+rs.getString("password"));
			}
			rs.close();
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