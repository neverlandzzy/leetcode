import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * Given a binary tree where every node has a unique value, and a target key k, find the closest leaf node to target k in the tree.
	 * 
	 * A node is called a leaf if it has no children.
	 * 
	 * In the following examples, the input tree is represented in flattened form row by row. 
	 * The actual root tree given will be a TreeNode object.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * root = [1, 3, 2], k = 1
	 * Diagram of binary tree:
	 *           1
	 *          / \
	 *         3   2

	 * Output: 2 (or 3)
	 * 
	 * Explanation: Either 2 or 3 is the closest leaf node to 1.
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * root = [1], k = 1
	 * Output: 1
	 * 
	 * Explanation: The closest leaf node is the root node itself.
	 * 
	 * Example 3:
	 * 
	 * Input:
	 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
	 * Diagram of binary tree:
	 *              1
	 *             / \
 	 *            2   3
	 *           /
	 *          4
	 *         /
	 *        5
	 *       /
	 *      6
	 *
	 * Output: 3
	 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest to the node with value 2.
	 * 
	 * Note:
	 * 	1. root represents a binary tree with at least 1 node and at most 1000 nodes.
	 * 	2. Every node has a unique node.val in range [1, 1000].
	 * 	3. There exists some node in the given binary tree for which node.val == k.
	 */
	

    public static int findClosestLeaf(TreeNode root, int k) {
    	Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    	dfs(map, root, null);
    	//System.out.println(map);
    	Queue<TreeNode> queue = new LinkedList<>();
    	Set<TreeNode> set = new HashSet<>();
    	
    	for (TreeNode node: map.keySet()) {
    		if (node.val == k) {
    			queue.offer(node);
    			set.add(node);
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		
    		if (node != null) {
	    		if (map.get(node).size() <= 1) {
	    			return node.val;
	    		}
	    		
	    		for (TreeNode neighbor: map.get(node)) {
	    			if (!set.contains(neighbor)) {
	    				set.add(neighbor);
	    				queue.offer(neighbor);
	    			}
	    		}
    		}    		
    	}
    	
    	return -1;
    }
    
    private static void dfs(Map<TreeNode, List<TreeNode>> map, TreeNode node, TreeNode parent) {
    	if (node == null) {
    		return;
    	}
    	
    	if (!map.containsKey(node)) {
    		map.put(node, new ArrayList<>());
    	}
 
    	if (parent != null && !map.containsKey(parent)) {
    		map.put(parent, new ArrayList<>());
    	}
    	
    	map.get(node).add(parent);
    	
    	if (parent != null) {
    		
    		map.get(parent).add(node);
    	}
    	
    	if (node.left != null) {
    		dfs(map, node.left, node);
    	}
    	
    	if (node.right != null) {
    		dfs(map, node.right, node);
    	}
    }

    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(6);
 		
 		node1.left = node2;
 		node1.right = node3;
 		node2.left = node4;
 		node4.left = node5;
 		node5.left = node6;
 		
 		TreeNode node7 = new TreeNode(1);
 		TreeNode node8 = new TreeNode(2);
 		node7.left = node8;
 		
 		//System.out.println(findClosestLeaf(node1, 2));
 		System.out.println(findClosestLeaf(node7, 1));
	}
}
