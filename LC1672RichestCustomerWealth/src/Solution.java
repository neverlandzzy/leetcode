public class Solution {

    /**
     * You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the ith customer has in the jth.
     * Return the wealth that the richest customer has.
     *
     * A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that
     * has the maximum wealth.
     *
     * Example 1:
     *
     * Input: accounts = [[1,2,3],[3,2,1]]
     * Output: 6
     * Explanation:
     * 1st customer has wealth = 1 + 2 + 3 = 6
     * 2nd customer has wealth = 3 + 2 + 1 = 6
     * Both customers are considered the richest with a wealth of 6 each, so return 6.
     *
     * Example 2:
     *
     * Input: accounts = [[1,5],[7,3],[3,5]]
     * Output: 10
     * Explanation:
     * 1st customer has wealth = 6
     * 2nd customer has wealth = 10
     * 3rd customer has wealth = 8
     * The 2nd customer is the richest with a wealth of 10.
     *
     * Example 3:
     *
     * Input: accounts = [[2,8,7],[7,1,3],[1,9,5]]
     * Output: 17
     *
     *
     * Constraints:
     *
     * m == accounts.length
     * n == accounts[i].length
     * 1 <= m, n <= 50
     * 1 <= accounts[i][j] <= 100
     */

    public static int maximumWealth(int[][] accounts) {
        if (accounts == null || accounts.length == 0 || accounts[0] == null || accounts[0].length == 0) {
            return 0;
        }

        int result = 0;

        for (int[] account: accounts) {
            int wealth = 0;

            for (int money: account) {
                wealth += money;
            }

            result = Math.max(result, wealth);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2, 3}, {3, 2, 1}};
        int[][] test2 = {{1, 5}, {7, 3}, {3, 5}};
        int[][] test3 = {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};

        System.out.println(maximumWealth(test1));
        System.out.println(maximumWealth(test2));
        System.out.println(maximumWealth(test3));
    }
}