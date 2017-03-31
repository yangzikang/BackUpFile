package p3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDB {
	public static void main(String srgs[]){
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		
			conn=DriverManager.getConnection("jdbc:odbc:StudentDB","sa","sa");
			Statement stmt = conn.createStatement();
			
			String sqlstr = "insert into StudentTable values ('072201','张学军',88)";
			stmt.executeUpdate(sqlstr);
			stmt.executeUpdate("insert into StudentTable values ('072202','丁勇',92)");
			
			ResultSet rs = stmt.executeQuery("select * from StudentTable");
			while(rs.next()){
				System.out.println("学号："+rs.getString("s_num")+"\t"+"姓名："+rs.getString("s_name")+"成绩："+rs.getInt("score"));
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
