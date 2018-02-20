
public class Solution {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
	 * 
	 * Note:
	 * You may not engage in multiple transactions at the same time (ie, you must sell the
	 * stock before you buy again).
	 */
	
    public static int maxProfit(int[] prices) {
    	
    	if (prices == null || prices.length < 2) {
    		return 0;
    	}
    	
        int n = prices.length;
        int[] first = new int[n];   // first[i] 从前向后遍历，表示在i这个点或在其之前卖出，也就是i之前的最大收益
        int[] second = new int[n];  // second[i] 从后向前遍历，表示在i这个点或在其之后买入，也就是i之后的最大收益。
        
        int min = prices[0];		
        for (int i = 1; i < n; i++) {
        	min = Math.min(prices[i], min);
        	first[i] = Math.max(first[i-1], prices[i] - min);
        }
        
        int max = prices[n-1];
        for (int i = n-2; i >= 0; i--) {
        	max = Math.max(prices[i], max);
        	second[i] = Math.max(second[i+1], max - prices[i]);
        }
        
        for(int k: first) {
        	System.out.print(k + ", ");
        }
        System.out.println();
        for(int k: second) {
        	System.out.print(k + ", ");
        }
        System.out.println();
        int profit = 0;
        for (int i = 0; i < n; i++) {
        	profit = Math.max(profit, first[i] + second[i]);
        }
        
        return profit;
    }
    
    public static void main(String[] args) {
		int[] prices = {10,11,14,23,32,27,18,16,10,15,7,9};
		int[] prices2 = {1, 3, 8, 6};
		System.out.println(maxProfit(prices));
		System.out.println(maxProfit(prices2));
	}
}
