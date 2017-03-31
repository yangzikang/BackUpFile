package im;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class denglu extends Frame{
	TextField zhanghao;
	TextField mima;
	public static void main(String args[]){
		new denglu().chuangjian();
	} 
	
	public void chuangjian(){
		Label zh = new Label("ÕËºÅ");
		Label mm = new Label("ÃÜÂë");
		zhanghao=new TextField();
		mima=new TextField();
		
		Button Bdenglu = new Button("µÇÂ½");
		Button Bzhuce = new Button("×¢²á");
		xiangying xy=new xiangying(this);
		Bdenglu.addActionListener(xy);
		Bzhuce.addActionListener(xy);
		setLocation(400, 300);
		
		setLayout(new GridLayout(3,2));
		
		addWindowListener(new guanbi1());
		
		add(zh);
		add(zhanghao);
		add(mm);
		add(mima);
		
		add(Bdenglu);
		add(Bzhuce);
		pack();
		
		setVisible(true);
	}
}

class xiangying implements ActionListener{
	private denglu dl;
	
	public xiangying(denglu dl){
		this.dl=dl;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="µÇÂ½"){
			String jieguo = null;
			System.out.println(dl.zhanghao.getText());
			System.out.println(dl.mima.getText());
			tongxin a=new tongxin();
			try {
				jieguo=a.getdenglu(dl.zhanghao.getText(),dl.mima.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dl.setVisible(false);
			if(jieguo.equals("no")){
				JOptionPane.showMessageDialog(null, "ÕËºÅ»òÃÜÂë²»¶Ô°¥£¡");
				
				new denglu().chuangjian();
			}
			else{
				zhujiemian hhh=new zhujiemian();
				hhh.liebiao(jieguo);
				/*ChatClient yonghu1=new ChatClient();
				yonghu1.launchFrame(jieguo);*/
			}
					
		}
		else if(e.getActionCommand()=="×¢²á"){
			dl.setVisible(false);
			new zhuce().chuangjian();
		}
	}
}

class guanbi1 extends WindowAdapter {
  	public void windowClosing(WindowEvent e) {
  		System.exit(0);
  	}
 }