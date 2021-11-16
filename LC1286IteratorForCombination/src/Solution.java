public class Solution {

    public static void main(String[] args) {
        CombinationIterator combinationIterator = new CombinationIterator("abc", 2);

        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
        System.out.println(combinationIterator.hasNext());
    }
}
