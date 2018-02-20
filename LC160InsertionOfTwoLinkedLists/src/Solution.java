
public class Solution {
	/*
	 * Write a program to find the node at which the intersection of two singly linked lists begins.
	 * 
	 * For example, the following two linked lists:
	 * 
	 * A:          a1 → a2
	 * 					↘
	 * 					c1 → c2 → c3
	 * 					↗            
	 * B:     b1 → b2 → b3
	 * 
	 * begin to intersect at node c1.
	 * 
	 * 
	 * 
	 * Notes:
	 * If the two linked lists have no intersection at all, return null.
	 * The linked lists must retain their original structure after the function returns.
	 * You may assume there are no cycles anywhere in the entire linked structure.
	 * Your code should preferably run in O(n) time and use only O(1) memory.
	 * 
	 */
	public static void main(String[] args) {
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode b1 = new ListNode(3);
		ListNode b2 = new ListNode(4);
		ListNode b3 = new ListNode(5);
		ListNode c1 = new ListNode(6);
		ListNode c2 = new ListNode(7);
		ListNode c3 = new ListNode(8);
		
		
		//a1.next = a2;
		//a2.next = c1;
		b1.next = a1;
		//b2.next = b3;
		//b3.next = c1;
		//c1.next = c2;
		//c2.next = c3;
		
		ListNode intersection = getIntersectionNode(a1, b1);
		if (intersection != null) {
			System.out.println(intersection.val);
		} else {
			System.out.println("null");
		}
	
	}
	
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode tail = headA;
        
        while (tail.next != null) {
            tail = tail.next;
        }
        
        tail.next = headB;
        ListNode result = detectCycle(headA);
        tail.next = null;
        return result;	
    }

    // LC142
    private static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode joint = null;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                joint = slow;
                break;
            }
        }
        
        if (joint != null) {
            while (joint != head) {
                joint = joint.next;
                head = head.next;
            }
        }
        
        return joint;
    }

}																																										