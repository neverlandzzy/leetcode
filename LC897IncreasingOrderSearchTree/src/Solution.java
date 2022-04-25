import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the
     * root of the tree, and every node has no left child and only one right child.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     * Example 2:
     *
     *
     * Input: root = [5,1,7]
     * Output: [1,null,5,null,7]
     *
     *
     * Constraints:
     *
     * The number of nodes in the given tree will be in the range [1, 100].
     * 0 <= Node.val <= 1000
     */

    // Solution 1: Time: O(n), Space: O(n)
//    public TreeNode increasingBST(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//
//        List<TreeNode> list = new ArrayList<>();
//
//        inOrderTraverse(root, list);
//
//        TreeNode newRoot = list.get(0);
//        TreeNode cur = newRoot;
//
//        for (int i = 1; i < list.size(); i++) {
//            cur.right = list.get(i);
//            list.get(i).left = null;
//            cur = cur.right;
//        }
//
//        return newRoot;
//    }
//
//    private void inOrderTraverse(TreeNode node, List<TreeNode> list) {
//        if (node == null) {
//            return;
//        }
//        inOrderTraverse(node.left, list);
//        list.add(node);
//        inOrderTraverse(node.right, list);
//    }

    // Solution 2: Time O(n), Space: O(h)

    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        cur = dummy;
        inOrderTraverse(root);
        return dummy.right;
    }

    private void inOrderTraverse(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inOrderTraverse(node.right);
    }
}
