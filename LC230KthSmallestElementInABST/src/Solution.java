import java.util.Stack;


public class Solution {
	/**
	 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
	 * 
	 * Note: 
	 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
	 * 
	 * 
	 * Follow up:
	 * What if the BST is modified (insert/delete operations) often and you need to find the 
	 * kth smallest frequently? How would you optimize the kthSmallest routine?
	 */
	
	// Solution 1: Recursion
	/*
    //int counter = 0;
    //int result = 0;
    
    public static int kthSmallest(TreeNode root, int k) {
        
        int[] counter = new int[1];
        int[] result = new int[1];
        helper(root, k, counter, result);
        return result[0];
    }
    
    private static void helper(TreeNode root, int k, int[] counter, int[] result) {
        if (root == null) {
            return;
        }
        
        helper(root.left, k, counter, result);
        counter[0]++;
        if (counter[0] == k) {
            result[0] = root.val;
            return;
        }
        helper(root.right, k, counter, result);
    }
    */
	
	// Solution 2: iteration
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
       
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
        	node = node.right;
        	while(node != null) {
            	stack.push(node);
            	node = node.left;
        	}
        }
        
        return -1;
    }
    
    // Follow up 1:
    /*
     * 如果BST节点TreeNode的属性可以扩展，则再添加一个属性leftCnt，记录左子树的节点个数
     * 
     * 记当前节点为node
     * 当node不为空时循环：
     * 若k == node.leftCnt + 1：则返回node
     * 否则，若k > node.leftCnt：则令k -= node.leftCnt + 1，令node = node.right 
     * 否则，node = node.left
     * 
     * http://algs4.cs.princeton.edu/32bst/BST.java.html
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63659/What-if-you-could-modify-the-BST-node's-structure
     * 
     * 上述算法时间复杂度为O(BST的高度)
     *
     *
     * Follow up 2:
     * What if the BST is modified (insert/delete operations) often and you need to find the
	 * kth smallest frequently? How would you optimize the kthSmallest routine?
	 *
	 * 用LRU的方法，用doubly linked list按inorder顺序存每个TreeNode，这样每次只要O(k)时间，就可以在linked list上
	 * 找到kth smallest element
     */

    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		
		node5.left = node2;
		node2.left = node1;
		node2.right = node4;
		node4.left = node3;
		node5.right = node7;
		node7.left = node6;
		node7.right = node8;
		
		System.out.println(kthSmallest(node5, 5));
	}
}
