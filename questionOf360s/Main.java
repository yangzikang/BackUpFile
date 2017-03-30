package questionOf360s;

import java.util.Scanner;

public class Main {
	public static void main(String []args){
	
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		
		String a = in.nextLine();
		String[] N = new String[n];

		for(int i = 0;i<n;i++){
			System.out.println(i);
			N[i] = in.nextLine();
			System.out.println(N[0]);
		}
		String M[] = new String[m];
		for(int i = 0;i<m;i++){
			System.out.println("a");
			M[i] = in.nextLine();
		}
		
		for(int i = 0;i<n;i++){
			String[] Ns = N[i].split(" ");
			int max = 0;
			int whereLine = 0;
			for(int j=0;j<m;j++){
				String[] Ms = M[j].split(" ");
				int temp = 0;
				for(int k=0;k<Ns.length;k++){
					for(int g=0;g<Ms.length;g++){
						
						if(Ns[k].equals(Ms[g])){
							temp++;
						}
					}
				}
				if(temp>max){
					max = temp;
					whereLine = j;
				}
				temp = 0;
			}
			System.out.println(M[whereLine]);
		}
	}
}
