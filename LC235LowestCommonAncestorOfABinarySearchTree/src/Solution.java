
public class Solution {
	/**
	 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in 
	 * the BST.
	 * 
	 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
	 * between two nodes v and w as the lowest node in T that has both v and w as descendants 
	 * (where we allow a node to be a descendant of itself).”
	 *
     *         _______6______
     *        /              \
     *    ___2__          ___8__
     *   /      \        /      \
     *  0      _4       7       9
     *        /  \
     *       3   5
     *       
     * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is 
     * LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA 
     * definition.
	 */
	
	// Solution 1: Recursion
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
        	return null;
        }
        
        if (root.val < p.val && root.val < q.val) {
        	return lowestCommonAncestor(root.right, p, q);
        }
        
        if (root.val > p.val && root.val > q.val) {
        	return lowestCommonAncestor(root.left, p, q);
        }
        
        return root;
    }
    
    // Solution 2: Iteration
    /*
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        
        return root;
    }
    */
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		
		node5.left = node2;
		node2.left = node1;
		node2.right = node4;
		node4.left = node3;
		node5.right = node7;
		node7.left = node6;
		node7.right = node8;

		//System.out.println(lowestCommonAncestor(node5, node4, node6).val);
		System.out.println(lowestCommonAncestor(null, node4, node6));
	
	}
}
