
public class Solution {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * Design an algorithm to find the maximum profit. You may complete as many transactions 
	 * as you like (ie, buy one and sell one share of the stock multiple times) with the following 
	 * restrictions:
	 * 
	 * You may not engage in multiple transactions at the same time (ie, you must sell the stock 
	 * before you buy again).
	 * 
	 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
	 * Example:
	 * 
	 * prices = [1, 2, 3, 0, 2]
	 * maxProfit = 3
	 * transactions = [buy, sell, cooldown, buy, sell]
	 */
	
	
	// https://leetcode.com/discuss/71354/share-my-thinking-process
	// https://soulmachine.gitbooks.io/algorithm-essentials/java/dp/best-time-to-buy-and-sell-stock-with-cooldown.html
	
	/*
	public static int maxProfit(int[] prices) {
		
		// before day i, the maxProfit for any sequence end with buy, sell, rest:
		// 1. buy[i]  = max(rest[i - 1] - price, buy[i - 1])
		// 2. sell[i] = max(buy[i - 1] + price, sell[i - 1])
		// 3. rest[i] = max(buy[i - 1], sell[i - 1], rest[i - 1]);
		// Since buy[i] <= rest[i] --> rest[i] = max(sell[i - 1], rest[i - 1]) --> 'buy, rest, buy' never happens
		// Since rest[i] = sell[i - 1]
		// we have:
		// buy[i] = max(sell[i - 2] - price, buy[i - 1])
		// sell[i] = max (buy[i - 1] + price, sell[i - 1])
		 
		if (prices == null || prices.length <= 1) {
			return 0;
		}
		
		int n = prices.length;
		
		int[] buy = new int[n];
		int[] sell = new int[n];
		
		buy[0] = -prices[0];
		sell[0] = 0;
		
		//
		// buy[0] = -prices[0];
        // sell[0] = 0;  
        // buy[1] = Math.max(-prices[0], -prices[1]);
        // sell[1] = Math.max(prices[1] - prices[0], 0);
        
		
		for (int i = 1; i < n; i++) {
			buy[i] = Math.max( (i > 1 ? sell[i - 2] : 0)- prices[i], buy[i - 1]);
			sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
		}
		
		return sell[n - 1];
	}
	*/
	
	//optimizing to O(1) space
	 
    public static int maxProfit(int[] prices) {
    	
        if (prices == null || prices.length <= 1) {
			return 0;
		}
		
		int n = prices.length;
        
        int buy = -prices[0];
        int sell = 0;
        int preSell = 0;
        
        for (int i = 1; i < n; i++) {
            int tmp = sell;
            // 这里交换下sell和buy的计算顺序，因为sell要依赖buy[i - 1].
            sell = Math.max(buy + prices[i], sell);
            buy = Math.max((i > 1 ? preSell : 0) - prices[i], buy);
            
            preSell = tmp;
        }
        
        return sell;
    }
    
    public static void main(String[] args) {
		int[] prices = {1, 2, 3, 0, 2};
		
		System.out.println(maxProfit(prices));
	}

    /*
     *         int sell = 0, preSell = 0, buy = Integer.MIN_VALUE, preBuy = 0;
        		
        for (int price : prices) {
        	preBuy = buy;
        	buy = Math.max(preBuy, preSell - price);
        	preSell = sell;
        	sell = Math.max(preSell, preBuy + price);
        }
        
        return sell;
     */
}
