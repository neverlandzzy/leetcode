
public class Solution {
	/**
	 * Merge two sorted linked lists and return it as a new list. The new list should be 
	 * made by splicing together the nodes of the first two lists.
	 */
	
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        if (l1 == null) {
            tail.next = l2;
        } else {
            tail.next = l1;
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(4);
    	ListNode node5 = new ListNode(5);
    	
    	node1.next = node3;
    	node3.next = node5;
    	node2.next = node4;
    	
    	
    	ListNode newList = mergeTwoLists(node1, node2);
    	
    	while (newList != null) {
    		
    		System.out.print(newList.val + "--->");
    		newList = newList.next;
    	}
    }
}
