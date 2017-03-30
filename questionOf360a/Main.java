package questionOf360a;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
		Scanner in = new Scanner(System.in);
		int length = in.nextInt();
		int A[] = new int[length];
		for(int i =0 ;i<length;i++){
			A[i] = in.nextInt();
		}
		int length1 =0;
		int left1 = -1;
		int right1 = -1;
		
		int length2 =0;
		int left = -1;
		int right = -1;
		for(int i =0 ;i<length;i++){
			for(int j =i-1;j>0;j--){
				if(A[j]<A[i]){
					length2++;
					System.out.println("ssss" + j);
					left = j;
				}
				else{
					break;
				}
			}
			for(int j=i+1;j<length;j++){
				if(A[j]<A[i]){
					length2++;
					System.out.println("hhh" + j);
					right = j;
				}
				else{
					break;
				}
			}
			if(length2>length1){
				length1 =length2;
				left1 = left;
				right1 = right;
				left = -1;
				right = -1;
		
			}
		}
		if(left1==-1||right1 ==-1){
			left1 = -1;
			right1 =-1;
		}
		System.out.println(left1+" "+right1);
	}
	
	
}
