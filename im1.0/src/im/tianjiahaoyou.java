package im;
import java.awt.*;
import java.awt.event.*;;
public class tianjiahaoyou extends Frame {
	
	
	
	TextField haoyouming;
	String name1;
	public void tianhaoyou(String name){
		
		name1=name;
		Label zh = new Label("д��������");
		haoyouming=new TextField();
		Button bt=new Button("ȷ��");
		xin aa=new xin(this);
		bt.addActionListener(aa);
		add(zh);
		add(haoyouming);
		add(bt);
		setSize(200,200);
		setLocation(400, 300);
		setLayout(new GridLayout(3,2));
		setVisible(true);
		
	}
	
}
class xin implements ActionListener{
	tianjiahaoyou tjhy=new tianjiahaoyou();
	String fanhui=null;
	xin(tianjiahaoyou hh){
		tjhy=hh;
	}
	public void actionPerformed(ActionEvent e) {
		fanhui=tjhy.haoyouming.getText();
		System.out.println(fanhui);
		tjhy.setVisible(false);
		tongxin hh=new tongxin();
		try {
			hh.tianjiahaoyou(tjhy.name1,fanhui);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
 


