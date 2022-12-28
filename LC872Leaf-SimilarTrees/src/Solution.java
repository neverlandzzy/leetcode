import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
     *
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
     * Output: true
     *
     * Example 2:
     *
     *
     * Input: root1 = [1,2,3], root2 = [1,3,2]
     * Output: false
     *
     *
     * Constraints:
     *
     * The number of nodes in each tree will be in the range [1, 200].
     * Both of the given trees will have values in the range [0, 200].
     */

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        return list1.equals(list2);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        dfs(root.left, list);
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        dfs(root.right, list);
    }
}
