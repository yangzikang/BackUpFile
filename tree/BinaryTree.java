package tree;
public class BinaryTree {

	char data;					//根节点
	BinaryTree leftChild;		//左孩子
	BinaryTree rightChild;		//右孩子
	
	public BinaryTree() {
		
	}
	
	public void visit() {
		System.out.println(this.data);
	}
	
	public BinaryTree(char data) {
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}

	public BinaryTree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTree leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTree getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTree rightChild) {
		this.rightChild = rightChild;
	}

	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

}
