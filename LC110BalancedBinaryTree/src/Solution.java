
public class Solution {
	/*
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * For this problem, a height-balanced binary tree is defined as a binary tree in 
	 * which the depth of the two subtrees of every node never differ by more than 1.
	 */
	
    public static boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }
    
    private static int maxDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int left = maxDepth(root.left);
    	int right = maxDepth(root.right);
    	
    	if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
    		return -1;
    	}
    	
    	return Math.max(left, right) + 1;
    }
    
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
 		
 		System.out.println(isBalanced(node1));
 	}
}
