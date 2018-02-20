
public class Solution {
	/*
	 * Given a binary tree, return the tilt of the whole tree.
	 * 
	 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum 
	 * of all right subtree node values. Null node has tilt 0.
	 * 
	 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
	 * 
	 * Example:
	 * Input: 
	 *          1
	 *        /   \
	 *       2     3
	 * Output: 1
	 * Explanation: 
	 * Tilt of node 2 : 0
	 * Tilt of node 3 : 0
	 * Tilt of node 1 : |2-3| = 1
	 * Tilt of binary tree : 0 + 0 + 1 = 1
	 * Note:
	 * 
	 * The sum of node values in any subtree won't exceed the range of 32-bit integer.
	 * All the tilt values won't exceed the range of 32-bit integer.
	 */
	
    public static int findTilt(TreeNode root) {
        int[] sum = new int[1];
        helper(root, sum);
        return sum[0];
    }
    
    private static int helper(TreeNode root, int[] sum) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int left = helper(root.left, sum);
    	int right = helper(root.right, sum);
    	sum[0] += Math.abs(left - right);
    	
    	return left + right + root.val;
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		
		node1.left = node2;
		node1.right = node3;
		
		System.out.println(findTilt(node1));
	}
}
