
public class Solution {
	/*
	 * Invert a binary tree.
	 *      4
   	 *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * to
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
	 */
	
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
        	return root;
        }
        
        TreeNode left  = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
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
 		
 		System.out.println(invertTree(node1));


 	}
}
