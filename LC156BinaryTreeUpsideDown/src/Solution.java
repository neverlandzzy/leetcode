public class Solution {
	
	/*
	 * Given a binary tree where all the right nodes are either leaf nodes with a sibling 
	 * (a left node that shares the same parent node) or empty, flip it upside down and turn 
	 * it into a tree where the original right nodes turned into left leaf nodes. Return 
	 * the new root.
	 * 
	 * For example:
	 * Given a binary tree {1,2,3,4,5},
	 *     1
	 *    / \
	 *   2   3
	 *  / \
	 * 4   5
	 * 
	 * return the root of the binary tree [4,5,2,#,#,3,1].
	 *    4
	 *   / \
	 *  5   2
	 *     / \
	 *    3   1  
	 */
	
    // Solution 1: iteration. T: O(n)  S: O(1) 
	/*
    public static TreeNode upsideDownBinaryTree(TreeNode root) {
  	
    	
    	TreeNode p     = root;
    	TreeNode parent = null;
    	TreeNode parentRight = null;
    	
    	while (p != null) {
    		TreeNode left = p.left;
    		TreeNode right = p.right;
    		
    		p.left = parentRight;
    		p.right = parent;
    		
    		parent = p;
    		parentRight = right;
    		p = left;
    	}
    	
    	return parent;
    	
    }
    */
	
	
	// Solution 2: Recursion. T: O(n)  S: O(log(n))
    public static TreeNode upsideDownBinaryTree(TreeNode root) {
    	return helper(root, null);
    }
    
    private static TreeNode helper(TreeNode p, TreeNode parent) {
    	 if (p == null)  {
    		 return parent;
    	 }
    	 
    	 TreeNode root = helper(p.left, p);
    	 p.left = (parent == null) ? null : parent.right;
    	 p.right = parent;
    	 
    	 return root;
    	 
    	 //      1
    	 //    /  \
    	 //   2    3
    	 //  / \ 
    	 // 4   5
    	 // 递归层次：(1, null) -> (2, 1) -> (4, 2) -> (null, 4) 
    	 // 递归到最底层时，p = 4, p.left = null -- root = helper(null, 4), 所以在if(p == null)处返回parent(4), 
    	 // 返回到上一层后，root = 4 , p = 4, parent = 2, 以后逐层返回时，root都是4，不会变，所以答案的root是4
    	 
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node2.left  = node4;
    	node2.right = node5; 
    	
    	System.out.println(upsideDownBinaryTree(node1)); 	
    	
	}
}
