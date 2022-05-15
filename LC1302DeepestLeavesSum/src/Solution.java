import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * Given the root of a binary tree, return the sum of values of its deepest leaves.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
     * Output: 15
     * Example 2:
     *
     * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
     * Output: 19
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 1 <= Node.val <= 100
     */

    // Solution 1: BFS
    public static int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result = sum;
        }

        return result;
    }

    // Solution 2: DFS
//    public static int deepestLeavesSum(TreeNode root) {
//        int[] sum = new int[1];
//        int[] maxLevel = new int[1];
//
//        calculateSumAtLevel(root,0, sum, maxLevel);
//        return sum[0];
//    }
//
//    private static void calculateSumAtLevel(TreeNode root, int level, int[] sum, int[] maxLevel) {
//        if (root == null) {
//            return;
//        }
//
//        if (level > maxLevel[0]) {
//            sum[0] = 0;
//            maxLevel[0] = level;
//        }
//
//        if (level == maxLevel[0]) {
//            sum[0] += root.val;
//        }
//
//        calculateSumAtLevel(root.left, level + 1, sum, maxLevel);
//        calculateSumAtLevel(root.right, level + 1, sum, maxLevel);
//    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node7;
        node3.right = node6;
        node6.right = node8;

        TreeNode node9 = null;

        System.out.println(deepestLeavesSum(node1));
        System.out.println(deepestLeavesSum(node9));
    }
}
