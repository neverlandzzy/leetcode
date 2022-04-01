import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a binary number
     * starting with the most significant bit.
     *
     * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
     * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these
     * numbers.
     *
     * The test cases are generated so that the answer fits in a 32-bits integer.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,0,1,0,1,0,1]
     * Output: 22
     * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     * Example 2:
     *
     * Input: root = [0]
     * Output: 0
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * Node.val is 0 or 1.
     */

    // Solution 1:
    public static int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }

        val = val * 2 + root.val;

        if (root.left == null && root.right == null) {
            return val;
        }

        return dfs(root.left, val) + dfs(root.right, val);
    }

    // Solution 2:
    /*
    public static int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<String> paths = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int result = 0;

        helper(root, paths, sb);

        for (String p: paths) {
            result += Integer.parseInt(p, 2);
        }

        return result;
    }

    private static void helper(TreeNode root, List<String> paths, StringBuilder sb) {
        sb.append(root.val);

        if (root.left == null && root.right == null) {
            paths.add(sb.toString());
            return;
        }

        if (root.left != null) {
            helper(root.left, paths, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (root.right != null) {
            helper(root.right, paths, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    */
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        System.out.println(sumRootToLeaf(node1));
    }
}
