package onsite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZBinaryTreeToString {
	
	
	public static void main(String[] args) {
		
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(20);
		TreeNode node3 = new TreeNode(30);
		TreeNode node4 = new TreeNode(40);
		TreeNode node5 = new TreeNode(50);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		
		TreeNode root = node1;

		System.out.println("Solution 1: LC 297");
		System.out.println(binaryTreeToString1(root));
		System.out.println();
		
		
		System.out.println("Solution 2: preorder + inorder -- 无重复");
		System.out.println(binaryTreeToString2(root));
		System.out.println();
		
		
		System.out.println("Solution 3: array -- desen");
		int[] result1 = binaryTreeToString3(root);
		for (int n: result1) {
			System.out.print(n + ", ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Solution 4: array -- sparse");
		System.out.println(binaryTreeToString4(root));
		System.out.println();
		
	}
}
