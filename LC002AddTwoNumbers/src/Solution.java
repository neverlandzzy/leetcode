
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
        ListNode result = new ListNode(0);
        ListNode cur = result;

        int carry = 0;

        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;

            cur.next = new ListNode(sum % 10);
            carry = sum / 10;

            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }


        if (carry != 0) {
            ListNode node = new ListNode(carry);
            cur.next = node;
        }

        return result.next;
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
