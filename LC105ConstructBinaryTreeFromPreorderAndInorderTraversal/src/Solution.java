import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given preorder and inorder traversal of a tree, construct the binary tree.
	 */
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        
        Map<Integer, Integer> inorderMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return helper(preorder, inorderMap, 0, 0, preorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, Map<Integer, Integer> inorderMap, int cur, int left, int right) {
        TreeNode root = new TreeNode(preorder[cur]);
        
        int mid = inorderMap.get(preorder[cur]);
        
        if (left < mid) {
            root.left = helper(preorder, inorderMap, cur + 1, left, mid - 1);
        }
        
        if(right > mid) {
            root.right = helper(preorder, inorderMap, cur + mid - left + 1 , mid + 1, right);
        }
        
        return root;
    }
    
    public static void main(String[] args) {
		int[] preorder = {1, 2, 3, 4, 5};
		int[] inorder = {3,2,4,1,5};
		


	}
}
