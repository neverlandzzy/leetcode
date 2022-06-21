public class Solution {
    /**
     * Given two binary trees original and cloned and given a reference to a node target in the original tree.
     *
     * The cloned tree is a copy of the original tree.
     *
     * Return a reference to the same node in the cloned tree.
     *
     * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
     *
     * Example 1:
     *
     * Input: tree = [7,4,3,null,null,6,19], target = 3
     * Output: 3
     * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
     *
     * Example 2:
     *
     * Input: tree = [7], target =  7
     * Output: 7
     *
     * Example 3:
     *
     * Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
     * Output: 4
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * The values of the nodes of the tree are unique.
     * target node is a node from the original tree and is not null.
     *
     *
     * Follow up: Could you solve the problem if repeated values on the tree are allowed?
     */
    public static TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }

        if (original == target) {
            return cloned;
        }

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        TreeNode right = getTargetCopy(original.right, cloned.right, target);

        return left == null ? right : left;
    }

    public static void main(String[] args) {
        TreeNode node11 = new TreeNode(1);
        TreeNode node12 = new TreeNode(2);
        TreeNode node13 = new TreeNode(3);
        TreeNode node14 = new TreeNode(4);
        TreeNode node15 = new TreeNode(5);
        TreeNode node16 = new TreeNode(6);
        TreeNode node18 = new TreeNode(8);

        TreeNode node21 = new TreeNode(1);
        TreeNode node22 = new TreeNode(2);
        TreeNode node23 = new TreeNode(3);
        TreeNode node24 = new TreeNode(4);
        TreeNode node25 = new TreeNode(5);
        TreeNode node26 = new TreeNode(6);
        TreeNode node28 = new TreeNode(8);

        node18.right = node16;
        node16.right = node15;
        node15.right = node14;
        node14.right = node13;
        node13.right = node12;
        node12.right = node11;

        node28.right = node26;
        node26.right = node25;
        node25.right = node24;
        node24.right = node23;
        node23.right = node22;
        node22.right = node21;

        TreeNode result = getTargetCopy(node18, node28, node14);
        System.out.println(result.val);
    }

}
