
public class Solution {
	
	/**
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 */
	
	
    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        // 此处不能用 fast = head. 因为对于 2->1 中点应该返回2，从而将其拆为2和1。 否则会返回2->1, null。 没有实现拆分，死循环
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // LC21
    private static ListNode merge(ListNode l1, ListNode l2) {
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
    
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode mid = findMiddle(head);
        
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        
        return merge(left, right);
    }
	
	/*
    public static ListNode sortList(ListNode head) {
    	
    	if (head == null || head.next == null) {
    		return head;
    	}
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        }
        
        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;
        
        left = sortList(left);
        right = sortList(right);
        
        return merge(left, right);
        
    }
    
    private static ListNode merge(ListNode left, ListNode right) {
        if (left == null)   return right;
        if (right == null)  return left;
    	ListNode dummy = new ListNode(0);
    	
    	ListNode p = dummy;
    	
    	while(left != null && right != null) {
    		if (left.val > right .val) {
    			p.next = right;
    			right = right.next;
    		} else {
    			p.next = left;
    			left = left.next;
    		}
    		
    		p = p.next;
    	}
    	
    	if (left != null) {
    		p.next = left;
    	}
    	
    	if (right != null) {
    		p.next = right;
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
		
		
		/*
		 * 4->2->3->1->5
		 */
		node4.next = node2;
		node2.next = node3;
		node3.next = node1;
		node1.next = node5;
		
		ListNode head = node4;
		
		ListNode newhead = sortList(head);
		
		while (newhead != null) {
			System.out.print(newhead.val + "->");
			newhead = newhead.next;
		}
		
		
	}
}
