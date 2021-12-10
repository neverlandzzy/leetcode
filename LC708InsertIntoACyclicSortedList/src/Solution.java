
public class Solution {
	
	/**
	 * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list 
	 * such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be 
	 * necessarily the smallest value in the cyclic list.
	 * 
	 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, 
	 * the cyclic list should remain sorted.
	 * 
	 * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. 
	 * Otherwise, you should return the original given node.
	 */
	
	// https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/discuss/149374/Java-5ms-One-Pass-and-Two-Pass-Traverse-With-Detailed-Comments-and-Edge-cases!
    //
	// Time: O(n), one-pass
	//
	// 两种情况：
	// 1. 链表中node的值不同，这样存在max->min的交界点：
	//    a. 插入的点的值x在min和max之间，则需要找到node.val <= x <= node.next.val，即为插入位置
	//    b. 插入的点的值为min或max，则插入的位置即为max->x->min。
	// 2. 链表中的值都相等，可以在任意位置插入
	
	public static  Node insert(Node head, int insertVal) {
		
		Node node = new Node(insertVal, null);
		if (head == null) {
			node.next = node;
			return node;
		}
		
		Node cur = head;
		while (true) {
			if (cur.val < cur.next.val) {
				// 1.a
				if (insertVal >= cur.val && insertVal <= cur.next.val) {
					node.next = cur.next;
					cur.next = node;
					break;
				}
			} else if (cur.val > cur.next.val) {
				// 1.b
				if (insertVal >= cur.val || insertVal <= cur.next.val) {
					node.next = cur.next;
					cur.next = node;
					break;
				}				
			} else {
				// 2
				if (cur.next == head) {
					node.next = cur.next;
					cur.next = node;
					break;					
				}
			}
			
			cur = cur.next;
		}
		
		return head;
    }

}
