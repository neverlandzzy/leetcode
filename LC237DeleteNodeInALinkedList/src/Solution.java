
public class Solution {
	/*
	 * Write a function to delete a node (except the tail) in a singly linked list, 
	 * given only access to that node.
	 * 
	 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with 
	 * value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
	 */
	
    public static void deleteNode(ListNode node) {
    	node.val = node.next.val;
    	node.next = node.next.next;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		ListNode head = node1;
		
		
		while (head != null) {
			System.out.print(head.val);
			System.out.print("->");
			head = head.next;
		}
		
		System.out.println();
		
		deleteNode(node2);
		
		head = node1;
			
		while (head != null) {
			System.out.print(head.val);
			System.out.print("->");
			head = head.next;
		}
		
	}
}
