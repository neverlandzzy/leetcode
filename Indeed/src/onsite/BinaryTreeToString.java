package onsite;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class BinaryTreeToString {
	
	// Store a binary tree to array
	
    // 方法1： LC297  -- 空节点占一个字符 2bytes
    public static String binaryTreeToString1(TreeNode root) {
        StringBuilder sb = new StringBuilder();    
        helper1(root, sb);
        return sb.toString();
    }
    
    private static void helper1(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("N").append(",");
    		return;
    	}
    	
    	sb.append(root.val).append(",");
    	helper1(root.left, sb);
    	helper1(root.right, sb);
    	
    }
    
    // 方法2：若无重复，可用preorder + inorder压缩 LC105
    private static List<List<Integer>> binaryTreeToString2(TreeNode root) {
    	List<Integer> preorder = new ArrayList<>();
    	List<Integer> inorder = new ArrayList<>();
    	preorderHelper(root, preorder);
    	inorderHelper(root, inorder);
    	
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(preorder);
    	result.add(inorder);
    	return result;
    }
    
    private static void preorderHelper(TreeNode root, List<Integer> result) {
    	if (root == null) {
    		return;
    	}
    	
    	result.add(root.val);
    	preorderHelper(root.left, result);
    	preorderHelper(root.right, result);	
    }
    
    private static void inorderHelper(TreeNode root, List<Integer> result) {
    	if (root == null) {
    		return;
    	}
    
    	inorderHelper(root.left, result);
    	result.add(root.val);
    	inorderHelper(root.right, result);	
    }
    
    
    // 方法3：
    // 用一个array 来存就行, Node i 的 两个孩子的index 是 2*i, 2 * i + 1. 如果 i 从１开始的话。这样子一个node 只寸值，也就是只用了4 byte空间，原来的1/3. 
    // 如果这个tree比较稀疏的话，可以把这个稀疏Array 转化成 dense format，也就是说用两个array, 一个存值，一个存 值对应的index. 
    // http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171444
    // 
    // 对于dense的tree，用一个array来存 -- 每个node 4byte
	public static int[] binaryTreeToString3(TreeNode root) {
		// 首先计算tree的高度height
		int height = getTreeHeight(root);
		
		// 然后需要2^height位的数组来存这个树
		int n = (int)Math.pow(2, height);
		int[] nodes = new int[n];
		
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		Queue<Integer> indexQueue = new LinkedList<>();
		nodeQueue.offer(root);
		indexQueue.offer(1);
		
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll();
			int index = indexQueue.poll();
			
			nodes[index] = node.val;
			
			if (node.left != null) {
				nodeQueue.offer(node.left);
				indexQueue.offer(index * 2);
			}
			
			if (node.right != null) {
				nodeQueue.offer(node.right);
				indexQueue.offer(index * 2 + 1);
			}
		}
		
		return nodes;
	}
	
	private static int getTreeHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int left = getTreeHeight(root.left);
		int right = getTreeHeight(root.right);
		
		return Math.max(left, right) + 1;
	}
	
	// 方法4：基于方法3 对于稀疏的tree，可以用两个array记录
	public static List<List<Integer>> binaryTreeToString4(TreeNode root) {
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		Queue<Integer> indexQueue = new LinkedList<>();
		nodeQueue.offer(root);
		indexQueue.offer(1);
		
		List<Integer> nodeList = new ArrayList<>();
		List<Integer> indexList = new ArrayList<>();
		
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll();
			int index = indexQueue.poll();
			
			nodeList.add(node.val);
			indexList.add(index);
			
			if (node.left != null) {
				nodeQueue.offer(node.left);
				indexQueue.offer(index * 2);
			}
			
			if (node.right != null) {
				nodeQueue.offer(node.right);
				indexQueue.offer(index * 2 + 1);
			}
		}
		
		List<List<Integer>> result = new ArrayList<>();
		result.add(nodeList);
		result.add(indexList);
		
		return result;
	}
		
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
