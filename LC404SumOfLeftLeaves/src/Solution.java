import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * Find the sum of all left leaves in a given binary tree.
	 * 
	 * Example:
	 *     3
	 *    / \
	 *   9  20
	 *     /  \
	 *    15   7
	 * 
	 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
	 */
	
	// Solution 1: Iteration
    public static int sumOfLeftLeaves(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<TreeNode>();
        
        if (root == null) {
        	return 0;
        }
        
        queue.offer(root);
        int sum = 0;
        
        while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	
        	if (node.left != null) {
        		if (node.left.left == null && node.left.right == null) {
        			sum += node.left.val;
        		} else {
        			queue.offer(node.left);
        		}
        	} 
        	
        	if (node.right != null) {
        		queue.offer(node.right);
        	}
        }
        
        return sum;
    }
    // Solution 2: recursion
    /*
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
                
        sum += sumOfLeftLeaves(root.right);
          
        return sum;
    }
    */
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(3);
 		TreeNode node2 = new TreeNode(9);
 		TreeNode node3 = new TreeNode(20);
 		TreeNode node4 = new TreeNode(15);
 		TreeNode node5 = new TreeNode(7);

 	
 		node1.left  = node2;
 		node1.right = node3;
 		node3.left  = node4;
 		node3.right = node5;
 		
 		System.out.println(sumOfLeftLeaves(node1));


 	}
}
