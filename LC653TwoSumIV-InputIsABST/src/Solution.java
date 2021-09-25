import java.util.HashSet;
import java.util.Set;


public class Solution {

	/**
	 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to 
	 * the given target.
	 * 
	 * Example 1:
	 * Input: 
	 *     5
	 *    / \
	 *   3   6
	 *  / \   \
	 * 2   4   7
	 * 
	 * Target = 9
	 * 
	 * Output: True
	 * 
	 * Example 2:
	 * Input: 
	 *     5
	 *    / \
	 *   3   6
	 *  / \   \
	 * 2   4   7
	 * 
	 * Target = 28
	 * 
	 * Output: False 
	 */
	
	// Solution 1: Inorder + two pointers - Time:O(n), Space:O(n)
	/*
    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        
        int i = 0;
        int j = list.size() - 1;
        
        while (i < j) {
        	int sum = list.get(i) + list.get(j);
        	if (sum == k) {
        		return true;
        	}
        	
        	if (sum < k) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return false;
    }
    
    private static void inorderTraversal(TreeNode root, List<Integer> list) {
    	if (root == null) {
    		return;
    	}
    	
    	inorderTraversal(root.left, list);
    	list.add(root.val);
    	inorderTraversal(root.right, list);
    }
    */
    
    // Solution 2: DFS + HashSet - Time:O(n), Space:O(n)
    public static boolean findTarget(TreeNode root, int k) {
    	Set<Integer> set = new HashSet<>();
    	
    	return dfs(root, set, k);
    }
    
    private static boolean dfs(TreeNode root, Set<Integer> set, int k) {
    	if (root == null) {
    		return false;
    	}
    	
    	if (set.contains(k - root.val)) {
    		return true;
    	}
    	
    	set.add(root.val);
    	
    	return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
    
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(5);
    	TreeNode node2 = new TreeNode(3);
    	TreeNode node3 = new TreeNode(6);
    	TreeNode node4 = new TreeNode(2);
    	TreeNode node5 = new TreeNode(4);
    	TreeNode node6 = new TreeNode(7);
    	
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node2.left  = node4;
    	node2.right = node5; 
    	node3.right = node6;
    	
    	System.out.println(findTarget(node1, 9));
    	System.out.println(findTarget(node1, 28));
	}
}
