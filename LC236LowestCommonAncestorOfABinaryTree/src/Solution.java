
public class Solution {
	/*
	 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
	 * 
	 * According to the definition of LCA on Wikipedia: “The lowest common ancestor 
	 * is defined between two nodes v and w as the lowest node in T that has both v and w as 
	 * descendants (where we allow a node to be a descendant of itself).”
	 *
     *      _______3______
     *     /              \
     *  ___5__          ___1__
     * /      \        /      \
     * 6      _2       0       8
     *       /  \
     *       7   4
	 *
	 *
	 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
	 * Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself 
	 * according to the LCA definition.
	 */
	
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	
    	if (root == null || root == p || root == q) {
    		return root;
    	}
    	
    	TreeNode left = lowestCommonAncestor(root.left, p, q);
    	TreeNode right = lowestCommonAncestor(root.right, p, q);
    	
    	if (left != null && right != null) {
    		return root;
    	} else if (left != null) {
    		return left;
    	} else {
    		return right;
    	}
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
 		
 		System.out.println(lowestCommonAncestor(node1, node4, node6).val);
    }
}
