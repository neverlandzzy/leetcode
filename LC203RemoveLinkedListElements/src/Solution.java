
public class Solution {
	/*
	 * Remove all elements from a linked list of integers that have value val.
	 * 
	 * Example
	 * 	Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
	 * 	Return: 1 --> 2 --> 3 --> 4 --> 5
	 */
	
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            
            cur = cur.next;
            
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
    	ListNode node7 = new ListNode(6);
    	ListNode node8 = null;
    	
    	node1.next = node2;
    	node2.next = node7;
    	node7.next = node3;
    	node3.next = node4;
    	node4.next = node5;
    	node5.next = node6;
    	
    	ListNode head = node1;
    	
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println(" ");
    	
    	head = removeElements(node8, 6);
    	
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
	}
}
