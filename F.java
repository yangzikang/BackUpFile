package test;
import java.util.*;

public class F {
	public static void main(String args[]){
		int total = new Balls().calcDistance(100, 90,80, 70);
		
		System.out.println(total);
	}
}

class Balls {
    public int calcDistance(int A, int B, int C, int D) {
        // write code here
        double sumA = getSum(A);
        double sumB = getSum(B);
        double sumC = getSum(C);
        double sumD = getSum(D);
        int total = (int)(sumA+sumB+sumC+sumD);
        return total;
        
        
    }
    public double getSum(int A2){
    	double A = A2;
        double sumA = A2;
        while(A/2>=1){
        	sumA += A;
        	A = A/2.0;
            
            System.out.println(A);
        }
        return sumA;
    }
}
