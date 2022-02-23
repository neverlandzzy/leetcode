import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest
     * time slot that works for both of them and is of duration duration.
     *
     * If there is no common time slot that satisfies the requirements, return an empty array.
     *
     * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
     *
     * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time
     * slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
     *
     * Example 1:
     *
     * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
     * Output: [60,68]
     *
     * Example 2:
     *
     * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
     * Output: []
     *
     *
     * Constraints:
     *
     * 1 <= slots1.length, slots2.length <= 10^4
     * slots1[i].length, slots2[i].length == 2
     * slots1[i][0] < slots1[i][1]
     * slots2[i][0] < slots2[i][1]
     * 0 <= slots1[i][j], slots2[i][j] <= 10^9
     * 1 <= duration <= 10^6
     */

    // Time: O(MlogM+NlogN)
    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(s -> s[0]));
        Arrays.sort(slots2, Comparator.comparingInt(s -> s[0]));

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < slots1.length && j < slots2.length) {
            int s1 = slots1[i][0], e1 = slots1[i][1], s2 = slots2[j][0], e2 = slots2[j][1];
            if (s2 < e1 && e2 > s1) {
                int overlap = Math.min(e1, e2) - Math.max(s1, s2);

                if (overlap >= duration) {
                    int leftIntersect = Math.max(s1, s2);
                    result.add(leftIntersect);
                    result.add(leftIntersect + duration);
                    return result;
                }
            }

            if (e1 < e2) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] s1 = {{10, 50}, {60, 120}, {140, 210}};
        int[][] s2 = {{0, 15}, {60, 70}};

        System.out.println(minAvailableDuration(s1, s2, 8));
        System.out.println(minAvailableDuration(s1, s2, 12));
    }
}
