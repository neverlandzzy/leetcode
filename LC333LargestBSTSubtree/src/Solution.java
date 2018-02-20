
public class Solution {
	/*
	 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means 
	 * subtree with largest number of nodes in it.
	 * 
	 * Note:
	 * 
	 * A subtree must include all of its descendants.
	 * 
	 * Here's an example:
     *     10
     *    / \
     *   5  15
     *  / \   \ 
     * 1   8   7
     * 
     * The Largest BST Subtree in this case is the highlighted one. 
     * The return value is the subtree's size, which is 3.
     * 
     * Follow up:
     * Can you figure out ways to solve it with O(n) time complexity?
	 */
    private static class Result {
        int res;
        int min;
        int max;
        boolean valid;
        public Result(int res, int min, int max, boolean valid) {
            this.res = res;
            this.min = min;
            this.max = max;
            this.valid = valid;
        }
    }
    
    public static int largestBSTSubtree(TreeNode root) {
        Result res = BSTSubstree(root);
        //return Math.abs(res.res);
        return res.res;
    }
    
    private static Result BSTSubstree(TreeNode root) {
    	if (root == null) {
    		return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
    	}
    	
    	Result left = BSTSubstree(root.left);
        Result right = BSTSubstree(root.right);
        
        if (!left.valid || !right.valid || root.val <= left.max || root.val >= right.min) {
        	//System.out.println("111111 root = " + root.val);
        	Result res =  new Result(Math.max(left.res, right.res), 0, 0, false);
        	//System.out.println(res.res + "->" + res.min + "->" + res.max + "->" + res.valid);
        	//System.out.println();
        	return res;
        } else {
        	//System.out.println("222222 root = " + root.val);
        	Result res = new Result(left.res + right.res + 1, Math.min(root.val, left.min), Math.max(root.val, right.max), true);
        	//System.out.println(res.res + "->" + res.min + "->" + res.max + "->" + res.valid);
        	//System.out.println();
        	return res;
        }
    }
    
    public static void main(String[] args) {
    	/*
 		TreeNode node1 = new TreeNode(10);
 		TreeNode node2 = new TreeNode(5);
 		TreeNode node3 = new TreeNode(15);
 		TreeNode node4 = new TreeNode(1);
 		TreeNode node5 = new TreeNode(8);
 		TreeNode node6 = new TreeNode(7);
 		
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.right = node6;
 		*/
    	
    	TreeNode node1 = new TreeNode(4);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(7);
 		TreeNode node4 = new TreeNode(2);
 		TreeNode node5 = new TreeNode(3);
 		TreeNode node6 = new TreeNode(5);
 		TreeNode node7 = new TreeNode(2);
 		TreeNode node8 = new TreeNode(1);
 		
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.left  = node6;
 		node4.left  = node7;
 		node7.left  = node8;
 		
 		
 		System.out.println(largestBSTSubtree(node1));
 	}
}
