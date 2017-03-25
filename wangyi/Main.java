package wangyi;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
		Scanner in = new Scanner(System.in);
		String string = in.nextLine();
		char[] chars = string.toCharArray();
		int number1 = 0;
		int number2 = 0;
		char operation = 0;
		int j = 0;
		for(int i=0;i<chars.length ;i++){
			
			if(chars[i]<='9'&&chars[i]>='0'){
				if(j==0){
					number1 = chars[i]-'0';
					System.out.println(number1);
					j++;
				}
				else{
					number2 = chars[i]-'0';
					if(operation == '+'){
						number1 += number2;
					}
					else if (operation == '-'){
						number1 -= number2;
					}
					else{
						number1 *= number2;
					}
				}
				
			}
			if(chars[i]=='+'||chars[i]=='-'||chars[i]=='*'){
				operation = chars[i];
			}
		}
		System.out.println(number1);
	}
}
