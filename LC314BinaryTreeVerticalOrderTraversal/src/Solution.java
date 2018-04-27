import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Solution {
	/*
	 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
	 * 
	 * If two nodes are in the same row and column, the order should be from left to right.
	 * 
	 * Examples:
	 * 
	 * Given binary tree [3,9,20,null,null,15,7],
	 *    3
	 *   /\
	 *  /  \
	 *  9  20
	 *     /\
	 *    /  \
	 *   15   7
	 * return its vertical order traversal as:
	 * [
	 *   [9],
	 *   [3,15],
	 *   [20],
	 *   [7]
	 * ]
	 * Given binary tree [3,9,8,4,0,1,7],
	 *      3
	 *     /\
	 *    /  \
	 *    9   8
	 *   /\  /\
	 *  /  \/  \
	 *  4  01   7
	 * return its vertical order traversal as:
	 * [
	 *   [4],
	 *   [9],
	 *   [3,0,1],
	 *   [8],
	 *   [7]
	 * ]
	 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
	 * 
	 *      3
	 *     /\
	 *    /  \
	 *    9   8
	 *   /\  /\
	 *  /  \/  \
	 *  4  01   7
	 *     /\
	 *    /  \
	 *    5   2
	 * return its vertical order traversal as:
	 * [
	 *   [4],
	 *   [9,5],
	 *   [3,0,1],
	 *   [8,2],
	 *   [7]
	 * ]
	 */
	
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
        	return result;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        
        queue.offer(root);
        colQueue.offer(0);
        int min = 0;
        int max = 0;
        
        while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	int col = colQueue.poll();
        	
        	if (!map.containsKey(col)) {
        		map.put(col, new ArrayList<>());
        	}
        	
        	map.get(col).add(node.val);
        	
        	if (node.left != null) {
        		queue.offer(node.left);
        		colQueue.offer(col - 1);
        		min = Math.min(min, col - 1);
        	}
        	
        	if (node.right != null) {
        		queue.offer(node.right);
        		colQueue.offer(col + 1);
        		max = Math.max(max, col + 1);
        	}
        }
        
        for (int i = min; i <= max; i++) {
        	result.add(map.get(i));
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(9);
		TreeNode node3 = new TreeNode(8);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(0);
		TreeNode node6 = new TreeNode(1);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(5);
		TreeNode node9 = new TreeNode(2);
		
		node1.left  = node2;
		node1.right = node3;
		node2.left  = node4;
		node2.right = node5;
		node3.left  = node6;
		node3.right = node7;
		node5.right = node9;
		node6.left  = node8; 
		
		System.out.println(verticalOrder(node1));
	}
    
}
