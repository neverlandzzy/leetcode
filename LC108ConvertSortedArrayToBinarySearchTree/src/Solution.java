
public class Solution {
	
	/*
	 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
	 */
	
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[]nums, int start, int end) {
        
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        if (start > end) {
            return null;
        }
        
        int mid = (start + end) / 2;
        
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid - 1);
        root.right = helper(nums, mid + 1, end);
        
        return root;
    }
}
