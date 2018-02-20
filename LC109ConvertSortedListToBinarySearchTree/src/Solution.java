
public class Solution {
	/*
	 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
	 */
	
	
	
    private ListNode current;
    
    public TreeNode sortedListToBST(ListNode head) {
       
        int size = 0;
        current = head;
        
        while (current != null) {
            current = current.next;
            size++;
        } 
        
        current = head;
        return helper(0, size - 1);
        
    }
    
    private TreeNode helper(int start, int end) {
        
        if (start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        
        TreeNode left = helper(start, mid - 1);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = helper(mid + 1, end);
        
        root.left = left;
        root.right = right;
        
        return root;
        
    }
    /*
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
        	return null;
        }
        
        int size = 0;
        ListNode node = head;
        
        while (node != null) {
        	node = node.next;
        	size++;
        }
        
        return helper(head, 0, size - 1);
    }
    
    private TreeNode helper(ListNode head, int start, int end) {
    	if (start > end) {
    		return null;
    	}
    	
    	int mid = start + (end - start) / 2;
    	TreeNode left = helper(head, start, mid - 1);
    	TreeNode root = new TreeNode(head.val);
    	head = head.next;
    	TreeNode right = helper(head, mid + 1, end);
    	
    	root.left = left;
    	root.right = right;
    	return root;
    }
    */
}
