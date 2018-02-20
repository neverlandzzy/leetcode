
public class Solution {
	/*
	 * Given a binary tree, find the maximum path sum.
	 * 
	 * For this problem, a path is defined as any sequence of nodes from some starting 
	 * node to any node in the tree along the parent-child connections. The path does not need 
	 * to go through the root.
	 * 
	 * For example:
	 * 
	 * Given the below binary tree,
	 * 
	 *    1
	 *   / \
	 *  2   3
	 *  
	 *  Return 6.
	 */
	
	// Solution 1:
	/*
    private static class ResultType {
    	int singlePath, maxPath;
    	
    	ResultType(int singlePath, int maxPath) {
    		this.singlePath = singlePath;
    		this.maxPath = maxPath;
    	}
    }
    
    private static ResultType helper(TreeNode root) {
    	if (root == null) {
    		return new ResultType(0, Integer.MIN_VALUE);
    	}
    	
    	ResultType left = helper(root.left);
    	ResultType right = helper(root.right);
    	
    	int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
    	singlePath = Math.max(singlePath, 0);
    	
    	int maxPath = Math.max(left.maxPath, right.maxPath);
    	maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
    	
    	return new ResultType(singlePath, maxPath);
    	
    }
    
    public static int maxPathSum(TreeNode root) {
    	ResultType result = helper(root);
    	return result.maxPath;
    }
    */
	
	// Solution 2:
    public static int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        helper(root, max);
        
        return max[0];
    }
    
    // helper() 求的是root这一点上能达到的最大和
    private static int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        
        int left = Math.max(0, helper(root.left, max));
        int right = Math.max(0, helper(root.right, max));
        max[0] = Math.max(max[0], left + right + root.val);
        
        return Math.max(left, right) + root.val;
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
 		
 		System.out.println(maxPathSum(node1));
    }
}
