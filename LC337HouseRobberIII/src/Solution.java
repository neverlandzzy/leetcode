
public class Solution {
	/**
	 * The thief has found himself a new place for his thievery again. 
	 * There is only one entrance to this area, called the "root." Besides the root, 
	 * each house has one and only one parent house. After a tour, the smart thief 
	 * realized that "all houses in this place forms a binary tree". It will automatically 
	 * contact the police if two directly-linked houses were broken into on the same night.
	 * 
	 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
	 * 
	 * Example 1:
	 *      3
	 *     / \
	 *    2   3
	 *     \   \ 
	 *      3   1
	 * 
	 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
	 * 
	 * Example 2:
	 *       3
	 *      / \
	 *     4   5
	 *    / \   \ 
	 *   1   3   1
	 * Maximum amount of money the thief can rob = 4 + 5 = 9.
	 */
	
    public static int rob(TreeNode root) {
    	
    	int[] num = helper(root);
    	
    	return Math.max(num[0], num[1]);
        
    }
    
    private static int[] helper(TreeNode root) {
    	if (root == null) {
    		return new int[2];
    	}
    	
    	int[] left  = helper(root.left);
    	int[] right = helper(root.right);
    	
    	int[] result = new int[2]; 
    	
    	result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // result[0]: root is not robed, so we don't care if
    																		   // left or right is robbed, just choose the max one
    	
    	result[1] = root.val + left[0] + right[0]; // result[1]: root is robbed;
    	
    	return result;
    			
    }
    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(3);
 		TreeNode node2 = new TreeNode(4);
 		TreeNode node3 = new TreeNode(5);
 		TreeNode node4 = new TreeNode(1);
 		TreeNode node5 = new TreeNode(3);
 		TreeNode node6 = new TreeNode(1);
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.right = node6;

 		System.out.println(rob(node1));
 	}
}
