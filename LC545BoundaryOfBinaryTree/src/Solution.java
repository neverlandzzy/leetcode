import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
	 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, 
	 * leaves, and right boundary in order without duplicate nodes.
	 * 
	 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. 
	 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies 
	 * to the input binary tree, and not applies to any subtrees.
	 * 
	 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the 
	 * right subtree. Repeat until you reach a leaf node.
	 * 
	 * The right-most node is also defined by the same way with left and right exchanged.
	 * 
	 * Example 1
	 * Input:
	 *   1
	 *    \
	 *     2
	 *    / \
	 *   3   4
	 * 
	 * Ouput:
	 * [1, 3, 4, 2]
	 * 
	 * Explanation:
	 * 	The root doesn't have left subtree, so the root itself is left boundary.
	 * 	The leaves are node 3 and 4.
	 * 	The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
	 * 	So order them in anti-clockwise without duplicates and we have [1,3,4,2].
	 * 
	 * Example 2
	 * Input:
	 *     ____1_____
	 *    /          \
	 *   2            3
	 *  / \          / 
	 * 4   5        6   
	 *    / \      / \
	 *   7   8    9  10  
	 *        
	 * Ouput:
	 * [1,2,4,7,8,9,10,6,3]
	 * 
	 * Explanation:
	 * 	The left boundary are node 1,2,4. (4 is the left-most node according to definition)
	 * 	The leaves are node 4,7,8,9,10.
	 * 	The right boundary are node 1,3,6,10. (10 is the right-most node).
	 * 	So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
	 */
	
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	if (root == null) {
    		return result;
    	}
    	
    	List<Integer> left   = new ArrayList<>();
    	List<Integer> right  = new ArrayList<>();
    	List<Integer> leaf   = new ArrayList<>();
    	
    	Set<TreeNode> set  = new HashSet<>();
    
    	if (root.left != null) {
    		dfsLeft(left, set, root);
    	} else {
    		left.add(root.val);
    		set.add(root);
    	}
    	
    	dfsLeaf(leaf, set, root);
    	
    	if (root.right != null) {
    		dfsRight(right, set, root);
    	} else {
    		if (!set.contains(root)) {
        		right.add(root.val);
        		set.add(root);
    		}
    	}
    	
    	
    	//System.out.println(left);
    	//System.out.println(leaf);
    	//System.out.println(right);
    	
    	
    	for (int l: left) {
    		result.add(l);
    	}
    	
    	for (int l: leaf) {
    		result.add(l);
    	}
    	
    	for (int i = right.size() - 1; i >= 0; i--) {
    		result.add(right.get(i));
    	}
    	return result;
    }
    
    private static void dfsLeft(List<Integer> left, Set<TreeNode> set, TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
		left.add(root.val);
		set.add(root);
		
    	if (root.left == null && root.right == null) {
    		return;
    	}
    	
    	if (root.left != null) {
    		dfsLeft(left, set, root.left);
    	} else {
    		dfsLeft(left, set, root.right);
    	}
    }
    
    
    private static void dfsRight(List<Integer> right, Set<TreeNode> set, TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	if (!set.contains(root)) {
    		right.add(root.val);
    		set.add(root);
    	}
		
    	if (root.left == null && root.right == null) {
    		return;
    	}
    	
    	if (root.right != null) {
    		dfsRight(right, set, root.right);
    	} else {
    		dfsRight(right, set, root.left);
    	}
    }
    
    private static void dfsLeaf(List<Integer> leaf, Set<TreeNode> set, TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	if (root.left == null && root.right == null && !set.contains(root)) {  		
    		leaf.add(root.val);
    		set.add(root);
    		return;
    	}
    	
    	dfsLeaf(leaf, set, root.left);
    	dfsLeaf(leaf, set, root.right);
    }
    
    public static void main(String[] args) {
		TreeNode node11 = new TreeNode(1);
		TreeNode node12 = new TreeNode(2);
		TreeNode node13 = new TreeNode(3);
		TreeNode node14 = new TreeNode(4);
		
		/*
		node11.right = node12;
		node12.left  = node13;
		node12.right = node14;
		*/
		node13.left = node12;
		node12.right = node14;
		node14.left = node11;
		
		System.out.println(boundaryOfBinaryTree(node13));
		
		
		TreeNode node21 = new TreeNode(1);
		TreeNode node22 = new TreeNode(2);
		TreeNode node23 = new TreeNode(3);
		TreeNode node24 = new TreeNode(4);
		TreeNode node25 = new TreeNode(5);
		TreeNode node26 = new TreeNode(6);
		TreeNode node27 = new TreeNode(7);
		TreeNode node28 = new TreeNode(8);
		TreeNode node29 = new TreeNode(9);
		TreeNode node20 = new TreeNode(10);
		
		node21.left = node22;
		node21.right = node23;
		node22.left = node24;
		node22.right = node25;
		node23.left = node26;
		node25.left = node27;
		node25.right = node28;
		node26.left = node29;
		node26.right = node20;
		
		
		System.out.println(boundaryOfBinaryTree(node21));
	}
    
}
