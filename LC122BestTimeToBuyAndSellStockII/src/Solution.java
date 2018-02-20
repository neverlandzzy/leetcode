
public class Solution {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete as many transactions 
	 * as you like (ie, buy one and sell one share of the stock multiple times). 
	 * 
	 * However, you may not engage in multiple transactions at the same time (ie, you must sell the 
	 * stock before you buy again).
	 */
	
    public static int maxProfit(int[] prices) {
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        
        return profit;
    }
    
    public static void main(String[] args) {
		int[] prices = {10,11,14,23,32,27,18,16,10,15,7,9};
		int[] prices2 = {};
		System.out.println(maxProfit(prices));
		System.out.println(maxProfit(prices2));
	}
}
