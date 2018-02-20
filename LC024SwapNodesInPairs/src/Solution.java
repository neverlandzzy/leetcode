
public class Solution {
	/*
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example,
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the values in the list, 
	 * only nodes itself can be changed.
	 * 
	 */
	
    public static ListNode swapPairs(ListNode head) {
    	
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	head = dummy;
    	
    	while (head.next != null && head.next.next != null) {
    		ListNode n1 = head.next;
    		ListNode n2 = head.next.next;
    		
    		head.next = n2;
    		n1.next = n2.next;
    		n2.next = n1;
    		head = n1;
    	}
        
    	return dummy.next;
    }
    
    public static void main(String[] args) {
    	
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(4);
    	ListNode node5 = new ListNode(5);
    	ListNode node6 = new ListNode(6);
    	ListNode node7 = new ListNode(7);
    	ListNode node8 = new ListNode(8);
    	ListNode node9 = new ListNode(9);
    	
    	node1.next = node2;
    	node2.next = node3;
    	node3.next = node4;
    	node4.next = node5;
    	node5.next = node6;
    	node6.next = node7;
    	node7.next = node8;
    	node8.next = node9;
    	
    	ListNode head = node1;
    	
    	while (head != null) {
    		
    		System.out.print(head.val + "--->");
    		head = head.next;
    	}
    	
    	ListNode newHead = swapPairs(node1);
    	System.out.println();
    	while (newHead != null) {
    		
    		System.out.print(newHead.val + "--->");
    		newHead = newHead.next;
    	}
    }
}
