import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed.
     * You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
     *
     * plantTime[i] is the number of full days it takes you to plant the ith seed. Every day, you can work on planting exactly one seed. You do not have to work on
     * planting the same seed on consecutive days, but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
     * growTime[i] is the number of full days it takes the ith seed to grow after being completely planted. After the last day of its growth, the flower blooms
     * and stays bloomed forever.
     * From the beginning of day 0, you can plant the seeds in any order.
     *
     * Return the earliest possible day where all seeds are blooming.
     *
     *
     * Example 1:
     *
     * Input: plantTime = [1,4,3], growTime = [2,3,1]
     * Output: 9
     * Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
     * One optimal way is:
     * On day 0, plant the 0th seed. The seed grows for 2 full days and blooms on day 3.
     * On days 1, 2, 3, and 4, plant the 1st seed. The seed grows for 3 full days and blooms on day 8.
     * On days 5, 6, and 7, plant the 2nd seed. The seed grows for 1 full day and blooms on day 9.
     * Thus, on day 9, all the seeds are blooming.
     *
     * Example 2:
     *
     * Input: plantTime = [1,2,3,2], growTime = [2,1,2,1]
     * Output: 9
     * Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
     * One optimal way is:
     * On day 1, plant the 0th seed. The seed grows for 2 full days and blooms on day 4.
     * On days 0 and 3, plant the 1st seed. The seed grows for 1 full day and blooms on day 5.
     * On days 2, 4, and 5, plant the 2nd seed. The seed grows for 2 full days and blooms on day 8.
     * On days 6 and 7, plant the 3rd seed. The seed grows for 1 full day and blooms on day 9.
     * Thus, on day 9, all the seeds are blooming.
     *
     * Example 3:
     *
     * Input: plantTime = [1], growTime = [1]
     * Output: 2
     * Explanation: On day 0, plant the 0th seed. The seed grows for 1 full day and blooms on day 2.
     * Thus, on day 2, all the seeds are blooming.
     *
     *
     * Constraints:
     *
     * n == plantTime.length == growTime.length
     * 1 <= n <= 10^5
     * 1 <= plantTime[i], growTime[i] <= 10^4
     */

    // Greedy: 按growTime倒序，一定是最优解
    // 证明：假设有i和j两个种子，growTime[i] ≤ growTime[j]，s是开始的时间，t是最优解
    //      1. 先种i，后种j：j开花的时间为：s + plantTime[i] + plantTime[j] + growTime[j] ≤ t
    //      2. 先种j，后种i：i开花的时间为：s + plantTime[j] + plantTime[i] + growTime[i] ≤ t
    //      如果1成立，即先种i，后种j可以达到最优解t, 那么由于growTime[i] ≤ growTime[j]，2 必然成立，
    //      也就是后种 growTime 小的种子，结果肯定不会比后种growTime大的种子更坏

    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            indices.add(i);
        }

        indices.sort(Comparator.comparingInt(i -> -growTime[i]));

        int result = 0;
        int currentPlantTime = 0;

        // 遍历plantTime。对于每一个种子，开花时间都是currentPlantTime + plantTime[i] + growTime[i];
        // 取n个开花时间中最大的那个
        for (int i = 0; i < n; i++) {
            int index = indices.get(i);
            int bloomTime = currentPlantTime + plantTime[index] + growTime[index];
            result = Math.max(bloomTime, result);
            currentPlantTime += plantTime[index];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] plantTime1 = {1, 4, 3};
        int[] growTime1 = {2, 3, 1};
        int[] plantTime2 = {1, 2, 3, 2};
        int[] growTime2 = {2, 1, 2, 1};
        int[] plantTime3 = {1};
        int[] growTime3 = {1};

        System.out.println(earliestFullBloom(plantTime1, growTime1));
        System.out.println(earliestFullBloom(plantTime2, growTime2));
        System.out.println(earliestFullBloom(plantTime3, growTime3));
    }
}
