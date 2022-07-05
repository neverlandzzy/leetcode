import java.util.Arrays;

public class Solution {
    /**
     * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
     *
     * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
     * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
     * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts.
     * Since the answer can be a large number, return this modulo 10^9 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
     * Output: 4
     * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green
     * piece of cake has the maximum area.
     *
     * Example 2:
     *
     * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
     * Output: 6
     * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake,
     * the green and yellow pieces of cake have the maximum area.
     *
     * Example 3:
     *
     * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
     * Output: 9
     *
     *
     * Constraints:
     *
     * 2 <= h, w <= 10^9
     * 1 <= horizontalCuts.length <= min(h - 1, 10^5)
     * 1 <= verticalCuts.length <= min(w - 1, 10^5)
     * 1 <= horizontalCuts[i] < h
     * 1 <= verticalCuts[i] < w
     * All the elements in horizontalCuts are distinct.
     * All the elements in verticalCuts are distinct.
     */

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int MOD = 1000000007;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        long maxH = horizontalCuts[0];
        long maxV = verticalCuts[0];

        for (int i = 1; i < horizontalCuts.length; i++) {
            maxH = Math.max(maxH, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        for (int i = 1; i < verticalCuts.length; i++) {
            maxV = Math.max(maxV, verticalCuts[i] - verticalCuts[i - 1]);
        }

        maxH = Math.max(maxH, h - horizontalCuts[horizontalCuts.length - 1]);
        maxV = Math.max(maxV, w - verticalCuts[verticalCuts.length - 1]);

        return (int)((maxH * maxV) % MOD);
    }

    public static void main(String[] args) {
        int[] h1 = {1, 2, 4};
        int[] v1 = {1, 3};
        int[] h2 = {3, 1};
        int[] v2 = {1};
        int[] h3 = {3};
        int[] v3 = {3};
        int[] h4 = {2};
        int[] v4 = {2};
        //System.out.println(maxArea(5, 4, h1, v1));
        //System.out.println(maxArea(5, 4, h2, v2));
        //System.out.println(maxArea(5, 4, h3, v3));
        System.out.println(maxArea(1000000000, 1000000000, h4, v4));
    }
}
