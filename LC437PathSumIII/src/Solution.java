import java.util.HashMap;


public class Solution {
	/*
	 * You are given a binary tree in which each node contains an integer value.
	 * 
	 * Find the number of paths that sum to a given value.
	 * 
	 * The path does not need to start or end at the root or a leaf, but it must go downwards 
	 * (traveling only from parent nodes to child nodes).
	 * 
	 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 * 
	 * Example:
	 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
	 * 
     * 	       10
     *	      /  \
     *       5   -3
     *      / \    \
     *     3   2   11
     *    / \   \
     *   3  -2   1
     *   
     *   Return 3. The paths that sum to 8 are:
     *   1.  5 -> 3
     *   2.  5 -> 2 -> 1
     *   3. -3 -> 11
	 */
	
	// https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method
    public static int pathSum(TreeNode root, int sum) {
    	HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
    	preSum.put(0, 1); // 对于从顶点root开始的path，起始的和为0
    	return helper(root, 0, sum, preSum);
    }
    
    private static int helper(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum) {
    	//System.out.println(preSum);
    	//System.out.println(curSum);
    	if (root == null) {
    		return 0;
    	}

    	curSum += root.val;
    	
    	int res = 0;
    	
    	// 对于cur之前任一点A到cur点的和 = root到cur点的和(curSum) - root到A的和 (preSum)
    	// 因此，若 curSum - target = preSum 则说明从A到cur点的和为target。
    	// e.g path   = 1, 2, -1, -1, 2  target = 2;
    	//     preSum = 0, 1, 3, 2, 1, 3  因此要 preSum.put(0, 1);
    	// 对于 {2，-1， -1， 2} 来说 preSum = 1, curSum = 3, 因此 curSum - preSum = 2 是一个解
    	if (preSum.containsKey(curSum - target)) {
    		res = preSum.get(curSum - target);
    	}
    	
    	if (preSum.containsKey(curSum)) {
    		preSum.put(curSum, preSum.get(curSum) + 1);
    	} else {
    		preSum.put(curSum, 1);
    	}
    	
    	res += helper(root.left, curSum, target, preSum) + helper(root.right, curSum, target, preSum);
    	preSum.put(curSum, preSum.get(curSum) - 1);
    	
    	return res;
    }
    
    public static void main(String[] args) {
    	/*
 		TreeNode node1 = new TreeNode(10);
 		TreeNode node2 = new TreeNode(5);
 		TreeNode node3 = new TreeNode(-3);
 		TreeNode node4 = new TreeNode(3);
 		TreeNode node5 = new TreeNode(2);
 		TreeNode node6 = new TreeNode(11);
 		TreeNode node7 = new TreeNode(3);
 		TreeNode node8 = new TreeNode(-2);
 		TreeNode node9 = new TreeNode(1);
 		
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node3.right = node6;
 		node4.left  = node7;
 		node4.right = node8;
 		node5.right = node9;
 		*/
    	
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(-1);
 		TreeNode node4 = new TreeNode(-1);
 		TreeNode node5 = new TreeNode(2);
 		
 		node1.right = node2;
 		node2.right = node3;
 		node3.right = node4;
 		node4.right = node5;
 		
 		System.out.println(pathSum(node1, 2));
 	}
}
