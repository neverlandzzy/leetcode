public class Solution {

    public static void main(String[] args) {
        int[] test = {2, 3, 5};
        FirstUnique firstUnique = new FirstUnique(test);

        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(5);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(2);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(3);
        System.out.println(firstUnique.showFirstUnique());
    }
}
