
public class Solution {
	/**
	 * Given a linked list, remove the nth node from the end of list and return its head.
	 * 
	 * For example,
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * After removing the second node from the end, the linked list becomes 1->2->3->5.
	 * 
	 * Note:
	 * Given n will always be valid.
	 * Try to do this in one pass.
	 */
	
    public static ListNode removeNthFromEnd(ListNode head, int n) {
    	
        ListNode fast = head;
        ListNode cur = head;
        ListNode pre = null;
        
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            pre = cur;
            cur = cur.next;
        }
        
        if (pre == null) {
            head = head.next;
        } else {
            pre.next = cur.next;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(4);
    	ListNode node5 = new ListNode(5);
    	
    	ListNode head = node1;
    	
    	node1.next = node2;
    	node2.next = node3;
    	node3.next = node4;
    	node4.next = node5;
    	
    	while (head != null) {
    		
    		System.out.print(head.val + "--->");
    		head = head.next;
    	}
    	System.out.println();
    	System.out.println("=========");
    	head = node1;
    	head = removeNthFromEnd(head, 2);
    	
    	while (head != null) {
    		
    		System.out.print(head.val + "--->");
    		head = head.next;
    	}
    	
    }
}
