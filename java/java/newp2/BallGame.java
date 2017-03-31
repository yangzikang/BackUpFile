package newp2;

import java.awt.*;
import java.lang.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class BallGame extends JFrame implements ActionListener
{
	JButton jbtStart=new JButton("Start");//������������ťjbtStart��������ʾStart
	JButton jbtStop =new JButton("Stop");//������������ťjbtStop��������ʾStop
	MainPanel mp = new MainPanel();
	public BallGame()
	{
		add(mp,"Center");                              //�����mp��ӵ�������м�
		mp.setBackground(Color.white);
		JPanel p=new JPanel();
		p.add(jbtStart);       //��jbtStart��ӵ����p��
		p.add(jbtStop);     //��jbtStop��ӵ����p��
		add(p,"South");       //�����p��ӵ�������ϲ�
		jbtStart.addActionListener(this);
		jbtStop.addActionListener(this);///ΪjbtStart��jbtStop	ע�������
		
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
             
            BallThread b=new BallThread(mp);  //��1������С����̶߳���������ʼ���ö���
            mp.balls.addElement(b);
            b.start();	//��2������С���̶߳����run���������߳�ִ�С�
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
   			BallThread ball = (BallThread)balls.elementAt(i);  //��3����ȡ��ǰС��Ķ��󣬸�ֵ���´�����ball����
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
       g.fillOval(x,y,XSIZE,YSIZE);  //��4��x - Ҫ�����Բ�����Ͻǵ� x ���ꡣy - Ҫ�����Բ�����Ͻǵ� y ���ꡣwidth - Ҫ�����Բ�Ŀ�ȡ�eight - Ҫ�����Բ�ĸ߶ȡ�

       g.dispose();
  
   	}
   	public void move()
   	{
       x+=dx;  //��5��ʹ����Ӵ�dx
       y+=dy;
       Dimension d=mainPanel.getSize();
       if(x<0){x=0;dx=-dx;}  //��6�����������߿򷴵�
       if(y<0){y=0;dy=-dy;}
       if(x+XSIZE>=d.width ) {x=d.width -XSIZE;dx=-dx;}  //��7�������ұ߿򷴵�
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
              move();  //��8������С����ƶ�����
 			  sleep(30); //��9��ÿ30����ִ��һ��
          }
       }catch(Exception e){   }
    }
}
