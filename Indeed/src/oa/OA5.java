package oa;

public class OA5 {
	/*
	 *【题目描述】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=145237
	 * 题目是best time to buy and sell stock 1 的变种，给你每一天的股价，然后给一个本金，要求只买一次卖一次，但是可以一次性买卖很多share的股票，问到最后最多剩下多少钱。
	 * 给一个数组 每一个数都是当天的股价（每股单价） 比如 4 10 6 7 1 5 这种， 然后给你100块钱假如， 然后让你决定， 你只能选一天买， 然后选一天卖掉， 但是你可以买很多share， 
	 * 求最大利润， 比如刚才这组数据，就显然是第一天买第二天卖， 第一天买进25share， 第二天全卖掉， 你的100块钱就变成了250块。
	 * 
	 *【解题思路】无论何时买入，那么肯定是在买入的后面的最高的价格卖出，于是我们使用max 表示从i到len-1的最高价格，这个可以从后向前计算，在计算max数组的过程中，就可以假定在i买入，
	 * 在max 卖出，于是得到一个局部最优解，i--，这些局部最优解的最大值，就是最后的最优解。
	 */

	public static int maxProfit(int[] prices, int money){
		if (prices == null || prices.length == 0 || money <= 0) {
			return 0;
		}
		
		int maxProfit = 0;
		int n = prices.length;
		int maxPrice = prices[n - 1];
		
		for (int i = n - 2; i >= 0; i--) {
			int profit = (money / prices[i]) * (maxPrice - prices[i]);
			if (profit > maxProfit) {
				maxProfit = profit;
			}
			
			if (prices[i] > maxPrice) {
				maxPrice = prices[i];
			}
		}
		
		return maxProfit + money;
		
	}
	
	public static void main(String[] args) {
		int[] test = {4, 10, 6, 7, 1, 5};
		
		System.out.println(maxProfit(test, 100));
	}
}
