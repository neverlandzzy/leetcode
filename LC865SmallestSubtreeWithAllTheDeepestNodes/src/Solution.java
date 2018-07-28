import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
	 * 
	 * A node is deepest if it has the largest depth possible among any node in the entire tree.
	 * 
	 * The subtree of a node is that node, plus the set of all descendants of that node.
	 * 
	 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
	 * 
	 * Example 1:
	 * 
	 * Input: [3,5,1,6,2,0,8,null,null,7,4]
	 * Output: [2,7,4]
	 * Explanation:
	 * 
	 * We return the node with value 2, colored in yellow in the diagram.
	 * The nodes colored in blue are the deepest nodes of the tree.
	 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
	 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
	 * Both the input and output have TreeNode type.
	 */
	
	
	 // The result of a subtree is:
	 // Result.node: the largest depth node that is equal to or
	 //              an ancestor of all the deepest nodes of this subtree.
	 // Result.dist: the number of nodes in the path from the root
	 //              of this subtree, to the deepest node in this subtree.

	public static class Result {
	    TreeNode node;
	    int dist;
	    Result(TreeNode node, int dist) {
	        this.node = node;
	        this.dist = dist;
	    }		
	}

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root).node;
    }
    
    public static Result helper(TreeNode root) {
    	if (root == null) {
    		return new Result(null, 0);
    	}
    	
    	Result left = helper(root.left);
    	Result right = helper(root.right);
    	
    	if (left.dist > right.dist) {
    		return new Result(left.node, left.dist + 1);
    	} 
    	if (right.dist > left.dist) {
    		return new Result(right.node, right.dist + 1);
    	}
    	
    	return new Result(root, left.dist + 1);
    }
    
    
    // Solution 2: iteration
    /*
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Map<TreeNode, Integer> depth = new HashMap<>();
        
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(root);
        map.put(root, root);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		depth.put(node, level);
        		
        		if (node.left != null) {
        			map.put(node.left, node);
        			queue.offer(node.left);
        		}
        		
        		if (node.right != null) {
        			map.put(node.right, node);
        			queue.offer(node.right);
        		}
        	}
        	
        	level++;
        }
        
        Set<TreeNode> deepest = new HashSet<>();
        for (TreeNode key: depth.keySet()) {
        	if (depth.get(key) == level - 1) {
        		deepest.add(key);
        	}
        }

        if (deepest.size() == 1) {
            return deepest.iterator().next();
        }
        
        Set<TreeNode> set = new HashSet<>();
        int count = 0;
        int size = 0;
        
        while (true) {
	        for (TreeNode node: deepest) {
	        	TreeNode result = map.get(deepest.iterator().next());
	        	
	        	set.add(map.get(node));
	        	count++; 
	        	
	        	if (result == map.get(node)) {        		
	        		size++;       		
	        	} 
	        	
	        	if (count == deepest.size()) {
	        		if (size == count) {
	        			return result;
	        		} else {
	            		deepest = new HashSet<>(set);
	            		set.clear();
	            		count = 0;
	            		size = 0;
	        		}
	        	}
	        }
        }
    }
    */
    public static void main(String[] args) {
    	TreeNode node0 = new TreeNode(0);
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	TreeNode node6 = new TreeNode(6);
    	TreeNode node7 = new TreeNode(7);
    	TreeNode node8 = new TreeNode(8);
    	
    	node3.left = node5;
    	node3.right = node1;
    	node5.left = node6;
    	node5.right = node2;
    	node2.left = node7;
    	node2.right = node4;
    	node1.left = node0;
    	node1.right = node8;
    	
    	System.out.println(subtreeWithAllDeepest(node3).val);
	}
}
