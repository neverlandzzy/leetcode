import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    /**
     * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
     *
     * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with
     * overlapping time range.
     *
     * If you choose a job that ends at time X you will be able to start another job that starts at time X.
     *
     * Example 1:
     * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
     * Output: 120
     * Explanation: The subset chosen is the first and fourth job.
     * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
     *
     * Example 2:
     * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
     * Output: 150
     * Explanation: The subset chosen is the first, fourth and fifth job.
     * Profit obtained 150 = 20 + 70 + 60.
     *
     * Example 3:
     * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= startTime.length == endTime.length == profit.length <= 5 * 104
     * 1 <= startTime[i] < endTime[i] <= 109
     * 1 <= profit[i] <= 104
     */

    /**
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
     * https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/408957/DP%2BBinary-search-(Java)
     *
     * For each job [startTime, endTime, profit], sort by endTime
     * dp[i]: max profit from time 0 to endTime[i] --> dp[n - 1]: max profit at endTime[n - 1] (since we sorted job by endTime, this is the maxProfit)
     *
     * base:
     * dp[0] = jobs[0][2] --> profit is profit[0] for job end at endTime[0]
     *
     * At each endTime[i], we have 2 options:
     * 1. Don't do this job -> dp[i] = dp[i - 1];
     * 2. Do this job -> dp[i] = dp[previous max] + p[i]
     *    dp[previous max] is the max profit we can make before current job's startTime startTime[i] --> binary search the job list and
     *    find the last one which endTime <= startTime[i]
     */

    // Time: O(nlogn) (O(nlogn) for sorting, O(nlogn) for binary search)
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));

        int[] dp = new int[n];
        dp[0] = jobs[0][2];

        for (int i = 1; i < n; i++) {
            // Option 1: Don't do this job
            dp[i] = dp[i - 1];

            // Option 2: Do this job and find the last job which endTime <= startTime
            // if lastJob == -1, it means there is no job can be done before current job
            int lastJobIndex = maxBenefitFromPreviousJob(jobs, i);
            int previousProfit = lastJobIndex == -1 ? 0 : dp[lastJobIndex];

            dp[i] = Math.max(dp[i], previousProfit + jobs[i][2]);
        }

        return dp[n - 1];
    }

    // Find the last job which endTime <= startTime of current job (job[index][0])
    private static int maxBenefitFromPreviousJob(int[][] jobs, int index) {
        int start = 0;
        int end = index;

        while (start + 1 < end) {
            int mid = (start + end) / 2;

            if (jobs[mid][1] <= jobs[index][0]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (jobs[end][1] <= jobs[index][0]) {
            return end;
        } else if (jobs[start][1] <= jobs[index][0]) {
            return start;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] startTime1 = {1, 2, 3, 3};
        int[] endTime1 = {3, 4, 5, 6};
        int[] profit1 = {50, 10, 40, 70};

        int[] startTime2 = {1, 2, 3, 4, 6};
        int[] endTime2 = {3, 5, 10, 6, 9};
        int[] profit2 = {20, 20, 100, 70, 60};

        int[] startTime3 = {1, 1, 1};
        int[] endTime3 = {2, 3, 4};
        int[] profit3 = {5, 6, 4};

        System.out.println(jobScheduling(startTime1, endTime1, profit1));
        System.out.println(jobScheduling(startTime2, endTime2, profit2));
        System.out.println(jobScheduling(startTime3, endTime3, profit3));
    }
}
