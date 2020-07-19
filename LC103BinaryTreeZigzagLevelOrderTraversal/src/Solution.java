import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/**
	 * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
	 * (ie, from left to right, then right to left for the next level and alternate between).
	 * 
	 * For example:
	 * 
	 * Given binary tree {3,9,20,#,#,15,7},
	 * 
	 *     3
	 *    / \
	 *   9  20
	 *      /  \
	 *     15   7
	 * return its zigzag level order traversal as:
   	 * 
	 * [
	 *   [3],
	 *   [20,9],
	 *   [15,7]
	 * ]
	 */
	
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	
    	// Solution 1:
    	/*
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
        	return result;
        }
        
        boolean isOddLevel = false;
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
        	
        	Stack<TreeNode> nextLevelNodes = new Stack<TreeNode>();
        	List<Integer> list = new ArrayList<Integer>();
        	
        	while (!stack.isEmpty()) {
        		TreeNode cur = stack.pop();
        		list.add(cur.val);
        		if (isOddLevel == false) {
        			if (cur.left != null) nextLevelNodes.push(cur.left);
        			if (cur.right != null) nextLevelNodes.push(cur.right);
        		} else {
        			if (cur.right != null) nextLevelNodes.push(cur.right);
        			if (cur.left != null) nextLevelNodes.push(cur.left);
        		}
        	}
        	
        	result.add(list);
        	isOddLevel = !isOddLevel;
        	stack = nextLevelNodes;
        }
        
        return result;
        */
    	
    	// Solution 2: Level-Order + flag
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            if (reverse) {
                Collections.reverse(list);
            }
            result.add(list);
            reverse = !reverse;
        }
        
        return result;
        
        /* 每次在list开头插入元素的效率低，不如上面的方法快
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        if (root == null) {
            return result;
        }
        
        queue.offer(root);
        boolean reverse = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (!reverse) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            reverse = !reverse;
            result.add(list);
        }
        
        return result;
        */
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(9);
		TreeNode node3 = new TreeNode(20);
		TreeNode node4 = new TreeNode(15);
		TreeNode node5 = new TreeNode(7);

		
		/*
		 * tree:
		 *  	    3
		 *        /   \
		 *   	 9    20
		 *	          / \
		 *	         15  7
		 */
	
		node1.left  = node2;
		node1.right = node3;
		node3.left  = node4;
		node3.right = node5;

		
		System.out.println(zigzagLevelOrder(node1));
	}
}
