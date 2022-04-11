public class Solution {
    /**
     * You are given the head of a linked list, and an integer k.
     *
     * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from
     * the end (the list is 1-indexed).
     *
     *
     *
     * Example 1:
     *
     *
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [1,4,3,2,5]
     * Example 2:
     *
     * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
     * Output: [7,9,6,6,8,7,3,0,9,5]
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is n.
     * 1 <= k <= n <= 10^5
     * 0 <= Node.val <= 100
     */

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode lastKthNode = head;
        ListNode node = head;
        int i = 1;
        while (i < k) {
            i++;
            node = node.next;
        }

        ListNode kthNode = node;

        while (node.next != null) {
            lastKthNode = lastKthNode.next;
            node = node.next;
        }

        int tmp = kthNode.val;
        kthNode.val = lastKthNode.val;
        lastKthNode.val = tmp;

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result = swapNodes(node1, 2);

        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }
}
