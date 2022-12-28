import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of
     * the subtrees is maximized.
     *
     * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Note that you need to maximize the answer before taking the mod and not after taking it.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,3,4,5,6]
     * Output: 110
     * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
     * 
     * Example 2:
     *
     *
     * Input: root = [1,null,2,3,4,null,null,5,6]
     * Output: 90
     * Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 5 * 10^4].
     * 1 <= Node.val <= 10^4
     */

    public static int maxProduct(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        long totalSum = dfs(root, list);
        long maxProduct = 0;
        int MOD = 1000000007;

        for (long sum: list) {
            maxProduct = Math.max(maxProduct, sum * (totalSum - sum));
        }

        return (int)(maxProduct % MOD);
    }

    private static int dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return 0;
        }

        int leftSum = dfs(root.left, list);
        int rightSum = dfs(root.right, list);
        int totalSum = leftSum + rightSum + root.val;
        list.add(totalSum);
        return totalSum;
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
        node2.left = node4;
        node4.right = node5;
        node3.left = node6;

        System.out.println(maxProduct(node1));
    }
}
