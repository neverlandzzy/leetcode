
public class Solution {
	/*
	 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
	 */
	
	
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        
        return helper(head, null);
    }
    
    
    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        
        return root;
    }
    
    /*
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
    */
}
