
public class Solution {
	/*
	 * Reverse a singly linked list.
	 */
	
	
	// Solution 1: Iteration
	/*
    public static ListNode reverseList(ListNode head) {
    	ListNode prev = null;
    	ListNode curr = head;
    	
    	while (curr != null) {
    		ListNode tmp = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = tmp;
    	}
    		
    	return prev;
    }
    */
    
    // Solution 2: Recursion
    
    public static ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode resHead = head.next;
    	head.next = null;
    	
    	ListNode newHead = reverseList(resHead);
    	resHead.next = head;

    	return newHead;
    	
    }
    
    public static void main(String[] args) {
		
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(4);
    	ListNode node5 = new ListNode(5);
    	ListNode node6 = new ListNode(6);
    	
    	node1.next = node2;
    	node2.next = node3;
    	node3.next = node4;
    	node4.next = node5;
    	node5.next = node6;
    	
    	ListNode head = node1;
    	
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println(" ");
    	
    	head = reverseList(node1);
    	
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
	}
}
