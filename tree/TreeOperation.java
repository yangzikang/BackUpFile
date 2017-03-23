package tree;

public class TreeOperation {
	//先序遍历递归算法  
    public void preOrderRecursion(BinaryTree root) {  
        if(root!=null) {  
            root.visit();
            
            preOrderRecursion(root.getLeftChild());  
            preOrderRecursion(root.getRightChild());  
        }  
    }  
	public void endOrderRecursion(BinaryTree root){
		if(root!=null){
			endOrderRecursion(root.getLeftChild());  
			endOrderRecursion(root.getRightChild());
            root.visit();
            
		}
	}
	
	public void zhongOrderRecursion(BinaryTree root){
		if(root!=null){
			zhongOrderRecursion(root.getLeftChild());  
            root.visit();
            zhongOrderRecursion(root.getRightChild());
		}
	}
	public char getCommenParent(char data1,char data2,BinaryTree root){
		return data2;
		
	}
}
