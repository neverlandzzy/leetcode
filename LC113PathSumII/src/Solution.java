import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum 
	 * equals the given sum. 
	 * 
	 * For example:
	 * 
	 * Given the below binary tree and sum = 22,
	 * 
	 *               5
	 *              / \
	 *             4   8
	 *            /   / \
	 *           11  13  4
	 *          /  \      \
	 *         7    2      1
	 * 
	 * return
	 * [
	 *    [5,4,11,2],
	 *    [5,8,4,5]   
	 * ]
	 */  
	
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> list = new ArrayList<Integer>();
    	
    	pathSumHelper(root, sum, result, list);
    	
    	return result;
    }
    
    private static void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> list) {
    	if (root == null) {
    		return;
    	}
    	    	
    	list.add(root.val);
    	
    	if (root.left == null && root.right == null) {
    		if (root.val == sum) {
    			result.add(new ArrayList<Integer>(list));
    		}
    	}
    	
    	pathSumHelper(root.left, sum - root.val, result, list);
    	pathSumHelper(root.right, sum - root.val, result, list);
    	list.remove(list.size()-1);
    	
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
 		
 		System.out.println(pathSum(node1, 14));
 	}
}
