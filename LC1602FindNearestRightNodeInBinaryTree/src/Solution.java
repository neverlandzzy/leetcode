import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u,
     * or return null if u is the rightmost node in its level.
     *
     * Example 1:
     *
     * Input: root = [1,2,3,null,4,5,6], u = 4
     * Output: 5
     * Explanation: The nearest node on the same level to the right of node 4 is node 5.
     *
     * Example 2:
     *
     * Input: root = [3,null,4,2], u = 2
     * Output: null
     * Explanation: There are no nodes to the right of 2.
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * All values in the tree are distinct.
     * u is a node in the binary tree rooted at root.
     */

    public static TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        if (root.val == u.val) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.val == u.val) {
                    flag = true;
                    continue;
                }

                if (flag) {
                    return node;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (flag) {
                return null;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;

        TreeNode result = findNearestRightNode(node1, node4);
        System.out.println(result.val);
    }
}
