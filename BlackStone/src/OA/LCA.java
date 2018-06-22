package OA;

import java.util.Scanner;

public class LCA {
	
	
	public static String lowestCommonAncestor(TreeNode root, int p, int q) {
		
        if (root == null) {
        	return null;
        }
        
        if (root.val < p && root.val < q) {
        	return lowestCommonAncestor(root.right, p, q);
        }
        
        if (root.val > p && root.val > q) {
        	return lowestCommonAncestor(root.left, p, q);
        }
        
        return String.valueOf(root.val);
	}
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String test = scanner.nextLine();
		scanner.close();
		
		System.out.println("input = " + test);
		String[] str = test.split(" ");
		int p = Integer.parseInt(str[0]);
		int q = Integer.parseInt(str[1]);
		
		TreeNode node1 = new TreeNode(30);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(52);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(20);
		TreeNode node6 = new TreeNode(10);
		TreeNode node7 = new TreeNode(29);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node5.left = node6;
		node5.right = node7;
		
		
		System.out.println(lowestCommonAncestor(node1, p, q));
	}
}
