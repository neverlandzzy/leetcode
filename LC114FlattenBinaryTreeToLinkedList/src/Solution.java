
public class Solution {
	/*
	 * Given a binary tree, flatten it to a linked list in-place.
	 * 
	 * For example,
	 * 
	 * Given
	 * 
	 *      1
     *     / \
     *    2   5
     *   / \   \
     *  3   4   6
	 * The flattened tree should look like:
     *   1
     *    \
     *	   2
     *      \
     *       3
     *        \
     *         4
     *          \
     *           5
     *            \
     *             6
	 */
	
	// Solution 1: recursion T: O(n)  S: O(log(n))
	
    public static void flatten(TreeNode root) {
    	if (root == null) {
    		return;
    	}

    	flatten(root.left);
    	flatten(root.right);

    	if (root.left == null) {
    		return;
    	}
    	
    	TreeNode tmp = root.left;

    	while (tmp.right != null) {
    		tmp = tmp.right;
    	}
    	tmp.right = root.right;
    	root.right = root.left;
    	root.left = null;
    }
    
	
	// Solution 2: Iteration T: O(n)  S:O(1)
    /*
    public static void flatten(TreeNode root) {
        TreeNode node = root;
        
        while (node != null) {
            if (node.left != null) {
                TreeNode next = node.left;
                while (next.right != null) {
                    next = next.right;
                }
                
                next.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
    */
    public static void main(String[] args) {
  		TreeNode node1 = new TreeNode(1);
  		TreeNode node2 = new TreeNode(2);
  		TreeNode node3 = new TreeNode(3);
  		TreeNode node4 = new TreeNode(4);
  		TreeNode node5 = new TreeNode(5);
  		TreeNode node6 = new TreeNode(6);
  		
  		/*
  		 * tree:
  		 *  	   1
  		 *   	 /   \
  		 *  	2     5
  		 *	   / \     \
  		 *	  3   4     6
  		 *        
  		 */
  	
  		node1.left  = node2;
  		node1.right = node5;
  		node2.left  = node3;
  		node2.right = node4;
  		node5.right = node6;

  		
  		flatten(node1);
  		
  		while (node1 != null) {
  			System.out.print(node1.val + "->");
  			node1 = node1.right;
  		}
  		
  		System.out.println();
  	}
}
