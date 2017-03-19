／**
*棋盘输入3个点，将三个点的上下左右翻转颜色
*／

public class turn {
	
	public static void main(String []args){
		int [][]A = {{0,0,1,1},{1,0,1,0},{0,1,1,0},{0,0,1,0}};
		int [][]F = {{2,2},{3,3},{4,4}};
		A = flipChess(A,F);
		System.out.println();
		
	}
	
	public static int[][] flipChess(int[][] A, int[][] f) {
        // write code here
		for(int i = 0;i<3;i++){
				int hang = f[i][0];
				int lie = f[i][1];
				if(hang-2>=0){
					A[hang-1-1][lie-1]=fanzhuan(A[hang-1-1][lie-1]);
				}
				if(hang<4){
					A[hang-1+1][lie-1]=fanzhuan(A[hang-1+1][lie-1]);
				}
				if(lie-2>=0)
				A[hang-1][lie-1-1]=fanzhuan(A[hang-1][lie-1-1]);
				if(lie<4)
				A[hang-1][lie]=fanzhuan(A[hang-1][lie]);
		}
		
		return A;
		
    }
	private static int fanzhuan(int number){
		if(number == 1){
			return 0;
		}
		else{
			return 1;
		}
	}
}
