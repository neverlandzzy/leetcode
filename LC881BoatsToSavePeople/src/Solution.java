import java.util.Arrays;

public class Solution {
    /**
     * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats
     * where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time,
     * provided the sum of the weight of those people is at most limit.
     *
     * Return the minimum number of boats to carry every given person.
     *
     * Example 1:
     *
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 boat (1, 2)
     *
     * Example 2:
     *
     * Input: people = [3,2,2,1], limit = 3
     * Output: 3
     * Explanation: 3 boats (1, 2), (2) and (3)
     *
     * Example 3:
     *
     * Input: people = [3,5,3,4], limit = 5
     * Output: 4
     * Explanation: 4 boats (3), (3), (4), (5)
     *
     *
     * Constraints:
     *
     * 1 <= people.length <= 5 * 10^4
     * 1 <= people[i] <= limit <= 3 * 10^4
     */

    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;
        int counter = 0;

        while (start <= end) {
            if (people[end] + people[start] <= limit) {
                end--;
                start++;
            } else {
                end--;
            }

            counter++;
        }

        return counter;
    }

    public static void main(String[] args) {
        int[] people1 = {1, 2};
        int[] people2 = {3, 2, 2, 1};
        int[] people3 = {3, 5, 3, 4};

        System.out.println(numRescueBoats(people1, 3));
        System.out.println(numRescueBoats(people2, 3));
        System.out.println(numRescueBoats(people3, 5));
    }
}
