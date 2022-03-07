import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
     *
     * Train tickets are sold in three different ways:
     *
     * a 1-day pass is sold for costs[0] dollars,
     * a 7-day pass is sold for costs[1] dollars, and
     * a 30-day pass is sold for costs[2] dollars.
     * The passes allow that many days of consecutive travel.
     *
     * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
     * Return the minimum number of dollars you need to travel every day in the given list of days.
     *
     *
     *
     * Example 1:
     *
     * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
     * Output: 11
     * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
     * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
     * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
     * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
     * In total, you spent $11 and covered all the days of your travel.
     *
     * Example 2:
     *
     * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
     * Output: 17
     * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
     * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
     * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
     * In total, you spent $17 and covered all the days of your travel.
     *
     *
     * Constraints:
     *
     * 1 <= days.length <= 365
     * 1 <= days[i] <= 365
     * days is in strictly increasing order.
     * costs.length == 3
     * 1 <= costs[i] <= 1000
     */
    /*
    // Solution 1: Track calendar days
    // Time: O(n), n is the maximum calendar day in travel plan. (max = 365)
    // https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226659/Two-DP-solutions-with-pictures
    public static int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        boolean[] isTravelDay = new boolean[lastDay + 1];

        for (int day: days) {
            isTravelDay[day] = true;
        }

        for (int i = 1; i <= lastDay; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = costs[0] + dp[i - 1];
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }

        return dp[lastDay];
    }
    */
    // Solution 2: Track travel days
    // Time: O(n), n is the number of unique days in your travel plan. (days.length)
    //  Track minimal cost for each travel day. We store {day, cost} for 7-and 30-day passes
    //  in the last7 and last30 queues. After a pass 'expires', we remove it from the queue.
    //  This way, our queues only contains travel days for the last 7 and 30 days, and the
    //  cheapest pass prices are in the front of the queues.
    public static int mincostTickets(int[] days, int[] costs) {
        Queue<int[]> last7Days = new LinkedList<>();
        Queue<int[]> last30Days = new LinkedList<>();
        int cost = 0;

        for (int day: days) {
            while (!last7Days.isEmpty() && last7Days.peek()[0] + 7 <= day) {
                last7Days.poll();
            }

            while (!last30Days.isEmpty() && last30Days.peek()[0] + 30 <= day) {
                last30Days.poll();
            }

            last7Days.offer(new int[]{day, cost + costs[1]});
            last30Days.offer(new int[]{day, cost + costs[2]});

            cost = Math.min(cost + costs[0], Math.min(last7Days.peek()[1], last30Days.peek()[1]));
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] days1 = {1,4,6,7,8,20};
        int[] days2 = {1,2,3,4,5,6,7,8,9,10,30,31};

        int[] costs1 = {2,7,15};
        int[] costs2 = {2,7,15};

        System.out.println(mincostTickets(days1, costs1));
        System.out.println(mincostTickets(days2, costs2));
    }
}
