public class MyHashMap {

    /**
     * Design a HashMap without using any built-in hash table libraries.
     *
     * Implement the MyHashMap class:
     *
     * MyHashMap() initializes the object with an empty map.
     *
     * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map,
     * update the corresponding value.
     *
     * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping
     * for the key.
     *
     * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
     * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
     * Output
     * [null, null, null, 1, -1, null, 1, null, -1]
     *
     * Explanation
     * MyHashMap myHashMap = new MyHashMap();
     * myHashMap.put(1, 1); // The map is now [[1,1]]
     * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
     * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
     * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
     * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
     * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
     * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
     * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
     *
     *
     * Constraints:
     *
     * 0 <= key, value <= 10^6
     * At most 10^4 calls will be made to put, get, and remove.
     */
    /** Initialize your data structure here. */
    class ListNode {
        int key;
        int value;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    ListNode[] linkedList;

    public MyHashMap() {
        linkedList = new ListNode[10001];
    }

    private int getIndex(int key) {
        return key % linkedList.length;
    }

    private ListNode getNodeByIndexAndKey(int index, int key) {
        if (linkedList[index] == null) {
            return null;
        }
        ListNode node = linkedList[index];

        while (node != null && node.key != key) {
            node = node.next;
        }

        return node;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getIndex(key);
        ListNode node = getNodeByIndexAndKey(index, key);
        ListNode head = linkedList[index];

        if (head == null) {
            linkedList[index] = new ListNode(key, value);
        } else if (node == null) {
            ListNode newNode = new ListNode(key, value);
            newNode.next = head;
            linkedList[index] = newNode;
        } else {
            node.value = value;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        ListNode node = getNodeByIndexAndKey(index, key);

        return node == null ? -1 : node.value;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        ListNode node = getNodeByIndexAndKey(index, key);

        if (node != null) {
            node.value = -1;
        }
    }
}
