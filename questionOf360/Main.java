package questionOf360;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
		int n,q;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		q= in.nextInt();
		
		int A[] = new int[n];
		int B[] = new int[n];
		
		for(int i = 0;i<n;i++){
			A[i] = in.nextInt();
		}
		
		for(int i = 0;i<n;i++){
			B[i] = in.nextInt();
		}
		
		int Q[][] = new int[q][2];
		for(int i=0;i<q;i++){
			for(int j=0;j<2;j++){
				Q[i][j] = in.nextInt();
			}
		}
		
		
		for(int i =0 ;i<q ;i++){
			int m =0;
			for(int j=0;j<n;j++){
				
				if(A[j]>=Q[i][0]&&B[j]>=Q[i][1]){
					m++;
				}
			}
			System.out.println(m);
			
		}
	}
}
