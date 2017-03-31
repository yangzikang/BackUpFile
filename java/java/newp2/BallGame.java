package newp2;

import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class BallGame extends JFrame implements ActionListener
{
	JButton jbtStart=new JButton("Start");//声明并创建按钮jbtStart，上面显示Start
	JButton jbtStop =new JButton("Stop");//声明并创建按钮jbtStop，上面显示Stop
	MainPanel mp = new MainPanel();
	public BallGame()
	{
		add(mp,"Center");                              //将面板mp添加到窗体的中间
		mp.setBackground(Color.white);
		JPanel p=new JPanel();
		p.add(jbtStart);       //将jbtStart添加到面板p上
		p.add(jbtStop);     //将jbtStop添加到面板p上
		add(p,"South");       //将面板p添加到窗体的南部
		jbtStart.addActionListener(this);
		jbtStop.addActionListener(this);///为jbtStart和jbtStop	注册监听器
		
	}
	public static void main(String[] args)
	{
		BallGame bt = new BallGame();
		bt.setSize(300,300);
		bt.setVisible(true);
		bt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbtStart)
		{
             
            BallThread b=new BallThread(mp);  //（1）创建小球的线程对象，用面板初始化该对象
            mp.balls.addElement(b);
            b.start();	//（2）启动小球线程对象的run方法，多线程执行。
		}
		else
		{
			System.exit(0);
		}
	}
}
class MainPanel extends Panel
{
   public Vector balls=new Vector();
    public void paint(Graphics g)
    {
   		for(int i=0;i<balls.size();i++)
   		{
   			BallThread ball = (BallThread)balls.elementAt(i);  //（3）获取当前小球的对象，赋值给新创建的ball对象
   			ball.draw(); 
   		}     
   
    }
}
class BallThread extends Thread
{
 
    MainPanel mainPanel;
   	private static final int XSIZE=20;
   	private static final int YSIZE=20;
   	private int x=0;
   	private int y=0;
   	private int dx=2;
   	private int dy=2;
   	public BallThread(MainPanel b)
   	{ 
   		mainPanel=b;
   	}
   	public void draw()
   	{
       Graphics g=mainPanel.getGraphics();
       g.fillOval(x,y,XSIZE,YSIZE);  //（4）x - 要填充椭圆的左上角的 x 坐标。y - 要填充椭圆的左上角的 y 坐标。width - 要填充椭圆的宽度。eight - 要填充椭圆的高度。

       g.dispose();
  
   	}
   	public void move()
   	{
       x+=dx;  //（5）使坐标加存dx
       y+=dy;
       Dimension d=mainPanel.getSize();
       if(x<0){x=0;dx=-dx;}  //（6）如果碰到左边框反弹
       if(y<0){y=0;dy=-dy;}
       if(x+XSIZE>=d.width ) {x=d.width -XSIZE;dx=-dx;}  //（7）遇到右边框反弹
       if(y+YSIZE>=d.height ) {y=d.height -YSIZE;dy=-dy;}
       mainPanel.repaint();
  
   	}
    public void run() 
    {
       try
       {
          draw();
          while(true)
          {
              move();  //（8）调用小球的移动函数
 			  sleep(30); //（9）每30毫秒执行一次
          }
       }catch(Exception e){   }
    }
}
