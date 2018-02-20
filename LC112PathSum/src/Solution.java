
public class Solution {
	/*
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given sum.
	 * 
	 * For example:
	 * 
	 * Given the below binary tree and sum = 22,
	 * 
	 *               5
	 *              / \
	 *             4   8
	 *            /   / \
	 *           11  13  4
	 *          /  \      \
	 *         7    2      1
	 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	 */
	
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        
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
 		
 		System.out.println(hasPathSum(node1, 14));
 	}
}
