
public class Solution {
	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, 
	 * leaving only distinct numbers from the original list.	
	 * 
	 * For example,
	 * Given 1->2->3->3->4->4->5, return 1->2->5.
	 * Given 1->1->1->2->3, return 2->3.
	 */
	
    public static ListNode deleteDuplicates(ListNode head) {
    	
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        if (head == null) {
            return head;
        }
        
        ListNode pre = dummy;
        ListNode cur = dummy.next;

		while (cur != null) {
			if (cur.next != null && cur.val == cur.next.val) {
				while (cur.next != null && cur.val == cur.next.val) {
					cur = cur.next;
				}

				pre.next = cur.next;

			} else {
				pre = cur;
			}

			cur = cur.next;

		}
        
        return dummy.next;
    }
    
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(1);
    	ListNode node3 = new ListNode(1);
    	ListNode node4 = new ListNode(3);
    	ListNode node5 = new ListNode(3);
    	ListNode node6 = new ListNode(4);
    	ListNode node7 = new ListNode(5);
    	ListNode node8 = new ListNode(5);
    	
    	ListNode head = node1;
    	
    	node1.next = node2;
    	//node2.next = node3;
    	//node3.next = node4;
    	//node4.next = node5;
    	//node5.next = node6;
    	//node6.next = node7;
    	//node7.next = node8;
    	
    	while(head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println();
    	
    	System.out.println("-------------");
    	ListNode newhead = node1;
    	
    	newhead = deleteDuplicates(newhead);
    	
    	while(newhead != null) {
    		System.out.print(newhead.val + "->");
    		newhead = newhead.next;
    	}
	}
}
