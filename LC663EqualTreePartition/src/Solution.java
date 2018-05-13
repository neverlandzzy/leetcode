import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the 
	 * equal sum of values after removing exactly one edge on the original tree.
	 * 
	 * Example 1:
	 * Input:     
	 *     5
	 *    / \
	 *   10 10
	 *     /  \
	 *    2   3
	 * 
	 * Output: True
	 * Explanation: 
	 *     5
	 *    / 
	 *   10
	 *       
	 * Sum: 15
	 * 
	 *    10
	 *   /  \
	 *  2    3
	 * 
	 * Sum: 15
	 * 
	 * Example 2:
	 * Input:     
	 *     1
	 *    / \
	 *   2  10
	 *     /  \
	 *    2   20
	 * 
	 * Output: False
	 * Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
	 * Note:
	 * The range of tree node value is in the range of [-100000, 100000].
	 * 1 <= n <= 10000
	 */
	
	// 用map而不用set 是因为对于sum = 0的情况，要求有和为0的子树才能返回true，因此要记录遇到sum = 0的次数
    public static boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = helper(root, map);
        if (sum % 2 != 0) {
        	return false;
        }
        
        if (sum == 0) {
        	return map.get(sum) > 1;
        }
        
        return map.containsKey(sum / 2);
    }
    
    private static int helper(TreeNode root, Map<Integer, Integer> map) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int result = root.val;
    	
    	int left = helper(root.left, map);
    	int right = helper(root.right, map);
    	
    	result += left;
    	result += right;
    	
    	map.put(result, map.getOrDefault(result, 0) + 1);
    	return result;
    }
    
    public static void main(String[] args) {
		TreeNode node11 = new TreeNode(5);
		TreeNode node12 = new TreeNode(10);
		TreeNode node13 = new TreeNode(10);
		TreeNode node14 = new TreeNode(2);
		TreeNode node15 = new TreeNode(3);
		node11.left = node12;
		node11.right = node13;
		node13.left = node14;
		node13.right = node15;
		
		System.out.println(checkEqualTree(node11));
		
		TreeNode node21 = new TreeNode(1);
		TreeNode node22 = new TreeNode(2);
		TreeNode node23 = new TreeNode(10);
		TreeNode node24 = new TreeNode(2);
		TreeNode node25 = new TreeNode(20);
		
		node21.left = node22;
		node21.right = node23;
		node23.left = node24;
		node23.right = node25;
		System.out.println(checkEqualTree(node21));
		
		TreeNode node31 = new TreeNode(0);
		TreeNode node32 = new TreeNode(1);
		TreeNode node33 = new TreeNode(-1);
		node31.left = node32;
		node31.right = node33;
		System.out.println(checkEqualTree(node31));
		
	}
}
