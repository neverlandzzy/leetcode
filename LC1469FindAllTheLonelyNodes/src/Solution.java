import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * In a binary tree, a lonely node is a node that is the only child of its parent node. The root of the tree is not lonely because it does not
     * have a parent node.
     *
     * Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. Return the list in any order.
     *
     * Example 1:
     *
     * Input: root = [1,2,3,null,4]
     * Output: [4]
     * Explanation: Light blue node is the only lonely node.
     * Node 1 is the root and is not lonely.
     * Nodes 2 and 3 have the same parent and are not lonely.
     *
     * Example 2:
     *
     * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
     * Output: [6,2]
     * Explanation: Light blue nodes are lonely nodes.
     * Please remember that order doesn't matter, [2,6] is also an acceptable answer.
     *
     * Example 3:
     *
     * Input: root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
     * Output: [77,55,33,66,44,22]
     * Explanation: Nodes 99 and 88 share the same parent. Node 11 is the root.
     * All other nodes are lonely.
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * 1 <= Node.val <= 10^6
     */

    public static List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);

        return result;
    }

    private static void helper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left == null || root.right == null) {
            result.add(root.left == null ? root.right.val : root.left.val);
        }

        helper(root.left, result);
        helper(root.right, result);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node7.left = node1;
        node7.right = node4;
        node1.left = node6;
        node4.left = node5;
        node4.right = node3;
        node3.right = node2;

        System.out.println(getLonelyNodes(node7));
    }
}
