import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * 
	 * For example:
	 * 
	 * Given binary tree {1,#,2,3},
	 * 	1
	 *   \
	 *    2
	 *   /
	 *  3
	 *  
	 *  return [1,3,2].
	 *  Note: Recursive solution is trivial, could you do it iteratively?
	 */
	
	// Solution 1: Recursion
	/*
	public static List<Integer> inorderTraversal(TreeNode root) {       
    	List<Integer> result = new ArrayList<Integer>();    	
    	traverse(root, result);    	
    	return result;
    }
    
    private static void traverse(TreeNode root, List<Integer> result) {
    	if (root == null) {
    		return;
    	} 
    
    	traverse(root.left, result);
    	result.add(root.val);
    	traverse(root.right, result);
    }
    */
	
	// Solution 2: Iteration, using stack
	/*
    public static List<Integer> inorderTraversal(TreeNode root) {
  
        List<Integer> resultList = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
        	return resultList;
        }
        
        TreeNode currNode = root;
        
        while (currNode != null) {
        	stack.push(currNode);
        	currNode = currNode.left;
        }
        
        while (!stack.isEmpty()) {
        	currNode = stack.pop();
        	resultList.add(currNode.val);
        	currNode = currNode.right;
        	while(currNode != null) {
            	stack.push(currNode);
            	currNode = currNode.left;
        	}
        }
        
        
        return resultList;
    }
    */
	
	// Solution 3: Morris Traversal
	// http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        TreeNode cur = root;
        TreeNode pre = null;
        
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    result.add(cur.val);
                    cur = cur.right;
                }
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
 		
 		System.out.println(inorderTraversal(node1));
 	}
}
