package newp;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class ApplicationApplet extends JApplet{
	public void init(){
		Container contentPane = getContentPane();
		JLabel label=new JLabel("我是Application也是APple",SwingConstants.CENTER);
		contentPane.add(label);
	}
	public static void main(String args[]){
		final JFrame f=new JFrame();
		JApplet applet =new ApplicationApplet();
		applet.init();
		f.setContentPane(applet.getContentPane());
		f.setBounds(50,50,300,200);
		f.setTitle("wodoushi");
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
//D