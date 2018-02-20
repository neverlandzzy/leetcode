import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * Given a binary tree, find the leftmost value in the last row of the tree.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 *     2
	 *    / \
	 *   1   3
	 *   
	 * Output:
	 * 1
	 * 
	 * Example 2: 
	 * 
	 * Input:
     *
     *       1
     *      / \
     *     2   3
     *    /   / \
     *   4   5   6
     *  /
     * 7
     * 
     * Output:
     * 7
     * 
     * Note: You may assume the tree (i.e., the given root node) is not NULL.
	 */
	
    public static int findBottomLeftValue(TreeNode root) {
    	
    	// not need for this question as we may assume the tree is not NULL. 
    	/*
        if (root == null) {
            return -1;
        }
        */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int left = root.val;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean leftMost = true;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                if (leftMost) {
                    left = node.val;
                    leftMost = false;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return left;
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(6);
 		TreeNode node7 = new TreeNode(7);
 		
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node3.left  = node5;
 		node3.right = node6;
 		node4.left  = node7;

 		System.out.println(findBottomLeftValue(node1));
	}
}
