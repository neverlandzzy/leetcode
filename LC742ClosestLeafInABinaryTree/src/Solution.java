
public class Solution {
	/*
	 * Given a binary tree where every node has a unique value, and a target key k, find the closest leaf node to target k in the tree.
	 * 
	 * A node is called a leaf if it has no children.
	 * 
	 * In the following examples, the input tree is represented in flattened form row by row. 
	 * The actual root tree given will be a TreeNode object.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * root = [1, 3, 2], k = 1
	 * Diagram of binary tree:
	 *           1
	 *          / \
	 *         3   2

	 * Output: 2 (or 3)
	 * 
	 * Explanation: Either 2 or 3 is the closest leaf node to 1.
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * root = [1], k = 1
	 * Output: 1
	 * 
	 * Explanation: The closest leaf node is the root node itself.
	 * 
	 * Example 3:
	 * 
	 * Input:
	 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
	 * Diagram of binary tree:
	 *              1
	 *             / \
 	 *            2   3
	 *           /
	 *          4
	 *         /
	 *        5
	 *       /
	 *      6
	 *
	 * Output: 3
	 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest to the node with value 2.
	 * 
	 * Note:
	 * root represents a binary tree with at least 1 node and at most 1000 nodes.
	 * Every node has a unique node.val in range [1, 1000].
	 * There exists some node in the given binary tree for which node.val == k.
	 */
	

    public static int findClosestLeaf(TreeNode root, int k) {

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
 		node4.left = node5;
 		node5.left = node6;
 		
 		System.out.println(findClosestLeaf(node1, 2));
	}
}
