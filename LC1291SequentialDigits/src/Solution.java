import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
     *
     * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
     *
     *
     *
     * Example 1:
     *
     * Input: low = 100, high = 300
     * Output: [123,234]
     * Example 2:
     *
     * Input: low = 1000, high = 13000
     * Output: [1234,2345,3456,4567,5678,6789,12345]
     *
     *
     * Constraints:
     *
     * 10 <= low <= high <= 10^9
     */

    public static List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        int n = digits.length();
        List<Integer> result = new ArrayList<>();

        int minLength = String.valueOf(low).length();
        int maxLength = String.valueOf(high).length();

        for (int i = minLength; i <= maxLength; i++) {
            for (int start = 0; start <= n - i; start++) {
                int number = Integer.parseInt(digits.substring(start, start + i));
                if (number >= low && number <= high) {
                    result.add(number);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(sequentialDigits(100, 300));
        System.out.println(sequentialDigits(1000, 13000));
    }
}
