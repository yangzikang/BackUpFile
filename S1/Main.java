package S1;

import java.util.*;

public class Main {
	public static void main(String []args){
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		List l = new ArrayList();
		
		for(int i =0;i< number;i++){
			int addNumber = in.nextInt();
			l.add(addNumber);
		}
		
		int jieguo = 0;
		
		while(true){
			if(isHuiWen(l)){
				break;
			}
			jieguo++;
			
			int tempMax = 3000;
			int tempI = 0;
			for(int i =0;i<l.size()-1;i++){
				if(tempMax>(int)l.get(i)+(int)l.get(i+1)){
					tempMax = (int)l.get(i)+(int)l.get(i+1);
					tempI = i;
				}
			};
			System.out.println(tempMax);
			l.remove(tempI);
			for(int i =0 ;i< l.size();i++){
				System.out.print("yin"+l.get(i));
				
			}
			System.out.println("");
			l.remove(tempI);
			for(int i =0 ;i< l.size();i++){
				System.out.print("yin"+l.get(i));
				
			}
			System.out.println("");
			l.add(tempI,tempMax);
			for(int i =0 ;i< l.size();i++){
				System.out.print("yin"+l.get(i));
				
			}
			System.out.println("");
			
		}
		
		System.out.println("jieguo :"+jieguo);
		
	}
	
	public static boolean isHuiWen(List l){
		if(l.size()==1){
			return true;
		}
		int number = 0;
		for(int i =0 ,j=l.size()-1;i<l.size();i++,j--){
			if(l.get(i).equals(l.get(j))){
				number++;
			}
		}
		if(number == l.size()){
			return true;
		}
		else{
			return false;
		}
	}
}
