
public class Solution {
	/*
	 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes 
	 * that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  
	 * It's not necessarily the case that the tree contains a node with value V.
	 * 
	 * Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, 
	 * if they are both in the same subtree after the split, then node C should still have the parent P.
	 * 
	 * You should output the root TreeNode of both subtrees after splitting, in any order.
	 * 
	 * Example 1:
	 * 
	 * Input: root = [4,2,6,1,3,5,7], V = 2
	 * Output: [[2,1],[4,3,6,null,null,5,7]]
	 * Explanation:
	 * Note that root, output[0], and output[1] are TreeNode objects, not arrays.
	 * 
	 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:
	 *           4
	 *         /   \
	 *       2      6
	 *      / \    / \
	 *     1   3  5   7
	 * 
	 * while the diagrams for the outputs are:
	 *           4
	 *         /   \
	 *       3      6      and    2
	 *             / \           /
	 *            5   7         1
	 * Note:
	 * 	1. The size of the BST will not exceed 50.
	 * 	2. The BST is always valid and each node's value is different.
	 */
	
    public static TreeNode[] splitBST(TreeNode root, int V) {
    	if (root == null) {
    		return new TreeNode[] {null, null};
    	}
    	
    	if (root.val <= V) {
    		TreeNode[] result = splitBST(root.right, V);
    		root.right = result[0];
    		result[0] = root;
    		return result;
    	} else {
    		TreeNode[] result = splitBST(root.left, V);
    		root.left = result[1];
    		result[1] = root;
    		return result;
    	}
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		node4.left  = node2;
		node4.right = node6;
		node2.left  = node1;
		node2.right = node3;
		node6.left  = node5;
		node6.right = node7;
		
		TreeNode[] result = splitBST(node4, 2);
		
		for (TreeNode t: result) {
			System.out.println(t.val);
		}
	}
}
