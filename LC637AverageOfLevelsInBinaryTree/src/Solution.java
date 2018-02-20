import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/*
	 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
	 * Example 1:
	 * Input:
	 *     3
	 *    / \
	 *   9  20
	 *     /  \
	 *    15   7
	 * Output: [3, 14.5, 11]
	 * 
	 * Explanation:
	 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
	 * 
	 * Note:
	 * The range of node's value is in the range of 32-bit signed integer.
	 */
	
	// FB tag，掌握BFS, DFS
	
	// Solution 1: BFS O(n)
	/*
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root == null) {
        	return result;
        }
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	double sum = 0;
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		
        		if (node.left != null) {
        			queue.offer(node.left);
        		}
        		if (node.right != null) {
        			queue.offer(node.right);
        		}
        		
        		sum += node.val;
        	}
        	
        	result.add(sum / size);
        }
        
        return result;
    }
    */
	
	// Solution 2: DFS: O(n)
	public static List<Double> averageOfLevels(TreeNode root) {
		List<Double> sum = new ArrayList<>();   // 每层的和
		List<Integer> count = new ArrayList<>(); // 每层node的个数
		
		helper(root, 0, sum, count);
		
		for (int i = 0; i < sum.size(); i++) {
			sum.set(i, sum.get(i) / count.get(i));
		}
		
		return sum;
	}
	
	
	private static void helper(TreeNode root, int i, List<Double> sum, List<Integer> count) {
		if (root == null) {
			return;
		}
		
		if (i < sum.size()) {
			sum.set(i, sum.get(i) + root.val);
			count.set(i, count.get(i) + 1);
		} else {
			sum.add(root.val * 1.0);
			count.add(1);
		}
		
		helper(root.left,  i + 1, sum, count);
		helper(root.right, i + 1, sum, count);
	}
	
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(3);
    	TreeNode node2 = new TreeNode(9);
    	TreeNode node3 = new TreeNode(20);
    	TreeNode node4 = new TreeNode(15);
    	TreeNode node5 = new TreeNode(7);
    	
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node3.left  = node4;
    	node3.right = node5; 
    	
    	System.out.println(averageOfLevels(node1));
	}
}
