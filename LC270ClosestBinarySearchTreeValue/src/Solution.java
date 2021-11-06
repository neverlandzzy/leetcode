
public class Solution {
	/**
	 * Given a non-empty binary search tree and a target value, find the value in the BST that 
	 * is closest to the target.
	 * 
	 * Note:
	 * Given target value is a floating point.
	 * You are guaranteed to have only one unique value in the BST that is closest to the target.
	 */

	// Solution 1:
	public static int closestValue(TreeNode root, double target) {

		int result = 0;
		double minDiff = Double.MAX_VALUE;

		while (root != null) {

			if (root.val == target) {
				return root.val;
			}

			double diff = Math.abs(root.val - target);
			if (minDiff > diff) {
				minDiff = diff;
				result = root.val;
			}

			if (root.val > target) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return result;
	}

	// Solution 2:
	/*
    public static int closestValue(TreeNode root, double target) {
        int less = root.val;
        int more = root.val;
        
        TreeNode node = root;
                
        while (node != null) {
        	if (node.val >= target) {
        		more = node.val;
        		node = node.left;
        	} else {
        		less = node.val;
        		node = node.right;
        	}
        }
        
        return (Math.abs(target - less) > Math.abs(target - more)) ? more : less;
    }

	*/
    
    
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(9);
 		TreeNode node2 = new TreeNode(3);
 		TreeNode node3 = new TreeNode(20);
 		TreeNode node4 = new TreeNode(7);
 		TreeNode node5 = new TreeNode(15);

 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.right  = node4;
 		node3.left = node5;
 		
 		System.out.println(closestValue(node1, 14.3));
 	}
}
