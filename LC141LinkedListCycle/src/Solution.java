
public class Solution {
	/*
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * Follow up:
	 * Can you solve it without using extra space?
	 */
	
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
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
		node5.next = node3; // cycle
		
		ListNode head = node1;
		
		System.out.println(hasCycle(head));

	}
}
