package onsite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZBinaryTreePaths {
	
	// Solution 1: DFS
	public static List<String> binaryTreePaths(TreeNode root) {

	}
	
	private static void dfs(List<String> result, String path, TreeNode root) {

	}
	
	// Solution 2: BFS
	public static List<String> binaryTreePaths2(TreeNode root) {

	}
	
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(6);
 		TreeNode node7 = new TreeNode(7);
 		
 		/*
 		 * tree:
 		 *  	    1
 		 *        /   \
 		 *   	 /     \
 		 *  	2       3
 		 *	   / \     /
 		 *	  4   5   7
 		 *         \
 		 *          6
 		 */
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node5.right = node6;
 		node3.left  = node7;
 		
 		System.out.println(binaryTreePaths(node1));
 		System.out.println(binaryTreePaths2(node1));
 	}
}
