import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
	
	/*
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	 */
	
	// Solution 1: Divide & Conquer 
	// Time: O(nlogk), Space: O(1) -- 但是递归占用栈空间
	/* 
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        } 
        
        if (lists.length == 1) {
            return lists[0];
        }
        
        return helper(lists, 0, lists.length - 1);
    }
    
    private static ListNode helper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        ListNode l1 = helper(lists, start, mid);
        ListNode l2 = helper(lists, mid + 1, end);
        
        return merge2Lists(l1, l2);
    }
    
    // LC21
    private static ListNode merge2Lists(ListNode l1, ListNode l2) {
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
    */
	
	// Solution 2: Priority Queue
	// Time: O(nlogk), Space: O(k) -- PQ 存每个list的第一个node
	public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
           
        for (ListNode node: lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            cur.next = node;
            if (node.next != null) {
                heap.offer(node.next);
            }
            cur = cur.next;
        }
        
        return dummy.next;
	}
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		ListNode node9 = new ListNode(9);
		ListNode node10 = new ListNode(10);
		
		// List1 = 1 -> 4 -> 7
		node1.next = node4;
		node4.next = node7;
		
		// List2 = 2 -> 3 -> 10
		node2.next = node3;
		node3.next = node10;
		
		// List3 = 5 -> 6 -> 8 -> 9
		node5.next = node6;
		node6.next = node8;
		node8.next = node9;
	
		ListNode[] listArray = {node1, node2, node5};
		
		ListNode head = mergeKLists(listArray);
		//ListNode head = merge2Lists(node1, node2);
		
    	while (head != null) {
    		
    		System.out.print(head.val + "--->");
    		head = head.next;
    	}
	
	}
}
