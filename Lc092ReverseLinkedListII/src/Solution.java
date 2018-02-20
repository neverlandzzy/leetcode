
public class Solution {
	/*
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 * 
	 * For example:
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * 
	 * return 1->4->3->2->5->NULL.
	 * 
	 * Note:
	 * 
	 * Given m, n satisfy the following condition:
	 * 1 ≤ m ≤ n ≤ length of list.
	 * 
	 */
	
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        for (int i = 1; i < m; i++) {
            // 题目已经假定满足 1 ≤ m ≤ n ≤ length of list.所以不用这步check
            /*
            if (head == null) {
                return null;
            }
            */
            head = head.next;
        }
        
        // dummy -> 1   ->   2   ->   3   ->   4   ->   5
        //          |        |        |
        //         preM   mNode/pre  cur
        ListNode preM = head;
        ListNode mNode = preM.next;
        ListNode pre = mNode;
        ListNode cur = mNode.next;
        
        for (int i = m; i < n; i++) {
            // 题目已经假定满足 1 ≤ m ≤ n ≤ length of list.所以不用这步check
            /*
            if (postnNode == null) {
                return null;
            }
            */
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        
        mNode.next = cur;
        preM.next = pre;
        
        return dummy.next;
    }
    /*
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        
        
        for (int i = 1; i < m; i++) {
        	pre = pre.next;
        	cur = cur.next;
        }
        
        for (int i = 0; i < n - m; i++) {
        	ListNode tmp = cur.next.next;
        	cur.next.next = pre.next;
        	pre.next = cur.next;
        	cur.next = tmp;
        }

        return dummy.next;
    }
    */
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
		
    	while(head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	System.out.println("\n-----------");
    	ListNode newhead = node1;
    	newhead = reverseBetween(newhead, 1, 6);
		
		while (newhead != null) {
			System.out.print(newhead.val + "->");
			newhead = newhead.next;
		}
		
		
	}
}
