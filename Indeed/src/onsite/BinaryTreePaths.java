package onsite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreePaths {
	
	// Solution 1: DFS
	/*
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        dfs(result, sb, root);
        return result;
    }
    
    private static void dfs(List<String> result, StringBuilder sb, TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	int len = sb.length();
    	if (root.left == null && root.right == null) {
    		sb.append(root.val);
    		result.add(sb.toString());
    		sb.setLength(len);
    		return;
    	}
    	
    	sb.append(root.val).append("->");
    	
    	dfs(result, sb, root.left);
    	dfs(result, sb, root.right);
    	sb.setLength(len);
    }
    */
	
	// Solution 2: BFS 	
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		
		if (root == null) {
			return result;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<String> path = new LinkedList<>();
		
		queue.offer(root);
		path.offer("");
		
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			String s = path.poll();
			
			if (node.left == null && node.right == null) {
				s += node.val;
				result.add(s);
				continue;
			}
			
			s += node.val;
			s += "->";
			
			if (node.left != null) {
				queue.offer(node.left);
				path.offer(s);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
				path.offer(s);
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
 		
 		System.out.println(binaryTreePaths(node1));
 	}
}
