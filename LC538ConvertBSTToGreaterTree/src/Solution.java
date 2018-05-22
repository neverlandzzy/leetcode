
public class Solution {
	/*
	 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is 
	 * changed to the original key plus sum of all keys greater than the original key in BST.
	 * 
	 * Example:
	 * 
	 * Input: The root of a Binary Search Tree like this:
     *         5
     *       /   \
     *      2     13
     * 
     * Output: The root of a Greater Tree like this:
     *        18
     *       /   \
     *     20     13
	 */
	
	// Solution 1:
	// Time: O(n), Space: O(n)
	/*
	int sum = 0;
    public TreeNode convertBST(TreeNode root) {
    	if (root == null) {
    		return null;
    	}
    	
        convertBST(root.right);
        sum += root.val;
        
        root.val = sum;
        convertBST(root.left);
        
        return root;
    }
    */
	
	// Solution 2: to get rid of instance variable 
    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[1];
        
        return helper(root, sum);
    }
    
    public TreeNode helper(TreeNode root, int[]sum) {
        if (root == null) {
    		return null;
    	}
        
        helper(root.right, sum);
        int tmp = root.val;
        root.val += sum[0];
        sum[0] += tmp;
        helper(root.left, sum);
        
        return root;
    }
}
