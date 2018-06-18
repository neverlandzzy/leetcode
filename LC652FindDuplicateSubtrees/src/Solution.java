import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node 
	 * of any one of them.
	 * 
	 * Two trees are duplicate if they have the same structure with same node values.
	 * 
	 * Example 1:
	 *         1
 	 *        / \
	 *       2   3
	 *      /   / \
	 *     4   2   4
	 *        /
	 *       4
	 * The following are two duplicate subtrees:
	 * 
	 *       2
	 *      /
	 *     4
	 * and
	 * 
	 *     4
	 * Therefore, you need to return above trees' root in the form of a list.
	 */
	
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        
        helper(root, map, result);
        return result;
    }
    
    private static String helper(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
    	if (root == null) {
    		return "#";
    	}
    	
    	String s = root.val + "," + helper(root.left, map, result) + helper(root.right, map, result);
    	
    	// 只有== 1时才加，> 1时再加会重复
    	if (map.containsKey(s) && map.get(s) == 1) {
    		result.add(root);
    	}
    	
    	map.put(s, map.getOrDefault(s, 0) + 1);
    	
    	return s;
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(4);
		TreeNode node7 = new TreeNode(4);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.left = node5;
		node3.right = node6;
		node5.left = node7;
		
		System.out.println(findDuplicateSubtrees(node1));
	}
}
