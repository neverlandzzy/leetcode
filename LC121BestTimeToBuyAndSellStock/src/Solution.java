
public class Solution {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (ie, buy one and sell one 
	 * share of the stock), design an algorithm to find the maximum profit.
	 */
	
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int profit = 0;
        int min = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(prices[i], min);
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
