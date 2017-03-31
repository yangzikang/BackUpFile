package imServer;
import java.io.*;
import java.net.*;
import java.util.*;

import im.yonghu;




public class yonghuServer{
	List<yonghu> yonghus = new ArrayList<yonghu>();

	ServerSocket ys = null;
	public static void main(String args[])throws Exception{
		new yonghuServer().start();
	}
//******************************************************************************
	public void cwj() throws Exception{
		FileOutputStream out = null;
		DataOutputStream objOut = null;
		File file =new File("d:\\test.txt");
		
		out = new FileOutputStream(file);
		
        objOut=new DataOutputStream(out);
        String shuliang=Integer.toString(yonghus.size());
        
		objOut.writeUTF(shuliang);
		objOut.flush();
		
        for(int i=0;i<yonghus.size();i++){
			yonghu xin=yonghus.get(i);
			String str=xin.getXingming()+" "+xin.getZhanghao()+" "+xin.getMima()+" ";
			for(int j=0;j<xin.haoyou.size();j++){
				str=str+" "+xin.haoyou.get(j);
			}
	        objOut.writeUTF(str);
	        objOut.flush();  
	        System.out.println("write object success!");   
		}
		objOut.close();
	}
	public void dwj()throws Exception{
		File file =new File("d:\\test.txt");
        FileInputStream out=null;
        DataInputStream objOut = null;
        out = new FileInputStream(file);
		
        objOut=new DataInputStream(out);
        String shuliang = null;
		
		shuliang = objOut.readUTF();
		
		int b=Integer.parseInt(shuliang);
		for(int i=0;i<b;i++){
			String str=objOut.readUTF();
			String []hh=str.split("\\s+");
			System.out.println(hh[0]+" "+hh[1]+" "+hh[2]);  
			yonghu a=new yonghu();
			a.setXingming(hh[0]);
			a.setZhanghao(hh[1]);
			a.setMima(hh[2]);
			for(int i1=3;i1<hh.length;i1++){
				a.haoyou.add(hh[i1]);
			}
			
			yonghus.add(a);
		}
	
		objOut.close();	
	}
	
//****************************************************************************
	@SuppressWarnings("finally")
	public void start()throws Exception{
		
		try{
			dwj();
		}catch(Exception e){
			System.out.println("文件内没有信息");
		}finally{
			ys = new ServerSocket(6688);
			while(true){
				Socket s = ys.accept();
				yonghuClient yc =new yonghuClient(s);
				System.out.println("a client connected!");
				new Thread(yc).start();
			}
		}

	}
	
	//*********************内部类*********************
		class yonghuClient implements Runnable{
			private Socket s;
			private DataInputStream dis = null;
			private DataOutputStream dos = null;
		
			public yonghuClient(Socket s) throws Exception{
				this.s = s;
				
					dis = new DataInputStream(s.getInputStream());
					dos = new DataOutputStream(s.getOutputStream());
			}
			
			
			
			public void send(String str) {
				try {
					dos.writeUTF(str);
					
				} catch (IOException e) {
					
					System.out.println("对方退出了！我从List里面去掉了！");
					
				}
			}
			
			
			public void run() {
				try {
					while(true) {
						String str = dis.readUTF();
						System.out.println(str);
						String[] jiexi =str.split("\\s+");
						int a=jiexi.length;
						
						
						
						if(a==2){
							int jieguo=0;
							System.out.println("denglu");
							for(int i1=0; i1<yonghus.size(); i1++) {
								yonghu c1 = yonghus.get(i1);
								System.out.println(jiexi[0]+"和"+jiexi[1]);
								System.out.println("账号被找到"+c1.getZhanghao());
								System.out.println("密码被找到"+c1.getMima());
								if(jiexi[0].equals(c1.getZhanghao())){
									if(jiexi[1].equals(c1.getMima())){
										String denglufasong=c1.getXingming();
										this.send(denglufasong);
										jieguo=1;
									}
								}	
							}
							if(jieguo==0){
								String dog="no";
								this.send(dog);
							}
							
						}
//***********************************************************************************
						else if(a==1){
							String fanhui=null;
							int fsf=0;
							System.out.println("获取好友");
							System.out.println(jiexi[0]);
							for(int i = 0;i<yonghus.size();i++){
								yonghu yh1=yonghus.get(i);
								if(yh1.getXingming().equals(jiexi[0])){
									if(yh1.haoyou.size()!=0){
										fanhui=yh1.haoyou.get(0);
										fsf=1;
									}
									for(int j=1;j<yh1.haoyou.size();j++){
										fanhui=fanhui+" "+yh1.haoyou.get(j);
									}
									if(fsf==1){
										System.out.println(fanhui);
										cwj();
										this.send(fanhui);
									}
								}
							}
							System.out.println(fanhui);
							if(fanhui==null){
								this.send("no");
							}
							
						}
//***********************************************************************************
						else if(a==4){
							System.out.println("tianjiahaoyou");
							for(int i=0;i<yonghus.size();i++){
								yonghu xinyh=yonghus.get(i);
								if(xinyh.getXingming().equals(jiexi[0])){
									xinyh.haoyou.add(jiexi[1]);
									System.out.println("添加好友成功");
									cwj();
								}
							}
							
						}
//***********************************************************************************
						else if(a==3){
							System.out.println("zhuce");
							yonghu xinyonghu=new yonghu();
							xinyonghu.setXingming(jiexi[0]);
							xinyonghu.setZhanghao(jiexi[1]);
							xinyonghu.setMima(jiexi[2]);
							int js=0;
							for(int xh=0;xh<yonghus.size();xh++){
								yonghu cc = yonghus.get(xh);
								if(jiexi[1].equals(cc.getZhanghao())){
									this.send("no");
									js=1;
								}
							}
							if(js==0){
								yonghus.add(xinyonghu);
								System.out.println("注册成功");
								this.send("yes");
								cwj();
							}
						}
						
//***********************************************************************************
						
					}
				} catch (EOFException e) {
					System.out.println("Client closed!");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(dis != null) dis.close();
						if(dos != null) dos.close();
						if(s != null) s.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				}//finally
			}//run
			
		}//class
}
