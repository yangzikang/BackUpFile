package tree;



public class Main {
	static char[] datas = new char[5];
	
	public static void main(String args[]){
		
		
		BinaryTree bt = new BinaryTree();
		BinaryTree bt2  = new BinaryTree();
		BinaryTree bt3  = new BinaryTree();
		BinaryTree bt4  = new BinaryTree();
		BinaryTree bt5  = new BinaryTree();
		
		bt.data = 'A';
		bt2.data = 'B';
		bt3.data = 'C';
		bt4.data = 'D';
		bt5.data = 'E';
		
		bt.leftChild = bt2;
		bt.rightChild = bt3;
		bt2.leftChild = bt4;
		bt2.rightChild = null;
		bt3.leftChild = null;
		bt3.rightChild = null;
		bt4.leftChild = null;
		bt4.rightChild = bt5;
		bt5.leftChild = null;
		bt5.rightChild = null;
		
		
		
		TreeOperation t = new TreeOperation();
		t.preOrderRecursion(bt);
		System.out.println("------------------------");
		t.endOrderRecursion(bt);
		System.out.println("------------------------");
		t.zhongOrderRecursion(bt);
	}
}
