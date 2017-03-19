／**
 *命名不对，实现后减前最大（美团面试题）
 *／
public class cha {
	
		public static void main(String []args){
			int A[]={10,5,10,6,3};
			System.out.println(getDis(A,5));
			
		}
	    public static  int getDis(int[] A, int n) {
	        // write code here
	        int maxValue = 0;
	    	for(int i = n-1;i>=0;i--){
	    		for(int j = 0;j<=i;j++){
	    			int maxValueTemp = A[i]-A[j];
	    			if(maxValue<maxValueTemp){
	    				maxValue = maxValueTemp;
	    			}
	    			
	    		}
	        	
	        }
	    	return maxValue;
	    }
	
}
