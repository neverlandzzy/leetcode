import java.util.ArrayList;
import java.util.List;

// Similar to LC146
public class MyLinkedList {
    class ListNode {
        ListNode next;
        ListNode prev;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        this.size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    // O(min(index, n - index))
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode node;

        // if index < size / 2: 从head找
        // if index > size / 2: 从tail找
        if (index  < size - index) {
            node = head;
            // node 从head开始，head是第一个node前的node，相当于index = -1, 所以这里i < index + 1
            for (int i = 0; i < index + 1; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = 0; i < size - index; i++) {
                node = node.prev;
            }
        }

        return node.val;
    }

    // O(1)
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode succ = head.next;

        newNode.prev = head;
        newNode.next = succ;
        head.next = newNode;
        succ.prev = newNode;
        size++;
    }

    // O(1)
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode pred = tail.prev;

        newNode.prev = pred;
        newNode.next = tail;
        tail.prev = newNode;
        pred.next = newNode;
        size++;
    }

    // O(min(index, n - index))
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        ListNode pred, succ;

        if (index  < size - index) {
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index; i++) {
                succ = succ.prev;
            }
            pred = succ.prev;
        }

        ListNode newNode = new ListNode(val);
        newNode.prev = pred;
        newNode.next = succ;
        pred.next = newNode;
        succ.prev = newNode;
        size++;
    }

    // O(min(index, n - index))
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        ListNode pred, succ;
        if (index  < size - index) {
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; i++) {
                succ = succ.prev;
            }
            pred = succ.prev.prev;
        }

        pred.next = succ;
        succ.prev = pred;
        size--;
    }
}
