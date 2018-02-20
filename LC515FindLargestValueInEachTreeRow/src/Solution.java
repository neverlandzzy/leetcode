import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/*
	 * You need to find the largest value in each row of a binary tree.
	 * 
	 * Example:
	 * 
	 * Input: 
	 * 
     *     1
     *    / \
     *   3   2
     *  / \   \  
     * 5   3   9 
     * 
     * Output: [1, 3, 9]
	 */
	
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        if (root == null) {
        	return result;
        }
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	int size = queue.size();

        	int max = Integer.MIN_VALUE;
        	
        	for (int i = 0; i < size; i++) {
        		
        		TreeNode node = queue.poll();
        		max = Math.max(max, node.val);
        		
        		if (node.left != null) {
        			queue.offer(node.left);
        		}
        		
        		if (node.right != null) {
        			queue.offer(node.right);
        		}
        		
        	}
        	result.add(max);
        }        
        return result;
    }
    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(3);
 		TreeNode node3 = new TreeNode(2);
 		TreeNode node4 = new TreeNode(5);
 		TreeNode node5 = new TreeNode(3);
 		TreeNode node6 = new TreeNode(9);

 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.right = node6;
 		
 		System.out.println(largestValues(node1));
	}
}
