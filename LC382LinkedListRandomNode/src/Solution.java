import java.util.Random;


public class Solution {
	
	/*
	 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
	 * 
	 * Follow up:
	 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
	 * 
	 * Example:
	 * 
	 * // Init a singly linked list [1,2,3].
	 * ListNode head = new ListNode(1);
	 * head.next = new ListNode(2);
	 * head.next.next = new ListNode(3);
	 * Solution solution = new Solution(head);
	 * 
	 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
	 * solution.getRandom();
	 */
	
	// Reservoir Sampling
	
	ListNode head;
	
	/** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
	public Solution(ListNode head) {
		this.head = head;
	}
	
	/** Returns a random node's value. */
	public int getRandom() {
	    int count = 1;
	    ListNode node = head;
	    int result = node.val;
	    Random random = new Random();
	    
	    while (node != null) {
	    	if (random.nextInt(count) == 0) {
	    		result = node.val; 
	    	}
	    	count++;
	    	node = node.next;
	    }
	    
	    return result;
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		node1.next = node2;
		node2.next = node3;
		
		Solution solution = new Solution(node1);
		
		System.out.println(solution.getRandom());
	}
}
