package p1;

import java.sql.*;
public class DeleteDBEx{
	public static void main(String agrs[]){
		try{
//�������������������ӣ�������Satement;
			Connection conn = null;
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				conn=DriverManager.getConnection("jdbc:odbc:AdminDB","sa","sa");
				Statement stmt = conn.createStatement();
				
				String sqlString="CREATE TABLE AdminTable (adminname char(10), password  char(10)) ";
				//ִ��SQL������
				
				stmt.executeUpdate(sqlString);
				
				//���йرգ�
				
				stmt.close();
				conn.close();
		}
		catch(SQLException e)
		{System.out.println("SQLException:"+e.getMessage());}	
	}
}
