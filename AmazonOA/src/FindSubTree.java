
public class FindSubTree {
	/* Lintcode 628 Maximum Subtree 
	 * 
	 * Given a binary tree, find the subtree with maximum sum. Return the root of the subtree. 
	 * 
	 * Example
	 * 
	 * Given a binary tree:
     *		1
   	 *    /   \
     *  -5     2
     *  / \   /  \
     * 0   3 -4  -5 
     * return the node with value 3.
	 */
	
	/**
    * @param root the root of binary tree
    * @return the maximum weight node
    */
	
   private static TreeNode result = null;
   private static int maxWeight = Integer.MIN_VALUE;
   
   public static TreeNode findSubtree(TreeNode root) {
       helper(root);
       return result;
   }
   
   private static int helper(TreeNode root) {
	   if (root == null) {
		   return 0;
	   }
	   
	   int left = helper(root.left);
	   int right = helper(root.right);
	   
	   if (result == null || left + right + root.val > maxWeight) {
		   result = root;
		   maxWeight = left + right + root.val;  
	   }
	   
	   return left + right + root.val;
   }
   
   public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(-5);
		TreeNode node3 = new TreeNode(2);
		TreeNode node4 = new TreeNode(0);
		TreeNode node5 = new TreeNode(3);
		TreeNode node6 = new TreeNode(-4);
		TreeNode node7 = new TreeNode(-5);


	
		node1.left  = node2;
		node1.right = node3;
		node2.left  = node4;
		node2.right = node5;
		node3.left  = node6;
		node3.right = node7;

		
		System.out.println(findSubtree(node1));
	}
}
