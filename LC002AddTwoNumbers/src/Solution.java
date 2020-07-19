
public class Solution {
	
	/**
	 * You are given two linked lists representing two non-negative numbers. 
	 * The digits are stored in reverse order and each of their nodes contain 
	 * a single digit. Add the two numbers and return it as a linked list.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 */
	
	
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int sum = 0;
        int carry = 0;
        
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            sum = l1.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1.next;
        }
        
        while (l2 != null) {
            sum = l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l2 = l2.next;
        }
        
        if (carry != 0) {
            cur.next = new ListNode(1);
        }
        
        return head.next;
    }
    
	public static void main(String[] args) {
		ListNode a1 = new ListNode(5);
		ListNode a2 = new ListNode(4);
		ListNode a3 = new ListNode(3);
		ListNode b1 = new ListNode(5);
		ListNode b2 = new ListNode(6);
		ListNode b3 = new ListNode(4);
		
		a1.next = a2;
		a2.next = a3;
		
		b1.next = b2;
		b2.next = b3;
		
		ListNode result = addTwoNumbers(a1, b1);
		
		while (result != null) {
			System.out.print(result.val + " -> ");
			result = result.next;
		} 
	}
}
