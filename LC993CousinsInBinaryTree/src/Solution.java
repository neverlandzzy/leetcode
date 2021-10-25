public class Solution {

    /**
     * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
     * return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
     *
     * Two nodes of a binary tree are cousins if they have the same depth with different parents.
     *
     * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
     *
     *
     *
     * Example 1:
     *
     * Input: root = [1,2,3,4], x = 4, y = 3
     * Output: false
     *
     * Example 2:
     *
     *
     * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
     * Output: true
     *
     * Example 3:
     *
     * Input: root = [1,2,3,null,4], x = 2, y = 3
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 100].
     * 1 <= Node.val <= 100
     * Each node has a unique value.
     * x != y
     * x and y are exist in the tree.
     */

    private static TreeNode xParent = null;
    private static TreeNode yParent = null;
    private static int xDepth = -1;
    private static int yDepth = -1;

    public static boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);

        return xDepth == yDepth && xParent != yParent;
    }

    private static void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent) {
        if (root == null) {
            return;
        }

        if (root.val == x) {
            xParent = parent;
            xDepth = depth;
        } else if (root.val == y) {
            yParent = parent;
            yDepth = depth;
        }

        getDepthAndParent(root.left, x, y, depth + 1, root);
        getDepthAndParent(root.right, x, y, depth + 1, root);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;

        System.out.println(isCousins(node1, 4, 3));
    }
}
