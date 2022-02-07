import java.util.Stack;


public class Solution {
	/**
	 * You are given two non-empty linked lists representing two non-negative integers. 
	 * The most significant digit comes first and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.
	 * 
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * 
	 * Follow up:
	 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
	 * 
	 * Example:
	 * 
	 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 8 -> 0 -> 7
	 */
	
	
	// Solution 1: reverse input and output--> LC 2
	/*
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int carry = 0;

        ListNode result = new ListNode(0);
        ListNode cur = result;

        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;

            int sum = a + b + carry;
            carry = sum / 10;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry != 0) {
            cur.next = new ListNode(carry);
        }

        return reverse(result.next);
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
	
	
	// Solution 2: Follow up -- reversing the lists is not allowed.
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = null;
        
        int carry = 0;
        int sum = 0;
        
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            
            sum = carry + a + b;
            carry = sum / 10;
            
            ListNode node = new ListNode(sum % 10);
            dummy.next = node;
            node.next = cur;
            cur = node;
        }
        
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            dummy.next = node;
            node.next = cur;
            cur = node;
        }
        
        return dummy.next;
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
