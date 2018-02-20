
public class Solution {
	/*
	 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
	 * 
	 * You may assume the integer do not contain any leading zero, except the number 0 itself.
	 * 
	 * The digits are stored such that the most significant digit is at the head of the list.
	 * 
	 * Example:
	 * Input:
	 * 1->2->3
	 * 
	 * Output:
	 * 1->2->4
	 */
	
	// Solution 1: reverse - add - reverse
	/*
    public static ListNode plusOne(ListNode head) {
        ListNode reversedHead = reverse(head);
        ListNode cur = reversedHead;
        
        int sum = 1;
        int carry = 0;
        
        while (cur != null) {
        	sum = sum + cur.val + carry;
        	carry = sum / 10;
        	sum = sum % 10;
        	
        	cur.val = sum;
        	
        	if (cur.next == null) {
        		break;
        	}
        	
        	cur = cur.next;
        	sum = 0;
        }
        
        if (carry != 0) {
        	cur.next = new ListNode(1);
        }
        
        return reverse(reversedHead);
    }
    
    private static ListNode reverse(ListNode head) {
    	ListNode pre = null;
    	ListNode cur = head;
    	
    	while (cur != null) {
    		ListNode tmp = cur.next;
    		cur.next = pre;
    		pre = cur;
    		cur = tmp;
    	}
    	
    	return pre;
    }
    */
	
	// Solution 2: no -reverse
    public static ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode lastNotNine = dummy;
        ListNode cur = head;
        
        while (cur != null) {
            if (cur.val != 9) {
                lastNotNine = cur;
            }
            cur = cur.next;
        }
        
        lastNotNine.val++;
        cur = lastNotNine.next;
        
        while (cur != null) {
            cur.val = 0;
            cur = cur.next;
        }
    	
    	// 对于dummy(0)->9->9->9，加1后会成为dummy(1)->0->0->0
    	return dummy.val == 1 ? dummy : dummy.next;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(9);
		ListNode node2 = new ListNode(9);
		ListNode node3 = new ListNode(9);
		
		node1.next = node2;
		node2.next = node3;

		
		ListNode head = node1;
		
		head = plusOne(head);
		
		while (head != null) {
			System.out.print(head.val + "->");
			head = head.next;
		}

	}
}
