package im;
import java.awt.*;
import java.awt.event.*;


public class zhujiemian extends Frame{
		String yhm;
	public void liebiao(String yhming){
		yhm=yhming;
		xiangying3 xy=new xiangying3(this);
		
		String hy=null;
		try {
			hy=new tongxin().haoyou(yhming);
		} catch (Exception e) {
			System.out.println("error");
		}
		if(hy.equals("no")){
			System.out.println("没有好友");
		}
		else{
			String []jiexi=hy.split("\\s+");
			int shu=jiexi.length;
			for(int i=0;i<shu;i++){
				Button a= new Button(jiexi[i]);
				a.addActionListener(xy);
				add(a);
			}
		}
		
		
		setTitle(yhm+"的主界面");
		Button qun=new Button("群聊");
		qun.addActionListener(xy);
		add(qun);
		Button haoyou=new Button("添加好友");
		haoyou.addActionListener(xy);
		add(haoyou);
		pack();
		setSize(100,400);
		setLocation(1000, 70);
		
		setLayout(new GridLayout(4,3));
		setVisible(true);
		addWindowListener(new guanbi2());
	}
	
}

class xiangying3 implements ActionListener{
	
	
	String jieshouzhi=null;
	private zhujiemian zhujiemian;
	
	public xiangying3(zhujiemian zhujiemian) {
		this.zhujiemian=zhujiemian;
	}
	public void actionPerformed(ActionEvent e) {
		try {
			String fanhui=new tongxin().haoyou(zhujiemian.yhm);
			String jiexi[]=fanhui.split("\\s+");
			for(int i=0;i<jiexi.length;i++){
				if(e.getActionCommand().equals(jiexi[i])){
					ChatClient cc=new ChatClient();
					cc.lauchFrame(zhujiemian.yhm,jiexi[i]);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getActionCommand()=="群聊"){
			ChatClient cc=new ChatClient();
			cc.launchFrame(zhujiemian.yhm);
			//zhujiemian.setVisible(false);
		}
		else if(e.getActionCommand()=="添加好友"){
			//zhujiemian.setVisible(false);
			tianjiahaoyou tjhaoyou=new tianjiahaoyou();
			tjhaoyou.tianhaoyou(zhujiemian.yhm);
			
		}
		
	}
}

class guanbi2 extends WindowAdapter {
  	public void windowClosing(WindowEvent e) {
  		System.exit(0);
  	}
 }