import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 *	Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently 
	 *	occurred element) in the given BST.
	 * 
	 *	Assume a BST is defined as follows:
	 * 
	 *	The left subtree of a node contains only nodes with keys less than or equal to the node's key.
	 *	The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
	 *	Both the left and right subtrees must also be binary search trees.
	 * 
	 *	For example:
	 *	Given BST [1,null,2,2],
	 * 
	 *    1
	 *     \
	 *      2
	 *     /
	 *    2
	 *	return [2].
	 *
	 *	Note: If a tree has more than one mode, you can return them in any order.
	 *	
	 *	Follow up: Could you do that without using any extra space? (Assume that the 
	 *	implicit stack space incurred due to recursion does not count).
	 */
	static int max = 0;
	static int count = 1;
	static Integer prev = null;
	
    public static int[] findMode(TreeNode root) {
        
    	List<Integer> list = new ArrayList<Integer>();
    	
    	traverse(root, list);
    	
    	int[] result = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
    	}
    	
    	return result;
    }
    
    private static void traverse(TreeNode root, List<Integer> list) {
    	if (root == null) {
    		return;
    	} 
    
    	traverse(root.left, list);
    	
    	if (prev != null) {
    		if (root.val == prev) {
    			count++;
    		} else {
    			count = 1;
    		}
    	}

    	if (count > max) {
    		list.clear();
    		list.add(root.val);
    		max = count;
    	} else if (count == max) {
    		list.add(root.val);
    	}
    	
    	prev = root.val;
    	
    	traverse(root.right, list);
    }
    
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(5);
 		TreeNode node2 = new TreeNode(3);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(1);
 		TreeNode node5 = new TreeNode(8);
 		TreeNode node6 = new TreeNode(8);

 	
 		node1.left  = node2;
 		node1.right = node5;
 		node2.left  = node4;
 		node2.right = node3;
 		node5.left  = node6;
 		
 		/*
 		 *		    5
 		 *	      /   \
 		 * 	     3     8
 		 *      / \   /
 		 *     1   3 8
 		 */
 		
 		System.out.println(findMode(node1));
 		
 		for (int n: findMode(node1)) {
 			System.out.println(n + ", ");
 		}
 	}
}
