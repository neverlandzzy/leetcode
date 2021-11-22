import java.util.HashMap;


public class Solution {
	/**
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 * 
	 */
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	
        if (inorder.length < 1) {
            return null;
        }
        
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < inorder.length; i++) {
        	inorderMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(postorder, inorderMap, inorder.length - 1, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] postorder, HashMap<Integer, Integer> inorderMap, int cur, int start, int end) {
    	
    	TreeNode root = new TreeNode(postorder[cur]);
    	
    	int mid = inorderMap.get(postorder[cur]);

		if (mid < end) {
			root.right = buildTreeHelper(postorder, inorderMap, cur - 1, mid + 1, end);
		}
		if (mid > start) {
			root.left = buildTreeHelper(postorder, inorderMap, cur - (end - mid) - 1, start, mid - 1);
		}

    	return root;
    }
}
