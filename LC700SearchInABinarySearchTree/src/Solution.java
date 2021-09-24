public class Solution {
    /**
     * You are given the root of a binary search tree (BST) and an integer val.
     *
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
     * If such a node does not exist, return null.
     *
     * Example 1:
     *
     *
     * Input: root = [4,2,7,1,3], val = 2
     * Output: [2,1,3]
     *
     * Example 2:
     *
     *
     * Input: root = [4,2,7,1,3], val = 5
     * Output: []
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 5000].
     * 1 <= Node.val <= 107
     * root is a binary search tree.
     * 1 <= val <= 107
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            }

            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);

        node4.left = node2;
        node4.right = node7;
        node2.left = node1;
        node2.right = node3;

        TreeNode result1 = searchBST(node4, 2);
        TreeNode result2 = searchBST(node4, 5);

        System.out.println(result1.val);
        System.out.println(result2);
    }
}
