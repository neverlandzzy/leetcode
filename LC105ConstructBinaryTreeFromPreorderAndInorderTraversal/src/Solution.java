import java.util.HashMap;


public class Solution {
	/*
	 * Given preorder and inorder traversal of a tree, construct the binary tree.
	 */
	
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length < 1) {
            return null;
        }
        
		HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i], i);
		}
		
		return buildTreeHelper(preorder, inorderMap, 0, 0, inorder.length-1);
		
    }
    
    private static TreeNode buildTreeHelper(int[] preorder, HashMap<Integer, Integer> inorderMap, int cur, int start, int end ) {
    	TreeNode root = new TreeNode(preorder[cur]);
    	
    	int mid = inorderMap.get(preorder[cur]);
    	
    	if (start < end) {
    		if (mid > start) {
    			root.left = buildTreeHelper(preorder, inorderMap, cur + 1, start, mid - 1);
    		}
    		if (mid < end) {
    			root.right = buildTreeHelper(preorder, inorderMap, cur + mid - start + 1, mid + 1, end);
    		}
    	}
    	
    	return root;
    }
    
    public static void main(String[] args) {
		int[] preorder = {1, 2, 3, 4, 5};
		int[] inorder = {3,2,4,1,5};
		


	}
}
