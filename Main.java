package hhh;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
		int w;
		double t;
		double x;
		int n;
		Scanner in = new Scanner(System.in);
		w = in.nextInt();
		t = in.nextDouble();
		x = in.nextDouble();
		n = in.nextInt();
		
		
		double T[] = new double[w];
		for(int i=0; i< w;i++){
			T[i] = t;
		}
		for(int i=0 ;i< n; i++){
			for(int j =0 ;j<w;j++){
				if(j<(int)(x*w)){
					T[j] = 21;
				}
				else{
					T[j]++;
				}
			}
			t=0;
			for(int k = 0;k<w;k++){
				t+=T[k];
			} 
			t = t/w;
			for(int l=0; l< w;l++){
				T[l] = t;
			}
			
			
		}
		if(t>(int)t){
			t=t+1;
		}
		
		t = Integer.valueOf(String.valueOf(t));
		System.out.println(t);
		
		
	}
	
	
	
}
