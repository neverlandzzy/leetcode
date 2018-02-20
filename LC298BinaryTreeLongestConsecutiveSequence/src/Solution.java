
public class Solution {
	/*
	 * Given a binary tree, find the length of the longest consecutive sequence path.
	 * 
	 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
	 * The longest consecutive path need to be from parent to child (cannot be the reverse).
	 * 
	 * For example,
	 *    1
	 *     \
     *      3
     *     / \
     *    2   4
     *         \
     *          5
     * Longest consecutive sequence path is 3-4-5, so return 3.
     *   2
     *    \
     *     3
     *    / 
     *   2    
     *  / 
     * 1
     * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
	 */
	
    public static int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int[] max = new int[1];
        helper(root, root.val, 0, max);
        return max[0];
    }
    
    private static void helper(TreeNode root, int target, int cur, int[] max) {
        if (root == null) {
            return;
        }
        
        if (root.val == target) {
            cur++;
        } else {
            cur = 1;
        }
        
        max[0] = Math.max(max[0], cur);
        
        helper(root.left, root.val + 1, cur, max);
        helper(root.right, root.val + 1, cur, max);
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		
 		node1.right = node3;
 		node3.left  = node2;
 		node3.right = node4;
 		node4.right = node5;

 		System.out.println(longestConsecutive(node1));
	}
}
