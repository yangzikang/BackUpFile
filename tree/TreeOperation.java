package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeOperation {
	int i = 0;
	//先序遍历递归算法  
    public void preOrderRecursion(BinaryTree root) {  
        if(root!=null) {  
            root.visit();
            Main.datas[i] = root.data;
            i++;
            preOrderRecursion(root.getLeftChild());  
            preOrderRecursion(root.getRightChild());  
        }  
        i=0;
    }  
	public void endOrderRecursion(BinaryTree root){
		if(root!=null){
			endOrderRecursion(root.getLeftChild());  
			endOrderRecursion(root.getRightChild());
            root.visit();
            Main.datas[i] = root.data;
            i++;
            
		}
		i=0;
	}
	
	public void zhongOrderRecursion(BinaryTree root){
		if(root!=null){
			zhongOrderRecursion(root.getLeftChild());  
            root.visit();
            Main.datas[i] = root.data;
            i++;
            zhongOrderRecursion(root.getRightChild());
		}
		i=0;
	}
	public char getCommenParent(char data1,char data2,BinaryTree root){
		
		List l = new ArrayList();
		
		preOrderRecursion(root);
		char[] dlr = Main.datas;
		endOrderRecursion(root);
		char[] lrd = Main.datas;
		zhongOrderRecursion(root);
		char[] ldr = Main.datas;
		for(int i=0;i<dlr.length;i++){
			if(dlr[i]==data1||dlr[i]==data2){
				break;
			}
			l.add(dlr[i]);
		}
		for(int i=0;i<lrd.length;i++){
			boolean j = true;
			if(lrd[i]==data1||lrd[i]==data2){
				
			}
			
		}
		for(int i=0;i<dlr.length;i++){
			if(dlr[i]==data1||dlr[i]==data2){
				break;
			}
			l.add(dlr[i]);
		}
		return data2;
		
	}
}
