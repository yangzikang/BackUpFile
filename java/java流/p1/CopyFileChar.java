package p1;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class CopyFileChar extends JFrame implements ActionListener{
  JTextField t1=new JTextField(30);
  JTextField t2=new JTextField(30);
  JButton btn=new JButton("复制");
  CopyFileChar(){
    JPanel p1=new JPanel();
    p1.add(new JLabel("源文件："));  p1.add(t1);  
    JPanel p2=new JPanel();
    //声明创建“目标文件”并进行布局
    p2.add(new JLabel("目标文件：")); p2.add(t2);
    JPanel  p3=new JPanel();
    p3.add(btn);
    this.getContentPane().setLayout(new GridLayout(3,1));  
    this.getContentPane().add(p1);
    //增加p2,p3
    this.getContentPane().add(p2);
    this.getContentPane().add(p3);           
    btn.addActionListener(this);
    setTitle("用字符流复制文件");
    setSize(450,150);
    setVisible(true);

}
    public void actionPerformed(ActionEvent e){
      try{
          File inputFile=new File(t1.getText());  
		File outputFile=new File(t2.getText());  
		FileReader in=new FileReader(inputFile); 
		//声明创建写出流对象out
		FileWriter out=new FileWriter(outputFile);
          int c;
          while((c=in.read())!=-1) 
           //将数据进行写出  
           out.write(c);                           
          in.close();
          out.close();

          JOptionPane.showMessageDialog(null,"复制成功！");  
        }catch(IOException ee){  System.err.println(ee);}
}
    public static void main(String[]  args)throws IOException{
                 JFrame.setDefaultLookAndFeelDecorated(true);
                  new CopyFileChar();

}
}
