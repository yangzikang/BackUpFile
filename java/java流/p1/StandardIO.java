package p1;

import java.io.*;

public class StandardIO {
	public static void main(String []args){
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("enter a string");
		try {
			System.out.println(stdin.readLine());//1��System.in������뻺������ֱ�����readLine
			System.out.println("Enter a integer");
			int number =Integer.parseInt(stdin.readLine());//2��stdin.readLine���õ����ַ���ת����int������number��
			System.out.println(number);
			System.out.println("Enter a double");
			double number2 =Double.parseDouble(stdin.readLine());//3��stdin.readLine���õ����ַ���ת����double������number2��
			System.out.println(number2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
