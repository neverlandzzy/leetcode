public class Solution {

    /**
     * You are given a doubly linked list which in addition to the next and previous pointers,
     * it could have a child pointer, which may or may not point to a separate doubly linked list.
     * These child lists may have one or more children of their own, and so on, to produce a multilevel
     * data structure, as shown in the example below.
     *
     * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
     * You are given the head of the first level of the list.
     *
     * Example 1:
     *
     * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
     * Explanation:
     *
     * The multilevel linked list in the input is as follows:
     *
     * After flattening the multilevel linked list it becomes:
     *
     *
     * Example 2:
     *
     * Input: head = [1,2,null,3]
     * Output: [1,3,2]
     * Explanation:
     *
     * The input multilevel linked list is as follows:
     *
     *   1---2---NULL
     *   |
     *   3---NULL
     *
     *
     * Example 3:
     *
     * Input: head = []
     * Output: []
     *
     *
     * How multilevel linked list is represented in test case:
     *
     * We use the multilevel linked list from Example 1 above:
     *
     *  1---2---3---4---5---6--NULL
     *          |
     *          7---8---9---10--NULL
     *              |
     *              11--12--NULL
     * The serialization of each level is as follows:
     *
     * [1,2,3,4,5,6,null]
     * [7,8,9,10,null]
     * [11,12,null]
     * To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
     *
     * [1,2,3,4,5,6,null]
     * [null,null,7,8,9,10,null]
     * [null,11,12,null]
     * Merging the serialization of each level and removing trailing nulls we obtain:
     *
     * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     *
     *
     * Constraints:
     *
     * The number of Nodes will not exceed 1000.
     * 1 <= Node.val <= 105
     */

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;

        while (node != null) {
            if (node.child == null) {
                node = node.next;
            } else {
                Node child = node.child;
                while (child.next != null) {
                    child = child.next;
                }

                child.next = node.next;
                if (node.next != null) {
                    node.next.prev = child;
                }

                node.next = node.child;
                node.child.prev = node;
                node.child = null;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node1.next = node2;
        node2.prev = node1;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;

        node4.next = node5;
        node5.prev = node4;

        node5.next = node6;
        node6.prev = node5;

        node3.child = node7;

        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;

        node9.next = node10;
        node10.prev = node9;

        node8.child = node11;

        node11.next = node12;
        node12.prev = node11;

        node1 = flatten(node1);

        printNodes(node1);
    }


    private static void printNodes(Node node) {
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }

        System.out.println();
    }
}
