
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
	
	/*
	 * https://blog.csdn.net/isnoel/article/details/52788480
	 * 找到每一段最长连续上升序列，在序列的第一天买入，在序列的最后一天卖出，这样就能达到利益的最大化。给个简单不太严谨的证明：首先在上升序列中买入卖出是不值得的，
	 * 不会赚到更多的钱。例如序列1，2， 3， 4， 5， 6，只有以1的价格买入，以6的价格卖出，才能获得最大的利益。而在序列中买入，在序列之后卖出也不会赚到更多的钱。
	 * 假如我在序列之后找到一个比序列最后一个元素还要大的元素卖出，这样会不会得到更好的结果呢？答案是不会的，因为序列之后的第一个元素是不会大于序列的最后一个元素的，
	 * 如果我们选择在序列第一天买入，最后一天卖出，在之后的一天又买入，再在找到的元素更大的那一天卖出，这样收入会更多。在序列之前买入，序列之中卖出，或者在序列之前买入，
	 * 序列之后卖出，简单的证明相似，就不再说明。
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
