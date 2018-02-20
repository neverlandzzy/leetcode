
public class Solution {
	/*
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path 
	 * could represent a number.
	 * 
	 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
	 * 
	 * Find the total sum of all root-to-leaf numbers.
	 * 
	 * For example,
	 *     1
	 *    / \
	 *   2   3
	 * The root-to-leaf path 1->2 represents the number 12.
	 * The root-to-leaf path 1->3 represents the number 13.
	 * 
	 * Return the sum = 12 + 13 = 25.
	 */
	
    public static int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }
    
    private static int sumNumbersHelper(TreeNode root, int sum) {
    	if (root == null) {
    		return 0;
    	}
    	
    	sum = sum * 10 + root.val;
    	
    	if (root.left == null && root.right == null) {
    		return sum;
        }
        
    	
    	return sumNumbersHelper(root.left, sum) + sumNumbersHelper(root.right, sum);
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
		
		System.out.println(sumNumbers(node1));
	}
}
