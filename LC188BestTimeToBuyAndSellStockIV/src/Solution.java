
public class Solution {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
	 * 
	 * Note:
	 * You may not engage in multiple transactions at the same time 
	 * (ie, you must sell the stock before you buy again).
	 */
	
    public static int maxProfit(int k, int[] prices) {
       
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int len = prices.length;
        
        if (k > len / 2) {
            int maxProfit = 0;
            
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        // maxP[i][j] :
        // the max profit of i transactions in [0, j] (1 <= i <= k, 0 <= j <= N - 1).
        // 两种状态：在j没有sell:  d[i, j] = d[i][j - 1];
        //         在j有sell:   d[i, j] = max(d(i - 1, l - 1) - p[l] + p[j] ) (0 <= l < j)
        int[][] maxProfit = new int[k + 1][len]; 
        
        
        for (int i = 1; i <= k; i++) {
            int max = - prices[0];
            for (int j = 1; j < len; j++) {
                maxProfit[i][j] = Math.max(maxProfit[i][j - 1], max + prices[j]);
                max = Math.max(max, maxProfit[i - 1][j - 1] - prices[j]);
            }    
        }
        return maxProfit[k][len - 1];
    } 
    
    public static void main(String[] args) {
		int[] prices = {10,11,14,23,32,27,18,16,10,15,7,9};
		int[] prices2 = {};
		System.out.println(maxProfit(4, prices));
		System.out.println(maxProfit(4, prices2));
	}
}
