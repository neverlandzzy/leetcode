public class Solution {

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
