import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations 
	 * that make up that amount. You may assume that you have infinite number of each kind of coin.
	 * 
	 * Note: You can assume that
	 * 
	 * 0 <= amount <= 5000
	 * 1 <= coin <= 5000
	 * the number of coins is less than 500
	 * the answer is guaranteed to fit into signed 32-bit integer
	 * 
	 * Example 1:
	 * 
	 * Input: amount = 5, coins = [1, 2, 5]
	 * Output: 4
	 * Explanation: there are four ways to make up the amount:
	 * 5=5
	 * 5=2+2+1
	 * 5=2+1+1+1
	 * 5=1+1+1+1+1
	 * 
	 * Example 2:
	 * 
	 * Input: amount = 3, coins = [2]
	 * Output: 0
	 * Explanation: the amount of 3 cannot be made up just with coins of 2.
	 * 
	 * Example 3:
	 * 
	 * Input: amount = 10, coins = [10] 
	 * Output: 1
	 */
	
	// Solution 1: DP
	 public static int change(int amount, int[] coins) {        
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int i = 0; i < coins.length; i++) {
        	for (int j = 1; j <= amount; j++) {
        		int diff = j - coins[i];
        		if (diff >= 0) {
        			dp[j] += dp[diff];
        		}
        	}
        }

        // 下面的写法不对， 会重复算[1, 1, 2][2, 1, 1]
//        for (int i = 1; i <= amount; i++) {
//        	for (int j = 0; j < coins.length; j++) {
//        		int diff = i - coins[j];
//        		if (diff >= 0) {
//        			dp[i] += dp[diff];
//        		}
//        	}
//        }
        
        return dp[amount];
	 }
	
	// Solution 2: DFS + mem: 和LC377. Combination Sum IV基本一样，两点区别在于：
	// 1. DFS要记录pos，因为不能重复计算[1, 1, 2], [2, 1, 1]
	// 2. map因此也要同时记录result 和pos
	// 用map比较慢，可以改用cache[][]。但时间复杂度一样
	/*
    public static int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            if (amount == 0) {
        		return 1;
        	}
            return 0;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        
        return helper(coins, amount, map, 0);
    }

    private static int helper(int[] coins, int amount, Map<Integer, Map<Integer, Integer>> map, int pos) {
        if (amount == 0) {
            return 1;
        }
        
        if (amount < 0) {
            return 0;
        }
        
        if (map.containsKey(amount)) {
        	if (map.get(amount).containsKey(pos)) {
        		return map.get(amount).get(pos);
        	}
        }
        
        int result = 0;
        
        for (int i = pos; i < coins.length; i++) {
            result += helper(coins, amount - coins[i], map, i);
        }
        
        if (!map.containsKey(amount)) {
        	map.put(amount, new HashMap<>());
        }
        map.get(amount).put(pos, result);
        return result;
    }
    */
    /*
    public static int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
        	if (amount == 0) {
        		return 1;
        	}
            return 0;
        }
        
        Integer[][] cache = new Integer[amount + 1][coins.length];
        
        return helper(coins, amount, cache, 0);
    }

    private static int helper(int[] coins, int amount, Integer[][] cache, int pos) {
        if (amount == 0) {
            return 1;
        }
        
        if (amount < 0) {
            return 0;
        }
        
        if (cache[amount][pos] != null) {
        	return cache[amount][pos];
        }
        
        int result = 0;
        
        for (int i = pos; i < coins.length; i++) {
            result += helper(coins, amount - coins[i], cache, i);
        }
        
        cache[amount][pos] = result;

        return result;
    }
    */
    public static void main(String[] args) {
		int[] test1 = {1, 2, 5};
		System.out.println(change(5, test1));
		
		int[] test2 = {2};
		System.out.println(change(3, test2));
		
		int[] test3 = {10};
		System.out.println(change(10, test3));
	}
}
