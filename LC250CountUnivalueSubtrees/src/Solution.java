
public class Solution {
	/*
	 * Given a binary tree, count the number of uni-value subtrees.
	 * 
	 * A Uni-value subtree means all nodes of the subtree have the same value.
	 * 
	 * For example:
	 * 
	 * Given binary tree,
     *         5
     *        / \
     *       1   5
     *      / \   \
     *     5   5   5
     * return 4.
	 */
	    
	public static int countUnivalSubtrees(TreeNode root) {
        int[] counter = new int[1];
        helper(root, counter);
        
        return counter[0];

    }
    
    private static boolean helper(TreeNode root, int[] counter) {
    	if (root == null) {
    		return true;
    	}
    	
    	boolean left = helper(root.left, counter);
    	boolean right = helper(root.right, counter);
    	
    	if (left && right) {
    		if (root.left != null && root.left.val != root.val) {
    			return false;
    		}
    		
    		if (root.right != null && root.right.val != root.val) {
    			return false;
    		}
    		counter[0]++;
    		return true;
    	}
    	return false;
    }
    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(5);
 		TreeNode node2 = new TreeNode(1);
 		TreeNode node3 = new TreeNode(5);
 		TreeNode node4 = new TreeNode(5);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(5);
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.right = node6;

 		System.out.println(countUnivalSubtrees(node1));
 	}
}
