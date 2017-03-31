package p1;

import java.io.*;

public class StandardIO {
	public static void main(String []args){
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter a string");
		try {
			System.out.println(stdin.readLine());//1从System.in输入存入缓冲流的直接输出readLine
			System.out.println("Enter a integer");
			int number =Integer.parseInt(stdin.readLine());//2将stdin.readLine所得到的字符串转换成int保存在number里
			System.out.println(number);
			System.out.println("Enter a double");
			double number2 =Double.parseDouble(stdin.readLine());//3将stdin.readLine所得到的字符串转换成double保存在number2里
			System.out.println(number2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
