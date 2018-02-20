
public class Solution {
	/*
	 * Given a singly linked list, determine if it is a palindrome.
	 * 
	 * Follow up:
	 * Could you do it in O(n) time and O(1) space?
	 */
	
	
	
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        if (head.next == null) {
            return true;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // 找中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // start of LC92
        ListNode preMnode = slow;
        ListNode mNode = slow.next;
        ListNode nNode = mNode;
        ListNode postnNode = mNode.next;
        
        while (postnNode != null){
            ListNode tmp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = tmp;
        }
        
        preMnode.next = nNode;
        mNode.next = null;
        // end of LC92
        
    	while (slow.next != null) {
    		slow = slow.next;
    		if (slow.val != head.val) {
    			return false;
    		}
    		head = head.next;
    	}
        
        return true;
    }
	/*
    public static boolean isPalindrome(ListNode head) {
    	
        if (head == null) {
            return true;
        }
        
    	ListNode fast = head;
    	ListNode slow = head;
    	
    	while(fast.next != null && fast.next.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	
    	fast = slow.next;
    	
    	while (fast != null && fast.next != null) {
    		ListNode node = fast.next.next;
    		fast.next.next = slow.next;
    		slow.next = fast.next;
    		fast.next = node;
    	}
    	while (slow.next != null) {
    		slow = slow.next;
    		if (slow.val != head.val) {
    			return false;
    		}
    		head = head.next;
    	}
        return true;
    }
    */
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(3);
		ListNode node6 = new ListNode(2);
		ListNode node7 = new ListNode(1);
		

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		
		
		System.out.println(isPalindrome(node1));
	}
}
