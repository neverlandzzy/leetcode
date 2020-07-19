import java.util.Stack;


public class Solution {
	/**
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 * 
	 * For example, this binary tree is symmetric:
	 * 
	 *     1
	 *    / \
	 *   2   2
	 *  / \ / \
	 * 3  4 4  3
	 * 
	 * But the following is not:
	 *     1
	 *    / \
	 *   2   2
	 *    \   \
	 *     3    3
	 */
	
    // Solution 1: Recursion
	/*
    public static boolean isSymmetric(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	return isSymmetricHelper(root.left, root.right);
    	
    }
    
    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
    	if (left == null && right == null) {
    		return true;
    	} else if (left == null || right == null || left.val != right.val) {
    		return false;
    	} 
    	
    	return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }    
    */
	
    // Solution 2: Non-Recursion
    
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.right);
        stack.push(root.left);
        
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            
            if (left == null && right == null) {
                continue;
            }
            
            if (left == null || right == null) {
                return false;
            }
            
            if (left.val != right.val) {
                return false;
            }
            
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        
        return true;
    	
    }

    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(2);
 		TreeNode node4 = new TreeNode(3);
 		TreeNode node5 = new TreeNode(3);
 		TreeNode node6 = new TreeNode(4);
 		TreeNode node7 = new TreeNode(4);
 		
 		/*
 		 * tree:
 		 *  	    1
 		 *        /   \
 		 *   	 /     \
 		 *  	2       2
 		 *	   / \     / \
 		 *	  3   4   4   3
 		 *         
 		 *          
 		 */
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node6;
 		node3.right = node5;
 		node3.left  = node7;
 		
 		System.out.println(isSymmetric(node1));
 	}
}
