
public class Solution {
	/**
	 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
	 * 
	 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
	 * 
	 * You must do this in-place without altering the nodes' values.
	 * 
	 * For example,
	 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 */
	
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        
        while (fast != null && fast.next != null) {
        	pre = slow;
        	slow = slow.next;
        	fast = fast.next.next;
        }
        
        slow = reverseList(slow);
        pre.next = null;
        
        ListNode cur = head;
        
        while (cur != null && slow != null) {
        	ListNode tmp1 = cur.next;
        	ListNode tmp2 = slow.next;
        	
        	cur.next = slow;
        	cur = tmp1;
        	
        	if (cur!= null) {
        		slow.next = tmp1;
        		slow = tmp2;  
        		
        	}
        }

    }
    
    public static ListNode reverseList(ListNode head) {
		ListNode cur = head;
		ListNode pre = null;

		while (cur != null) {
			ListNode tmp = cur.next;
			cur.next = pre;
			pre = cur;

			cur = tmp;
		}

		return pre;
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
		
		reorderList(head);
		
		while (head != null) {
			System.out.print(head.val + "->");
			head = head.next;
		}
		
		
	}
}
