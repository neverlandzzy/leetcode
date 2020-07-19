import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class Solution {
	/**
	 * Given a binary tree, return the postorder traversal of its nodes' values.
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
	 * return [3,2,1].
	 * 
	 */
	
	// Solution 1: Recursion
	/*
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
    	traverse(root, result);    	
    	return result;
	}
	
	private static void traverse(TreeNode root, List<Integer> result) {
		
		if (root == null) {
			return;
		}
			

		traverse(root.left, result);
		traverse(root.right, result);
		result.add(root.val);
	}
	*/
	
	// Solution 2: Divide and Conquer
	/*
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        
        if (root == null) {
        	return result;
        }
        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);
        
        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
        
        return result;
    }
    */
   
	// Solution 3: [RECOMMENDED] Non-recursion
	public static List<Integer> postorderTraversal(TreeNode root) {
       List<Integer> result = new ArrayList<Integer>();
       Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
            return result;
        }
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
                        
            TreeNode node = stack.pop();
           
            result.add(node.val);
            
            if (node.left != null) {
                stack.push(node.left);
            }
            
            if (node.right != null) {
                stack.push(node.right);
            }

        }
        
        Collections.reverse(result);
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
    	
    	System.out.println(postorderTraversal(node1)); 	
    	
	}
}
