
public class Solution {
	/**
	 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
	 * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree 
	 * of itself.
	 * 
	 * Example 1:
	 * Given tree s:
	 * 
	 *      3
	 *     / \
	 *    4   5
	 *   / \
	 *  1   2
	 * Given tree t:
	 *    4 
	 *   / \
	 *  1   2
	 * Return true, because t has the same structure and node values with a subtree of s.
	 * 
	 * Example 2:
	 * Given tree s:
	 * 
	 *      3
	 *     / \
	 *    4   5
	 *   / \
	 *  1   2
	 *     /
	 *    0
	 * Given tree t:
	 *    4
	 *   / \
	 *  1   2
	 * Return false.
	 */
	
	// Solution 1: compare two trees while traverse
	// Time: O(m * n), both tree are traversed 1 time
	// Space: O(n) n: number of nodes in s
	/*
    public static boolean isSubtree(TreeNode s, TreeNode t) {
   		if (isSameTree(s, t)) {
    		return true;
    	}
    	
    	if (s == null) {
    		return false;
    	}
    	return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private static boolean isSameTree(TreeNode s, TreeNode t) {
    	if (s == null && t == null) {
    		return true;
    	}
    	
    	if (s == null || t == null) {
    		return false;
    	}
    	
    	if (s.val != t.val) {
    		return false;
    	}
    	
    	return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
    */
    
    // Solution 2: get preorder strings of both trees 
    
    public static boolean isSubtree(TreeNode s, TreeNode t) {
    	StringBuilder sb1 = new StringBuilder();
    	StringBuilder sb2 = new StringBuilder();
    	

    	traverse(s, sb1.append(","));
    	traverse(t, sb2.append(","));
    	//System.out.println(sb1.toString());
    	//System.out.println(sb2.toString());
    	// 下面的写法对于12, 2的test case会错，因为两个输出字符串分别是12,N,N和2,N,N，会被indexOf认为sb2包含于sb1中，因此在前面加上','，于是两个字符串变为,12,N,N, ,2,N,N
    	// traverse(s, sb1);
    	// traverse(t, sb2);

    	return sb1.toString().indexOf(sb2.toString()) >= 0;
    }
    
    private static void traverse(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("N,");
    		return;
    	}
    	
    	sb.append(root.val).append(",");
    	traverse(root.left, sb);
    	traverse(root.right, sb);
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(2);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		
		TreeNode node6 = new TreeNode(4);
		TreeNode node7 = new TreeNode(1);
		TreeNode node8 = new TreeNode(2);
		
		node6.left = node7;
		node6.right = node8;
		
		System.out.println(isSubtree(node1, node6));
		
		TreeNode node9 = new TreeNode(0);
		node5.left = node9;
		System.out.println(isSubtree(node1, node6));

		TreeNode node10 = new TreeNode(2);
		TreeNode node11 = new TreeNode(12);
		System.out.println(isSubtree(node10, node11));

	}
}
