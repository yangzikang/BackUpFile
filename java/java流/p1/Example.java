package p1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
public class Example extends JFrame implements ActionListener{

	JTextArea ta;
	JButton readbtn,writebtn,clearbtn;
	JPanel p=new JPanel();
	public Example(){
		ta=new JTextArea(10,20);
		readbtn=new JButton("读取文件");
		writebtn=new JButton("写入文件");
		clearbtn=new JButton("清空文本区");
		add(ta,"Center");
		add(p,"South");
		p.add(readbtn);
		p.add(writebtn);
		p.add(clearbtn);
		readbtn.addActionListener(this);
		writebtn.addActionListener(this);
		clearbtn.addActionListener(this);
		this.setSize(500, 500);
		this.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clearbtn){
			ta.setText("");
		}
		else if(e.getSource()==readbtn){
			FileInputStream fin;
			try{
				fin = new FileInputStream("D://test.txt");
				byte buffer[]=new byte[5];
				String s;
				while(true){
					int count=fin.read(buffer);
					if(count==-1){
						break;
					}
					for(int i=0;i<count;i++){
						ta.append((char)buffer[i]+"");
					}				
					/*if(count==5){
						s= new String(buffer);
						ta.append(s);
					}
					else{
						for(int i=0;i<count;i++){
							ta.append((char)buffer[i]+"");
						}						
					}*/
				}
				fin.close();
			}catch(Exception e1){}

		}
		else if(e.getSource()==writebtn){
			FileOutputStream fout;
			try {
				fout = new FileOutputStream("D://test.txt");
			
				String str=ta.getText();
				byte[] srtbyte = str.getBytes();
			
				fout.write(srtbyte);
				fout.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Example();
	}

}

