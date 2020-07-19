import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution {
	/**
	 * Given a binary tree, return the preorder traversal of its nodes' values.
	 * 
	 * For example:
	 * 
	 * Given binary tree {1,#,2,3},
	 * 
	 *    1
	 *     \
	 *      2
	 *     /
	 *    3
	 *    
	 * return [1,2,3].
	 */
	
	// Solution 1: recursion
	/*
    public static List<Integer> preorderTraversal(TreeNode root) {
        
    	List<Integer> result = new ArrayList<Integer>();    	
    	traverse(root, result);    	
    	return result;
    }
    
    private static void traverse(TreeNode root, List<Integer> result) {
    	if (root == null) {
    		return;
    	} 
    	
    	result.add(root.val);
    	traverse(root.left, result);
    	traverse(root.right, result);
    }
    */
	
	// Solution 2: Divide & Conquer
	/*
    public static List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	if (root == null) {
    		return result;
    	}
    	
    	// Divide
    	List<Integer> left = preorderTraversal(root.left);
    	List<Integer> right = preorderTraversal(root.right);
    	
    	// Conquer
    	
    	result.add(root.val);
    	result.addAll(left);
    	result.addAll(right);
    	
    	return result;
    }
    */
	
	// Solution 3: [RECOMMENDED] Non-recursion
	
	public static List<Integer> preorderTraversal(TreeNode root) {
		 List<Integer> result = new ArrayList<Integer>();
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 
		 if(root == null) {
			 return result;
		 }
		 
		 stack.push(root);
		 
		 while(!stack.isEmpty()) {
			 TreeNode node = stack.pop();
			 result.add(node.val);
			 if(node.right != null) {
				 stack.push(node.right);
			 }
			 if(node.left != null) {
				 stack.push(node.left);
			 }
		 }
		 
		 return result;
	}
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	
    	
    	node1.left  = node2;
    	node1.right = node5;
    	node2.left  = node3;
    	node2.right = node4; 
    	
    	System.out.println(preorderTraversal(node1)); 	
    	
	}
}
