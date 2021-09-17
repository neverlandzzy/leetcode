public class Solution {

    /**
     * Given the head of a singly linked list, return the middle node of the linked list.
     *
     * If there are two middle nodes, return the second middle node.
     *
     * Example 1:
     * Input: head = [1,2,3,4,5]
     * Output: [3,4,5]
     * Explanation: The middle node of the list is node 3.
     *
     * Example 2:
     * Input: head = [1,2,3,4,5,6]
     * Output: [4,5,6]
     * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
     *
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 100].
     * 1 <= Node.val <= 100
     */

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        if (head == null) {
            return null;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode result1 = middleNode(n1);
        System.out.println(result1.val);

        ListNode n26 = new ListNode(6);
        ListNode n25 = new ListNode(5, n26);
        ListNode n24 = new ListNode(4, n25);
        ListNode n23 = new ListNode(3, n24);
        ListNode n22 = new ListNode(2, n23);
        ListNode n21 = new ListNode(1, n22);

        ListNode result2 = middleNode(n21);
        System.out.println(result2.val);
    }
}
