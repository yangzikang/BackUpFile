package im;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class zhuce extends Frame{
	
	TextField zhanghao;
	TextField mima;
	TextField xingming;
	TextField nianling;
	public void chuangjian(){
		Label zh = new Label("’À∫≈");
		Label mm = new Label("√‹¬Î");
		Label xm = new Label("–’√˚");
	
		zhanghao=new TextField();
		mima=new TextField();
		xingming=new TextField();
	
	
		Button Bzhuce = new Button("◊¢≤·");
		xiangying2 xy=new xiangying2(this);
		Bzhuce.addActionListener(xy);
		setLocation(400, 300);
		setLayout(new GridLayout(4,2));
		
		addWindowListener(new guanbi());
		
		add(zh);
		add(zhanghao);
		add(mm);
		add(mima);
		add(xm);
		add(xingming);
		
		add(Bzhuce);
		pack();
		
		setVisible(true);
	}
}

class xiangying2 implements ActionListener{
	private zhuce zc;
	yonghu a;
	String jieshouzhi=null;
	public xiangying2(zhuce zc){
		this.zc=zc;
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand()=="◊¢≤·"){
			//System.out.println(zc.zhanghao.getText());
			String zhanghao=zc.zhanghao.getText();
			//System.out.println(zc.mima.getText());
			String mima=zc.mima.getText();
			//System.out.println(zc.xingming.getText());
			String xingming=zc.xingming.getText();
			
			
			zc.setVisible(false);
			try {
				jieshouzhi=new tongxin().getzhuce(zhanghao,mima,xingming);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(jieshouzhi.equals("yes")){
				new denglu().chuangjian();
			}
			else if(jieshouzhi.equals("no")){
				JOptionPane.showMessageDialog(null, "’À∫≈÷ÿ∏¥¡À£°ªª∏ˆ’À∫≈‘Ÿ◊¢≤·∞…£°");
				new zhuce().chuangjian();
			}
			
			
		}
	}
}
class guanbi extends WindowAdapter {
  	public void windowClosing(WindowEvent e) {
  		System.exit(0);
  	}
 }
