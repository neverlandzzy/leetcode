import java.util.Arrays;

public class Solution {
    /**
     * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j
     * where 0 <= j < i).
     *
     * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days.
     * The difficulty of a day is the maximum difficulty of a job done on that day.
     *
     * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
     *
     * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
     * Output: 7
     * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
     * Second day you can finish the last job, total difficulty = 1.
     * The difficulty of the schedule = 6 + 1 = 7
     *
     * Example 2:
     *
     * Input: jobDifficulty = [9,9,9], d = 4
     * Output: -1
     * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
     *
     * Example 3:
     *
     * Input: jobDifficulty = [1,1,1], d = 3
     * Output: 3
     * Explanation: The schedule is one job per day. total difficulty will be 3.
     *
     *
     * Constraints:
     *
     * 1 <= jobDifficulty.length <= 300
     * 0 <= jobDifficulty[i] <= 1000
     * 1 <= d <= 10
     */

    // Solution 1: DFS + mem
    // https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/solutions/1070611/java-dfs-with-memoization-detailed-step-by-step-explanation/
    // Base:  当只有一天剩余时，max = jobDifficulty中的最大值
    // cache: cache[day][pos] 记录已经计算过的还有day天，在pos时difficulty的最大值
    // Time: O(n^2*d)
    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) {
            return -1;
        }

        int[][] cache = new int[n][d + 1];

        for (int[] c: cache) {
            Arrays.fill(c, -1);
        }

        return dfs(jobDifficulty, d, 0, cache);
    }

    private static int dfs(int[] jobDifficulty, int d, int pos, int[][] cache) {
        if (cache[pos][d] != -1) {
            return cache[pos][d];
        }

        int n = jobDifficulty.length;

        if (d == 1) {
            // Base:  当只有一天剩余时，max = jobDifficulty中的最大值
            int max = 0;

            for (int i = pos; i < n; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }

            return max;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = pos; i < n - d + 1; i++) {
            max = Math.max(max, jobDifficulty[i]);
            min = Math.min(min, max + dfs(jobDifficulty, d - 1, i + 1, cache));
        }

        cache[pos][d] = min;
        return min;
    }


    public static void main(String[] args) {
        int[] test1 = {6, 5, 4, 3, 2, 1};
        int[] test2 = {9, 9, 9};
        int[] test3 = {1, 1, 1};

        System.out.println(minDifficulty(test1, 2));
        System.out.println(minDifficulty(test2, 4));
        System.out.println(minDifficulty(test3, 3));
    }
}
