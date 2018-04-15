

public class Solution {
	
	
    public static double largestTriangleArea(int[][] points) {
    	double result = 0;
    	
        for (int i = 0; i < points.length; i++) {
        	for (int j = i + 1; j < points.length; j++) {
        		for (int k = j + 1; k < points.length; k++) {
        			double area = area(points[i], points[j], points[k]);
        			if (!Double.isNaN(area)) {
        				result = Math.max(result, area(points[i], points[j], points[k]));
        			}
        		}
        	}
        }
        
        return result;
    }
    
    private static double area(int[] p1, int[] p2, int[] p3) {
    	double a = Math.sqrt(Math.pow(Math.abs(p1[0] - p2[0]), 2) + Math.pow(Math.abs(p1[1] - p2[1]), 2));
    	double b = Math.sqrt(Math.pow(Math.abs(p1[0] - p3[0]), 2) + Math.pow(Math.abs(p1[1] - p3[1]), 2));
    	double c = Math.sqrt(Math.pow(Math.abs(p3[0] - p2[0]), 2) + Math.pow(Math.abs(p3[1] - p2[1]), 2));
    	double s = (a + b + c) / 2;
    	double result = Math.sqrt(s * (s - a) * (s - b) * (s - c));

    	return result;
    }
    
	
    public static TreeNode pruneTree(TreeNode root) {
        helper(root);
        
        return root;
    }
    
    private static boolean helper(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	boolean left = helper(root.left);
    	boolean right = helper(root.right);
    	
    	if (left) {
    		root.left = null;
    	}
    	
    	if (right) {
    		root.right = null;
    	}
    	
    	return root.val == 0 && left && right;
    }
    
    
    /*
    public static double largestSumOfAverages(int[] A, int K) {
    	int[] max = new int[1];
    	
    }
    
    private static void helper(int[] A, int K, int pos, int ) {
    	
    }
    */
    
	public static void main(String[] args) {
		int[][] test11 = {{0,0},{0,1},{1,0},{0,2},{2,0}};
		int[][] test12 = {{35,-23},{-12,-48},{-34,-40},{21,-25},{-35,-44},{24,1},{16,-9},{41,4},{-36,-49},{42,-49},{-37,-20},{-35,11},{-2,-36},{18,21},{18,8},{-24,14},{-23,-11},{-8,44},{-19,-3},{0,-10},{-21,-4},{23,18},{20,11},{-42,24},{6,-19}};
		//System.out.println(largestTriangleArea(test11));
		System.out.println(largestTriangleArea(test12));
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(0);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(0);
		TreeNode node5 = new TreeNode(0);
		TreeNode node6 = new TreeNode(0);
		TreeNode node7 = new TreeNode(1);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		
		//TreeNode result = pruneTree(node1);
		
		int[] test31 = {9, 1, 2, 3, 9};
		//System.out.println(largestSumOfAverages(test31, 3));
	}
}
