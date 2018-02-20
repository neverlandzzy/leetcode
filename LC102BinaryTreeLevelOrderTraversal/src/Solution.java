import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	/*
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 * For example:
	 * Given binary tree {3,9,20,#,#,15,7},
	 *     3
	 *    / \
	 *   9  20
	 *     /  \
	 *    15   7
	 *    
	 * return its level order traversal as:
	 * 
	 * [
	 * 	[3],
	 * 	[9,20],
	 *  [15,7]
	 * ]
	 * 
	 */

    public static List<List<Integer>> levelOrder(TreeNode root) {
    	
    	/*
    	 * Solution 1:
    	 */
    	/*
    	List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    	
    	List<TreeNode> nodesAtSameLevel = new ArrayList<TreeNode>();
    	
    	if (root == null) {
    		return resultList;
    	}
    	
    	TreeNode node = root;
    	nodesAtSameLevel.add(node);
    	
    	while (nodesAtSameLevel.isEmpty() == false) {
    		List<TreeNode> tempList = new ArrayList<TreeNode>();
    		List<Integer> valueList = new ArrayList<Integer>();
    		
    		for (TreeNode tNode: nodesAtSameLevel) {
    			valueList.add(tNode.val);
    			if (tNode.left != null) {
    				tempList.add(tNode.left);
    			}
    			if (tNode.right != null) {
    				tempList.add(tNode.right);
    			}
    		}
    		resultList.add(valueList);
    		nodesAtSameLevel = tempList;
    		
    	}
    	
    	return resultList;
    	*/
    	
    	/*
    	 * Solution 2: similar to solution 1, but use queue
    	 */
    	List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	
    	if (root == null) {
    		return resultList;
    	}
    	
    	queue.offer(root);
    	
    	while (!queue.isEmpty()) {
    		List<Integer> temp = new ArrayList<Integer>();
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			
    			TreeNode node = queue.poll();
    			temp.add(node.val);
    			
    			if (node.left != null) {
    				queue.offer(node.left);
    			}
    			
    			if (node.right != null) {
    				queue.offer(node.right);
    			}
    		}
    		
    		resultList.add(temp);
    	}
    	//Collections.reverse(resultList);
    	
    	return resultList;
        
    }

	
	public static void main(String arg[]){
		TreeNode root = new TreeNode(3);
		TreeNode n1 = new TreeNode(9);
		TreeNode n2 = new TreeNode(20);
		TreeNode n3 = new TreeNode(15);
		TreeNode n4 = new TreeNode(7);
		
		TreeNode none = null;
		
		root.left = n1;
		root.right = n2;
		n2.left = n3;
		n2.right = n4;
		
		System.out.println(levelOrder(root));
		System.out.println(levelOrder(none));
	}
	 
}


