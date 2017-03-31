package newp;

import java.applet.Applet;
import java.awt.Graphics;

public class MyFirstApplet extends Applet{
	public String s;
	public void init(){
		s="java world!";
	}
	public void paint(Graphics g){
		g.drawString(s, 5, 25);
	}
}