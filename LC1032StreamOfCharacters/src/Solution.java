public class Solution {

    public static void main(String[] args) {

        // https://leetcode.com/problems/stream-of-characters/discuss/713356/Explaining-by-examples-what-constitutes-an-expected-solution-for-future-readers

        String[] test = {"baa","aa","aaaa","abbbb","aba"};
        StreamChecker streamChecker = new StreamChecker(test);

        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
    }
}
