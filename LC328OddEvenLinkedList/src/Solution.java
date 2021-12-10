
public class Solution {
	/**
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking 
	 * about the node number and not the value in the nodes.
	 * 
	 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
	 * 
	 * Example:
	 * Given 1->2->3->4->5->NULL,
	 * return 1->3->5->2->4->NULL.
	 * 
	 * Note:
	 * The relative order inside both the even and odd groups should remain as it was in the input. 
	 * The first node is considered odd, the second node even and so on ...
	 */
	
    public static ListNode oddEvenList(ListNode head) {
    	
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        
        while (even != null && even.next != null) {
        	odd.next = even.next;
        	odd = odd.next;
        	even.next = odd.next;
        	even = even.next;
        }
        
        odd.next = evenHead;
        
        return head;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		ListNode head = node1;
		
    	while(head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println("\n-----------");
    	ListNode newhead = node1;
    	newhead = oddEvenList(newhead);
		
		while (newhead != null) {
			System.out.print(newhead.val + "->");
			newhead = newhead.next;
		}
		
		
	}
}
