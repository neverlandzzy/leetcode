
public class Solution {
	/*
	 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; 
	 * and a non-negative integer fee representing a transaction fee.
	 * 
	 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
	 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
	 * 
	 * Return the maximum profit you can make.
	 * 
	 * Example 1:
	 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
	 * Output: 8
	 * Explanation: The maximum profit can be achieved by:
	 * Buying at prices[0] = 1
	 * Selling at prices[3] = 8
	 * Buying at prices[4] = 4
	 * Selling at prices[5] = 9
	 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	 * 
	 * Note:
	 * 
	 * 0 < prices.length <= 50000.
	 * 0 < prices[i] < 50000.
	 * 0 <= fee < 50000.
	 */
	
	// Space: O(1) 
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        int buy = -prices[0];
        int sell = 0;

        
        for (int i = 1; i < n; i++) {
            int tmp = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, tmp + prices[i] - fee);
        }
            
        return sell;
    }
    
    // Space: O(n) 
    /*
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] hold = new int[n]; // hold[i]: on the i-th day, we have stock holding:
                                 // 1. we do nothing on the i-th day, so hold[i] = hold[i - 1];
        						 // 2. we buy stocks on the i-th day, so hold[i] = sell[i - 1] - prices[i];
        int[] sell = new int[n]; // sell[i]: on the i-th day, we don't have stock holding;
                                 // 1. we do nothing on the i-th day, so sell[i] = sell[i - 1];
                                 // 2. we sell stocks on the i-th day, so sell[i] = hold[i - 1] + prices[i] - fee
        
        hold[0] = -prices[0];
        sell[0] = 0;
        
        for (int i = 1; i < n; i++) {
        	hold[i] = Math.max(hold[i - 1], sell[i - 1] - prices[i]);
        	sell[i] = Math.max(sell[i - 1], hold[i - 1] + prices[i] -fee);
        }
        
        return sell[n - 1];
    }
    */
    public static void main(String[] args) {
		Solution solution = new Solution();
		int[] test = {1, 3, 2, 8, 4, 9};
		
		System.out.println(solution.maxProfit(test, 2));
	}
}
