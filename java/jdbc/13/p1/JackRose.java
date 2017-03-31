package p1;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class JackRose extends JFrame implements ActionListener{
	JTextField jtf1;
	JTextField jtf2;
	JButton ok;
	JButton cancel;
	public JackRose(){
		ok=new JButton("确定");
		cancel=new JButton("取消");
		JLabel jl1= new JLabel("用户名");
		JLabel jl2= new JLabel("密码");
		jtf1=new JTextField(20);
		jtf2=new JTextField(20);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		this.setLayout(new GridLayout(3,2));
		add(jl1);
		add(jtf1);
		add(jl2);
		add(jtf2);
		add(ok);
		add(cancel);
		
		this.setSize(200,200);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok){
			DBConn dbc=new DBConn();
			String sql="select * from AdminTable;";
			ResultSet rs= dbc.executeQuery(sql);
			try {
				while(rs.next()){
					//System.out.println("管理员姓名"+rs.getString("name")+"\t"+"密码"+rs.getString("password"));
					if(rs.getString("name").equals(jtf1.getText())){
						if(rs.getString("password").equals(jtf2.getText())){
							JOptionPane.showMessageDialog(null, "登陆成功", "成功", JOptionPane.ERROR_MESSAGE);
						}
						else{JOptionPane.showMessageDialog(null, "登陆失败", "失败", JOptionPane.ERROR_MESSAGE);}
					}
				}
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dbc.close();		
			
			
		}
		else if(e.getSource()==cancel){
			JOptionPane.showMessageDialog(null, "cancel", "取消", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	public static void main(String args[]){
		new JackRose();
	}

}

class DBConn {
	String driveName="sun.jdbc.odbc.JdbcOdbcDriver";
	String url="jdbc:odbc:AdminDB";
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
