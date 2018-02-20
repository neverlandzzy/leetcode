import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Solution {
	/*
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the node's key.
	 * The right subtree of a node contains only nodes with keys greater than the node's key.
	 * Both the left and right subtrees must also be binary search trees.
	 */
	
	// Solution 1: In-order traverse:
	/*
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Integer pre = null;
        
        while (root.left != null) {
            root = root.left;
            stack.push(root);
        }    
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            if (pre != null && pre >= node.val) {
                return false;
            }
            
            pre = node.val;
            
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        
        return true;
    }
	*/
	
	// Solution 2: Divide and Conquer
	
    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    private static boolean helper(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }
        
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        
        return helper(root.left, low, root.val) && helper(root.right, root.val, high);
    }
    
    
	
	// Solution 3: recursion inorder traverse
	/*
    public static boolean isValidBST(TreeNode root) {
        Integer[] pre = new Integer[1];

        return helper(root, pre);
    }
    
    private static boolean helper(TreeNode root, Integer[] pre) {

        if (root == null) {
            return true;
        }

        boolean left = helper(root.left, pre);

        if (pre[0] != null && root.val <= pre[0]) {
            return false;
        }

        pre[0] = root.val;
        
        boolean right = helper(root.right, pre);
        
        return left && right;
    }
    */
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(6);
 		TreeNode node7 = new TreeNode(7);

 		
 		/*
 		 * tree:
 		 *  	    1
 		 *        /   \
 		 *   	 /     \
 		 *  	2       3
 		 *	   / \     /
 		 *	  4   5   7
 		 *         \
 		 *          6
 		 */
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node5.right = node6;
 		node3.left  = node7;
 		
 		System.out.println(isValidBST(node1));
 		
 		
 		TreeNode node8 = new TreeNode(4);
 		TreeNode node9 = new TreeNode(3);
 		TreeNode node10 = new TreeNode(5);
 		TreeNode node11 = new TreeNode(7);
 		
 		/*
 		 * tree:
 		 *  	   4
 		 *   	 /   \
 		 *  	3     5
 		 *	           \
 		 *	            7
 		 */
 	
 		node8.left  = node9;
 		node8.right = node10;
 		node10.right  = node11;
 		
 		System.out.println(isValidBST(node8));
 	}
}
