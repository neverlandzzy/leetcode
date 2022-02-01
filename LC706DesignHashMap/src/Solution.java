public class Solution {

    class ListNode {
        int key;
        int value;
        MyHashMap.ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(2, 3);
        hashMap.put(1, 10);
        System.out.println(hashMap.get(2));
        System.out.println(hashMap.get(1));

        hashMap.put(1, 9);
        System.out.println(hashMap.get(1));

        hashMap.remove(1);
        System.out.println(hashMap.get(1));
    }
}
