
public class Solution {
	/**
	 * Given a list, rotate the list to the right by k places, where k is non-negative.
	 * 
	 * For example:
	 * Given 1->2->3->4->5->NULL and k = 2,
	 * return 4->5->1->2->3->NULL.
	 */
	
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        
        int size = 0;
        ListNode cur = head;
        
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        
        k %= size;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode tail = head;
        
        for (int i = 0; i < k; i++) {
            head = head.next;
        }
        
        while (head.next != null) {
            head = head.next;
            tail = tail.next;
        }
        
        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;
        
        return dummy.next;
    }
    
	/*
    public static ListNode rotateRight(ListNode head, int k) {
    	ListNode fast = head;
    	ListNode slow = head;
    	int size = 1;
    	
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	while(fast.next != null) {
    		fast = fast.next;
    		size++;
    	}
    	
    	if (k % size == 0) {
    		return head;
    	}
    	
    	int n = size - k % size - 1;
    	
    	while(n > 0) {
    		slow = slow.next;
    		n--;
    	}
    	
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        
        return newHead;
    }
    */
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
		
		head = rotateRight(head, 7);
		
		while (head != null) {
			System.out.print(head.val + "->");
			head = head.next;
		}

	}
}
