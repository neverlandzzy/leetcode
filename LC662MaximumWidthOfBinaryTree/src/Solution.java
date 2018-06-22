import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a binary tree, write a function to get the maximum width of the given tree. 
	 * The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, 
	 * but some nodes are null.
	 * 
	 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, 
	 * where the null nodes between the end-nodes are also counted into the length calculation.
	 * 
	 * Example 1:
	 * Input: 
	 *            1
	 *          /   \
	 *         3     2
	 *        / \     \  
	 *       5   3     9 
	 * Output: 4
	 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
	 * 
	 * Example 2:
	 * Input: 
	 *           1
	 *          /  
	 *         3    
	 *        / \       
	 *       5   3     
	 * Output: 2
	 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
	 * 
	 * Example 3:
	 * Input: 
	 *           1
	 *          / \
	 *         3   2 
	 *        /        
	 *       5      
	 * Output: 2
	 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
	 * 
	 * Example 4:
	 * Input: 
	 *           1
	 *          / \
	 *         3   2
	 *        /     \  
	 *       5       9 
	 *      /         \
	 *     6           7
	 * Output: 8
	 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
	 * Note: Answer will in the range of 32-bit signed integer.
	 */
	
	// Another way, same idea but more straightforward
	
    public static int widthOfBinaryTree(TreeNode root) {
        List<Integer> leftNodes = new ArrayList<>();
        int[] result = new int[1];
        
        helper(root, leftNodes, 1, 0, result);
        
        return result[0];
    }
    
    private static void helper(TreeNode root, List<Integer> list, int id, int level, int[] result) {
        if (root == null) {
            return;
        }
        
        if (level == list.size()) {
            list.add(id);
        }
        
        result[0] = Math.max(result[0], id - list.get(level) + 1);
        
        helper(root.left, list, id * 2, level + 1, result);
        helper(root.right, list, id * 2 + 1, level + 1, result);
    }
    
    /*
    public static int widthOfBinaryTree(TreeNode root) {
        List<Integer> leftNodes = new ArrayList<>(); // left most nodes at each level;
        return dfs(root, 1, 0, leftNodes);
    }

    private static int dfs(TreeNode n, int id, int d, List<Integer> leftNodes) { // d : depth
        if (n == null) return 0;
        //System.out.println("id = " + id + " d = " + d + "     " +lefts);
        if (d == leftNodes.size()) leftNodes.add(id);   // add left most node
        //System.out.println("id = " + id + " d = " + d + "     " +lefts);
        //System.out.println("-----");
        
        // 这里递归到下一层的left 和 right顺序不能变，否则加入的就不是leftmost点
        return Math.max(id + 1 - leftNodes.get(d), Math.max(dfs(n.left, id * 2, d + 1, leftNodes), dfs(n.right, id * 2 + 1, d + 1, leftNodes)));
    }
    */
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(3);
 		TreeNode node3 = new TreeNode(2);
 		TreeNode node4 = new TreeNode(5);
 		TreeNode node5 = new TreeNode(9);
 		TreeNode node6 = new TreeNode(6);
 		TreeNode node7 = new TreeNode(7);
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node3.right = node5;
 		node4.left  = node6;
 		node5.right = node7;
 		
 		System.out.println(widthOfBinaryTree(node1));
	}
}
