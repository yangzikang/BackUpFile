package newp;


import java.awt.*;
import java.applet.*;
public class ImageShowTest extends Applet implements Runnable{
	public Image picture;//定义类型为Image的成员变量
	public String imagearray[]={"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg"};
	public Thread mythread;
	public int n=0;
	public void init(){
		
	    mythread=new Thread(this);
	    mythread.start();
	}//装载图像
	public void paint(Graphics g){
		picture=getImage(getCodeBase(), "image//"+imagearray[n]);
		g.drawImage(picture,0,0,this);  
		 	
	}
	public void run(){
		while(true){
			n++;
		    if(n>=5)  n=n%5;   
		    repaint(); 
		    try{
				mythread.sleep(500);  
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		} 
	}
	
}
