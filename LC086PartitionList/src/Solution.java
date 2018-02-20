
public class Solution {
	/*
	 * Given a linked list and a value x, partition it such that all nodes less 
	 * than x come before nodes greater than or equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of the two partitions.
	 * 
	 * For example,
	 * 
	 * Given 1->4->3->2->5->2 and x = 3,
	 * return 1->2->2->4->3->5.
	 */
	
	// Solution 1:
	/*
    public static ListNode partition(ListNode head, int x) {
        
        if (head == null) {
            return head;
        }
        
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        
        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = head;
            } else {
                right.next = head;
                right = head;
            }
            
            head = head.next;
        }
        
        right.next = null;
        left.next = rightDummy.next;
                
        return leftDummy.next;
    }
	*/
	
	// Solution 2:
    public static ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        
        ListNode pre = dummy;
        
        while (pre.next != null && pre.next.val < x) {
        	pre = pre.next;
        }
        
        ListNode cur = pre;
        // Cannot use ListNode cur = pre.next; since if head is null, pre will be null, pre.next is error.  
        
        while (cur.next != null) {
        	if (cur.next.val >= x) {
        		cur = cur.next;
        	} else {
        		ListNode tmp = cur.next.next;
        		cur.next.next = pre.next;
        		pre.next = cur.next;
        		cur.next = tmp;
        		pre = pre.next;
        	}
        	
        	
        }
        return dummy.next;
    }
    
    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(4);
    	ListNode node3 = new ListNode(3);
    	ListNode node4 = new ListNode(2);
    	ListNode node5 = new ListNode(5);
    	ListNode node6 = new ListNode(2);
    	
    	ListNode head = node1;
    	
    	node1.next = node2;
    	node2.next = node3;
    	node3.next = node4;
    	node4.next = node5;
    	node5.next = node6;
    	
    	while(head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	
    	ListNode newHead = node1;
    	newHead = partition(newHead, 1);
    	
    	System.out.println();
    	while(newHead != null) {
    		System.out.print(newHead.val + "->");
    		newHead = newHead.next;
    	}
	}
}
