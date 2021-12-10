public class Solution {
    /**
     * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1.
     * The linked list holds the binary representation of a number.
     *
     * Return the decimal value of the number in the linked list.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [1,0,1]
     * Output: 5
     * Explanation: (101) in base 2 = (5) in base 10
     *
     * Example 2:
     *
     * Input: head = [0]
     * Output: 0
     *
     * Example 3:
     *
     * Input: head = [1]
     * Output: 1
     *
     * Example 4:
     *
     * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * Output: 18880
     * Example 5:
     *
     * Input: head = [0,0]
     * Output: 0
     *
     *
     * Constraints:
     *
     * The Linked List is not empty.
     * Number of nodes will not exceed 30.
     * Each node's value is either 0 or 1.
     */

    public static int getDecimalValue(ListNode head) {
        ListNode node = head;
        int num = 0;

        while (node != null) {
            int val = node.val;
            num = num * 2 + val;
            node = node.next;
        }

        return num;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;

        System.out.println(getDecimalValue(node1));
    }
}
