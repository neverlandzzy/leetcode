
public class Solution {
	/**
	 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
	 * 
	 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some 
	 * parts being null.
	 * 
	 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal 
	 * parts occurring later.
	 * 
	 * Return a List of ListNode's representing the linked list parts that are formed.
	 * 
	 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
	 * Example 1:
	 * Input:  root = [1, 2, 3], k = 5 
	 * 
	 * Output: [[1],[2],[3],[],[]] 
	 * 
	 * Explanation: The input and each element of the output are ListNodes, not arrays. 
	 * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null. 
	 * The first element output[0] has output[0].val = 1, output[0].next = null. The last element output[4] is null, 
	 * but it's string representation as a ListNode is []. 
	 * 
	 * Example 2:
	 * Input: root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
	 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
	 * 
	 * Explanation:
	 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
	 * 
	 * Note:
	 * 	1. The length of root will be in the range [0, 1000].
	 * 	2. Each value of a node in the input will be an integer in the range [0, 999].
	 * 	3. k will be an integer in the range [1, 50].
	 */
	
    public static ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode node = root;
        ListNode[] result = new ListNode[k];
        
        while (node != null) {
        	length++;
        	node = node.next;
        }
        
        node = root;
        int mod = length % k;
        int avg = length / k;
        
        for (int i = 0; i < result.length; i++) {
        	result[i] = node;
        	ListNode pre = node;
        	for (int j = 0; j < avg; j++) {
        		pre = node;
        		node = node.next;
        	}
        	
        	if (mod > 0) {
        		pre = node;
        		node = node.next;
        		mod--;
        	}
        	
        	if (pre != null) {
        		pre.next = null;
        	}
        }
        
        return result;
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
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node10;

		ListNode[] result1 = splitListToParts(node1, 3);
		print(result1);
		
		ListNode node21 = new ListNode(1);
		ListNode node22 = new ListNode(2);
		ListNode node23 = new ListNode(3);
		ListNode node24 = new ListNode(4);
		
		node21.next = node22;
		node22.next = node23;
		node23.next = node24;
		
		ListNode[] result2 = splitListToParts(node21, 5);
		print(result2);
		
	}
    
    private static void print(ListNode[] list) {
		for (ListNode node: list) {
			while (node != null) {
				System.out.print(node.val + " -> ");
				node = node.next;
			}
			System.out.println();
		}
    }
        
}
