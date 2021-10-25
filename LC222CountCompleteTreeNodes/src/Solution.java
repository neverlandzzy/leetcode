
public class Solution {
	/**
	 * Given a complete binary tree, count the number of nodes.
	 * 
	 * Definition of a complete binary tree from Wikipedia:
	 * In a complete binary tree every level, except possibly the last, is completely filled, 
	 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h 
	 * nodes inclusive at the last level h.
	 */
	
	// Solution 1: Recursion
	/*
    public static int countNodes(TreeNode root) {
        int left = 0;
        int right = 0;
        
        TreeNode cur = root;
        
        while (cur != null) {
        	cur = cur.left;
        	left++;
        }
        
        cur = root;
        
        while (cur != null) {
        	cur = cur.right;
        	right++;
        }
        
        if (left == right) {
        	return (1 << left) - 1;
        } else {
        	return countNodes(root.left) + countNodes(root.right) + 1;
        }
        
        
    }
    */
	
	// Solution 2: iteration
    public static int countNodes(TreeNode root) {
    	int counter = 0;
    	
    	if (root == null) {
    		return counter;
    	}
    	
    	int h1 = leftH(root.left);
    	int h2 = 0;
    	
    	while (root != null) {
    		h2 = leftH(root.right);
    		//System.out.println("h1: " + h1);
    		//System.out.println("h2: " + h2);
    		
    		if (h1 == h2) {
    			counter += 1 << h1;
    			root = root.right;
    		} else {
    			counter += 1 << (h1 - 1);
    			//或者： counter += 1 << h2;
    			root = root.left;
    		}
    		
    		h1--;
    	}
    	
    	return counter;
    }
    
    private static int leftH(TreeNode curr){
        int h = 0;
        while(curr != null){
            curr = curr.left;
            h++;
        }
        return h;
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
		node2.right = node5;
		
		node3.left = node6;
		
		TreeNode nul = null;
		
		
		System.out.println(countNodes(node1));
		System.out.println(countNodes(nul));
	}
}
