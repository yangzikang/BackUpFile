package p1;

import java.io.*;

public class CharIOExample {
	public static void main(String []args){
		String s="";
		System.out.print("请输入一个字符串");
		BufferedReader mystring = new BufferedReader(new InputStreamReader(System.in));
		try {
			s=mystring.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("您输入的是"+s);
	}
}
