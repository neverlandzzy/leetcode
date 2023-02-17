public class Solution {

    /**
     * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
     *
     * Example 1:
     *
     *
     * Input: root = [4,2,6,1,3]
     * Output: 1
     * Example 2:
     *
     *
     * Input: root = [1,0,48,null,null,12,49]
     * Output: 1
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 104].
     * 0 <= Node.val <= 105
     *
     *
     * Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
     */


    static TreeNode preNode = null;
    static int minDiff = Integer.MAX_VALUE;

    public static int getMinimumDifference(TreeNode root) {
        inorderTraversal(root);
        return minDiff;
    }

    private static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);

        if (preNode != null) {
            minDiff = Math.min(minDiff, root.val - preNode.val);
        }

        preNode = root;
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);

        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;

        System.out.println(getMinimumDifference(node4));
    }
}
