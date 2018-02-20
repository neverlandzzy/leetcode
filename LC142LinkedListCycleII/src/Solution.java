
public class Solution {
	/*
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 *
	 * Note: Do not modify the linked list.
	 *
	 * Follow up:
	 * Can you solve it without using extra space?
	 */
	
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode joint = null;
        
        while (fast != null && fast.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        	
        	if (slow == fast) {
        		joint = slow;
        		break;
        	}
        }
        
        if (joint != null) {
        	while (head != joint) {
        		head = head.next;
        		joint = joint.next;
        	}
        }
        return joint;
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
		node5.next = node2; // cycle
		
		ListNode head = node1;
		
		System.out.println(detectCycle(head).val);	
	}
}
