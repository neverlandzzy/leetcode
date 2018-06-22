import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * Given a binary tree, find its minimum depth.
	 * 
	 * The minimum depth is the number of nodes along the shortest 
	 * path from the root node down to the nearest leaf node.
	 */
	
	// Solution 1: DFS
	/*
    public static int minDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	if (root.left == null) {
    		return minDepth(root.right) +1;
    	}
    	
    	
    	if (root.right == null) {
    		return minDepth(root.left) +1;
    	}
    	
    	return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    	
    }
    */
	
    // Solution 2: BFS
	
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            level++;
        }
        
        return level;
    }
    
	/*
    public static int minDepth(TreeNode root) {
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	
    	if (root == null) {
    		return 0;
    	}
    	
    	queue.offer(root);
    	int depth = 1;
    	
    	TreeNode rightMost = root;
    	
    	while (!queue.isEmpty()) {
    		TreeNode cur = queue.poll();
    		
    		if (cur.left == null && cur.right == null) {
    			return depth;
    		}
    		
    		if (cur.left != null) {
    			queue.offer(cur.left);
    		}
    		
    		if (cur.right != null) {
    			queue.offer(cur.right);
    		}
    		
    		if (cur == rightMost) {
    			depth += 1;
    			rightMost = (cur.right == null) ? cur.left : cur.right;
    		}
    		
    	}
    	return depth;
    }
    */
    
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
 		
 		System.out.println(minDepth(node1));
 	}
}
