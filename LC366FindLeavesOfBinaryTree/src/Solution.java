import java.util.ArrayList;
import java.util.List;


public class Solution {
	
	/*
	 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, 
	 * repeat until the tree is empty.
	 * 
	 * Example:
	 * Given binary tree 
     *     1
     *    / \
     *   2   3
     *  / \     
     * 4   5    
     * Returns [4, 5, 3], [2], [1].
     *
     * Explanation:
     * 1. Removing the leaves [4, 5, 3] would result in this tree:
     *
     *     1
     *    / 
     *   2          
     * 
     * 2. Now removing the leaf [2] would result in this tree:
     *
     *     1          
     * 
     * 3. Now removing the leaf [1] would result in the empty tree:
     *     []         
     *     
     * Returns [4, 5, 3], [2], [1].
	 */
    public static List<List<Integer>> findLeaves(TreeNode root) {
    
    	List<List<Integer>> result = new ArrayList<>();
    	helper(root, result);
    	
    	return result;
    }
    
    private static int helper(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left, result);
        int right = helper(root.right, result);
        
        int depth = Math.max(left, right);
        
        if (result.size() == depth) {
            result.add(new ArrayList<Integer>());    
        }
        
        result.get(depth).add(root.val);
        
        return depth + 1;
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		
 		node1.left = node2;
 		node1.right  = node3;
 		node2.left = node4;
 		node2.right = node5;

 		System.out.println(findLeaves(node1));
	}
}
