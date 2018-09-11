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
	 * We are given a binary tree (with root node root), a target node, and an integer value K.
	 * 
	 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
	 * 
	 * Example 1:
	 * 
	 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
	 * 
	 * Output: [7,4,1]
	 * 
	 * Explanation: 
	 * The nodes that are a distance 2 from the target node (with value 5)
	 * have values 7, 4, and 1.
	 * 
	 * Note that the inputs "root" and "target" are actually TreeNodes.
	 * The descriptions of the inputs above are just serializations of these objects.
	 * 
	 * Note:
	 * 	1. The given tree is non-empty.
	 * 	2. Each node in the tree has unique values 0 <= node.val <= 500.
	 * 	3. The target node is a node in the tree.
	 * 	4. 0 <= K <= 1000.
	 */
	
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
        	return result;
        }
        
        if (K == 0) {
        	result.add(target.val);
        	return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	
        	if (!map.containsKey(node)) {
        		map.put(node, new ArrayList<>());
        	}
        	
        	if (node.left != null) {
        		if (!map.containsKey(node.left)) {
        			map.put(node.left, new ArrayList<>());
        		}
        		
        		map.get(node).add(node.left);
        		map.get(node.left).add(node);
        		queue.offer(node.left);
        	}
        	
        	if (node.right != null) {
        		if (!map.containsKey(node.right)) {
        			map.put(node.right, new ArrayList<>());
        		}
        		
        		map.get(node).add(node.right);
        		map.get(node.right).add(node);
        		queue.offer(node.right);
        	}
        }

        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int level = 1;
        boolean flag = false;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	
        	if (level == K) {
        		flag = true;
        	}
        	
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		for (TreeNode next: map.get(node)) {
        			if (!visited.contains(next)) {
        				if (!flag) {
	        				queue.offer(next);
	        				visited.add(next);
        				} else {
        					result.add(next.val);
        				}
        			}
        		}
        	}
        	
        	if (flag) {
        		break;
        	}
        	level++;
        }
        
        return result;           
    }
}
