package im;
import java.net.*;
import java.io.*;
import java.util.*;
public class tongxin {
	public String getzhuce(String zhanghao,String mima,String xingming) throws Exception{
		yonghu yh = new yonghu();
		yh.setZhanghao(zhanghao);
		yh.setXingming(xingming);
		yh.setMima(mima);
		String zhuce=yh.getXingming()+" "+yh.getZhanghao()+" "+yh.getMima();
		sockettongxin stx=new sockettongxin();
		stx.denglu();
		stx.kehuduan(zhuce);
		String fanhuizhi=stx.jieshou();
		return fanhuizhi;
		
		/*System.out.println("ssssssss"+js);*/
		//stx.closeit();
		
	}
	public String getdenglu(String zhanghao,String mima) throws Exception{
		yonghu dl = new yonghu();
		dl.setZhanghao(zhanghao);
		dl.setMima(mima);
		String denglu=dl.getZhanghao()+" "+dl.getMima();
		sockettongxin tx=new sockettongxin();
		tx.denglu();
		tx.kehuduan(denglu);
		String fanhui=tx.jieshou();
		return fanhui;
		//tx.closeit();
	}
	public String haoyou(String jieshou)throws Exception{
		String sender=jieshou;
		sockettongxin xx=new sockettongxin();
		xx.denglu();
		xx.kehuduan(sender);
		String fanhui=xx.jieshou();
		return fanhui;
		
	}
	public void tianjiahaoyou(String a,String b)throws Exception{
		String sender=a+" "+b+" "+"meiyong"+" "+"meiyong";
		sockettongxin xx=new sockettongxin();
		xx.denglu();
		xx.kehuduan(sender);	
	}
}

class sockettongxin{
		
		Socket yhs=null;
		
		DataOutputStream op = null;
		DataInputStream ip =null;
	public void denglu()throws Exception{
		yhs= new Socket("127.0.0.1",6688);
		op=new DataOutputStream(yhs.getOutputStream());
		ip=new DataInputStream(yhs.getInputStream());
	}
	public void kehuduan(String yonghuxinxi)throws Exception{
		
		
		op.writeUTF(yonghuxinxi);
	}
	public String jieshou()throws Exception{
		
		System.out.println("等待消息中");
		String str = ip.readUTF();
		System.out.println("收到："+str);
		return str;
	}
	public void closeit()throws Exception{
		yhs.close();
	}

}
