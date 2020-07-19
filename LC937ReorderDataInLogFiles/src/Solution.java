import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /**
     * You have an array of logs.  Each log is a space delimited string of words.
     *
     * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
     *
     * Each word after the identifier will consist only of lowercase letters, or;
     * Each word after the identifier will consist only of digits.
     * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
     *
     * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
     *
     * Return the final order of the logs.
     *
     * Example 1:
     *
     * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
     * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
     *
     *
     * Constraints:
     *
     * 1. 0 <= logs.length <= 100
     * 2. 3 <= logs[i].length <= 100
     * 3. logs[i] is guaranteed to have an identifier, and a word after the identifier.
     */

    // Comparator:
    //当返回0的时候排序方式是 t1,t2
    //当返回1的时候排序方式是 t2,t1
    //当返回-1的时候排序方式是t1,t2
    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            String[] s1ToArray = s1.split(" ", 2);
            String[] s2ToArray = s2.split(" ", 2);

            String s1Identifier = s1ToArray[0];
            String s2Identifier = s2ToArray[0];
            String s1Log = s1ToArray[1];
            String s2Log = s2ToArray[1];

            if (Character.isDigit(s1Log.charAt(0))) {
                if (Character.isDigit(s2Log.charAt(0))) {
                    return 0;
                }

                return 1;
            }

            if (Character.isDigit(s2Log.charAt(0))) {
                return -1;
            }

            int compare = s1Log.compareTo(s2Log);

            if (compare == 0) {
                compare = s1Identifier.compareTo(s2Identifier);
            }

            return compare;
        });

        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};

        print(reorderLogFiles(logs));
    }

    private static void print(String[] arr) {
        for (String str: arr) {
            System.out.print(str + " , ");
        }
        System.out.println();
    }
}
