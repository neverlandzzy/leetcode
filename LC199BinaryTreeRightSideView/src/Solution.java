import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/**
	 * Given a binary tree, imagine yourself standing on the right side of it, 
	 * return the values of the nodes you can see ordered from top to bottom.
	 * 
	 * For example:
	 * Given the following binary tree,
	 * 
	 *       1            <---
	 *     /   \
	 *    2     3         <---
	 *     \     \
	 *      5     4       <---
	 * 
	 * You should return [1, 3, 4].
	 */
	
	public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
    
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.val);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }   
        
        return result;
	}
	
	
	// Solution 2:
	/*
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
            return result;
        }
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                
                if (i == size - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }
	*/
	
	// Solution 3:
	/* Another similar solution, same runtime complexity but not concise
	 
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        if (root == null) {
        	return result;
        }
        
        queue.offer(root);
        result.add(root.val);
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	int flag = 0;
        	
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		if (node.right != null) {
        			queue.add(node.right);
        			
        			if (flag == 0) {
        				result.add(node.right.val);
        				flag = 1;
        			}
        		}
        		
        		if (node.left != null) {
        			queue.add(node.left);
        			
        			if (flag == 0) {
        				result.add(node.left.val);
        				flag = 1;
        			}
        		}
        	}
        	
        	for (TreeNode cur: queue) {
        		System.out.print(cur.val + ", ");
        	}
        	
        	System.out.println();
        }
        
        return result;
    }
    */
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node4 = new TreeNode(4);
    	TreeNode node5 = new TreeNode(5);
    	
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node2.right  = node5;
    	node3.right = node4; 
    	
    	System.out.println(rightSideView(node1)); 
	}
}
