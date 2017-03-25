package wangyi2;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
		Scanner in = new Scanner(System.in);
		int length = in.nextInt();
		int time = in.nextInt();
		int[] array = new int[length];
		for(int i =0;i<length ;i++){
			array[i] = in.nextInt();
		}
		
		for(int i=0;i<time ;i++){
			int []array2 =  array;
			int a = array[0];
			
			for(int j=0;j<length-1;j++){
				array[j] = (array2[j]+array2[j+1])%100;
			}
			array[length-1] = (array2[length-1]+a)%100;
		}
		
		
		for(int i =0 ;i<length-1;i++){
			System.out.print(array[i]+" ");
		}
		System.out.print(array[length-1]);
	}
}
