import java.util.Arrays;

public class Solution {
    /**
     * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
     *
     * You have to form a team of 3 soldiers amongst them under the following rules:
     *
     * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
     * A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
     * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
     *
     * Example 1:
     *
     * Input: rating = [2,5,3,4,1]
     * Output: 3
     * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
     *
     * Example 2:
     *
     * Input: rating = [2,1,3]
     * Output: 0
     * Explanation: We can't form any team given the conditions.
     *
     * Example 3:
     *
     * Input: rating = [1,2,3,4]
     * Output: 4
     *
     *
     * Constraints:
     *
     * n == rating.length
     * 1 <= n <= 200
     * 1 <= rating[i] <= 10^5
     */

    public static int numTeams(int[] rating) {
        if (rating == null  || rating.length == 0) {
            return 0;
        }
        int result = 0;

        for (int i = 0; i < rating.length; i++) {
            int[] greater = new int[2];
            int[] less    = new int[2];
            for (int j = 0; j < rating.length; j++) {
                if (rating[j] > rating[i]) {
                    greater[j > i ? 1 : 0]++;
                }

                if (rating[j] < rating[i]) {
                    less[j > i ? 1 : 0 ]++;
                }
            }
            result += greater[0] * less[1] + greater[1] * less[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 5, 3, 4, 1};
        int[] test2 = {2, 1, 3};
        int[] test3 = {1, 2, 3, 4};

        System.out.println(numTeams(test1));
        System.out.println(numTeams(test2));
        System.out.println(numTeams(test3));
    }
}
