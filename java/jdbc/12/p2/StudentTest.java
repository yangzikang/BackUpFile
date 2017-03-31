package p2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTest {
	public static void main(String args[]){
		DBConn db=new DBConn();
		/*String sql="insert into student values ('123','Tom',19)";
		db.executeUpdate(sql);
		db.close();*/
		/*String sql="delete from student where s_id='123'";
		db.executeUpdate(sql);
		db.close();*/
		
		/*Student stu= new Student();
		String sql="select * from student";
		ResultSet rs= db.executeQuery(sql);
		try {
			while(rs.next()){
				stu.setId(rs.getString("s_id"));
				stu.setName(rs.getString("s_nam"));
				stu.setAge(rs.getInt("s_age"));
				System.out.println(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();	*/
		String sql="update student set s_age=300 where s_nam='Tom'";
		db.executeUpdate(sql);
		Student stu= new Student();
		String sql2="select * from student";
		ResultSet rs= db.executeQuery(sql2);
		try {
			while(rs.next()){
				stu.setId(rs.getString("s_id"));
				stu.setName(rs.getString("s_nam"));
				stu.setAge(rs.getInt("s_age"));
				System.out.println(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		
	}
}
