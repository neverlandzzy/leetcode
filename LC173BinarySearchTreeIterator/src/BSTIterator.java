import java.util.Stack;


public class BSTIterator {
	/**
	 * Implement an iterator over a binary search tree (BST). 
	 * Your iterator will be initialized with the root node of a BST.
	 * 
	 * Calling next() will return the next smallest number in the BST.
	 * 
	 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
	 * where h is the height of the tree.
	 */
	
    private Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int val = node.val;
        node = node.right;
        
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        
        return val;
    }
}

