public class Solution {
    /**
     * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a
     * is an ancestor of b.
     *
     * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
     *
     * Example 1:
     *
     * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
     * Output: 7
     * Explanation: We have various ancestor-node differences, some of which are given below :
     * |8 - 3| = 5
     * |3 - 7| = 4
     * |8 - 1| = 7
     * |10 - 13| = 3
     * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
     *
     * Example 2:
     *
     *
     * Input: root = [1,null,2,null,0,3]
     * Output: 3
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 5000].
     * 0 <= Node.val <= 10^5
     */

    public static int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return helper(root, root.val, root.val);
    }

    private static int helper(TreeNode node, int max, int min) {
        if (node == null) {
            return max - min;
        }

        max = Math.max(max, node.val);
        min = Math.min(min, node.val);

        int left = helper(node.left, max, min);
        int right = helper(node.right, max, min);

        return Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node10 = new TreeNode(10);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);

        node8.left = node3;
        node8.right = node10;
        node3.left = node1;
        node3.right = node6;
        node6.left = node4;
        node6.right = node7;
        node10.right = node14;
        node14.left = node13;

        System.out.println(maxAncestorDiff(node8));
    }
}
