
public class Solution {
	/*
	 * Given a binary tree, you need to compute the length of the diameter of the tree. 
	 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
	 * This path may or may not pass through the root.
	 * 
	 * Example:
	 * 
	 * Given a binary tree 
     *     1
     *    / \
     *   2   3
     *  / \     
     * 4   5    
     * 
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     * Note: The length of path between two nodes is represented by the number of edges between them.
	 */

	// 类似LC124
	/*
	private static int max = 0;
	
    public static int diameterOfBinaryTree(TreeNode root) {

    	maxDepth(root);
    	return max;
    }
    
    private static int maxDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int left = maxDepth(root.left);
    	int right = maxDepth(root.right);
    	max = Math.max(max, left + right);
    	
    	return Math.max(left, right) + 1;
    }
    */
	
	// Without using global variable 
    public static int  diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        maxDepth(root, max);
        
        return max[0];
    }
    
    // maxDepth() 求的是root这一点上能达到的最大diameter
    private static int  maxDepth(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left, max);
        int right = maxDepth(root.right, max);
        
        max[0] = Math.max(max[0], left + right);
        
        return Math.max(left, right) + 1;
    }
    
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);

 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		
 		System.out.println(diameterOfBinaryTree(node1));
 	}
}
