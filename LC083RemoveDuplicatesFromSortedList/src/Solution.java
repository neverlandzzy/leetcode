
public class Solution {
	/*
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * 
	 * For example,
	 * 
	 * Given 1->1->2, return 1->2.
	 * Given 1->1->2->3->3, return 1->2->3.
	 */
	
    public static ListNode deleteDuplicates(ListNode head) {
    	// Solution 1: 类似LC82解法
    	
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        if (head == null) {
            return head;
        }
        
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        
        return dummy.next;
    	
    	/*
    	// Solution 2
        
        if (head == null) {
        	return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
            
        while (cur != null) {
            if (pre.val == cur.val) {
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        
        return head;
        */
    }
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(1);
    	ListNode node3 = new ListNode(1);
    	ListNode node4 = new ListNode(3);
    	ListNode node5 = new ListNode(3);
    	
    	ListNode head = node1;
    	
    	node1.next = node2;
    	node2.next = node3;
    	//node3.next = node4;
    	//node4.next = node5;
    	
    	while(head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println();
    	
    	System.out.println("-------------");
    	ListNode newhead = node1;
    	
    	ListNode sss = deleteDuplicates(newhead);
    	
    	while(sss != null) {
    		System.out.print(sss.val + "->");
    		sss = sss.next;
    	}
	}
	
}
