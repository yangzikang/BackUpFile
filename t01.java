package Main;
import java.util.ArrayList;
import java.util.List;
public class t01 {
	public static void main (String []args){
		ListNode l = new ListNode(67);
		ListNode l2 = new ListNode(0);
		ListNode l3 = new ListNode(24);
		ListNode l4 = new ListNode(58);
		
		l.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = null;
		
		
		Solution s = new Solution();
		
		
		ArrayList a = s.printListFromTailToHead(l);
	
		
		for(int i =0;i<a.size();i++){
			System.out.println(a.get(i));
		}
	}
	
}

class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    
    	if(listNode == null){
    		return new ArrayList();
    	}
       List l = new ArrayList();
       List l2 = new ArrayList();
       while(listNode!= null){
    	   l.add(listNode.val);
    	   listNode = listNode.next;
       }
       for(int i =0;i<l.size();i++){
    	   System.out.println(l.get(i));
       }
       
      
       int j = l.size()-1;
       System.out.println(j);
       for(int i = j;i>=0;i--){
    	   
    	   l2.add(l.get(i));
       }
       return (ArrayList<Integer>) l2;
    }
}