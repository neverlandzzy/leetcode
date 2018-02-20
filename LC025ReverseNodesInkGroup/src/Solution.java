

public class Solution {
	static int counter = 0;
	/*
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
	 * You may not alter the values in the nodes, only nodes itself may be changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example,
	 * Given this linked list: 1->2->3->4->5
	 * For k = 2, you should return: 2->1->4->3->5
	 * For k = 3, you should return: 3->2->1->4->5
	 */
	
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int length = 0;
        int i = 1;
        
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        
        if (k > length) {
            return head;
        }
        
        cur = head;
        while ((i + k - 1) <= length) {
            cur = reverseBetween(cur, i, i + k - 1);
            i += k;
        }
        
        return cur;
    }
    
    
    private static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        
        ListNode preM = head;
        ListNode mNode = preM.next;
        ListNode pre = mNode;
        ListNode cur = mNode.next;
        
        for (int i = m; i < n; i++) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        
        preM.next = pre;
        mNode.next = cur;
        
        return dummy.next;
    }
    
	public static void main(String[] args) {
	    	
	    	ListNode node1 = new ListNode(1);
	    	ListNode node2 = new ListNode(2);
	    	ListNode node3 = new ListNode(3);
	    	ListNode node4 = new ListNode(4);
	    	ListNode node5 = new ListNode(5);
	    	ListNode node6 = new ListNode(6);
	    	ListNode node7 = new ListNode(7);
	    	ListNode node8 = new ListNode(8);
	    	ListNode node9 = new ListNode(9);
	    	
	    	node1.next = node2;
	    	//node2.next = node3;
	    	//node3.next = node4;
	    	//node4.next = node5;
	    	//node5.next = node6;
	    	//node6.next = node7;
	    	//node7.next = node8;
	    	//node8.next = node9;
	    	
	    	ListNode head = node1;
	    	
	    	while (head != null) {
	    		
	    		System.out.print(head.val + "--->");
	    		head = head.next;
	    	}
	    	System.out.println();
	    	ListNode newHead = reverseKGroup(node1, 2);
	    	System.out.println();
	    	while (newHead != null) {
	    		
	    		System.out.print(newHead.val + "--->");
	    		newHead = newHead.next;
	    	}
	    }
}
