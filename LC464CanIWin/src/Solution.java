import java.util.HashMap;


public class Solution {
	/*
	 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running
	 * total to reach or exceed 100 wins.
	 * 
	 * What if we change the game so that players cannot re-use integers?
	 * 
	 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until 
	 * they reach a total >= 100.
	 * 
	 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, 
	 * assuming both players play optimally.
	 * 
	 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
	 * 
	 * Example
	 * 
	 * Input:
	 * maxChoosableInteger = 10
	 * desiredTotal = 11
	 * 
	 * Output:
	 * false
	 * 
	 * Explanation:
	 * No matter which integer the first player choose, the first player will lose.
	 * The first player can choose an integer from 1 up to 10.
	 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
	 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
	 * Same with other integers chosen by the first player, the second player will always win.
	 */
	
	// https://discuss.leetcode.com/topic/68896/java-solution-using-hashmap-with-detailed-explanation/3?page=1
	
    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        
        // 因为每个int只能用一次，所以若sum小于desiredTotal，则没有人能赢
        if (sum < desiredTotal) {
        	return false;
        }
        
        HashMap<Integer, Boolean> map = new HashMap<>();
        
        return helper(map, 0, maxChoosableInteger, desiredTotal);
    }
    
    private static boolean helper(HashMap<Integer, Boolean> map, int state, int n, int desiredTotal) {
    	if (map.containsKey(state)) {
    		return map.get(state);
    	}
    	
    	for (int i = 1; i <= n; i++) {
    		if ((state & (1 << (i - 1))) != 0) {
    			continue;
    		}
    		
    		if (desiredTotal <= i || !helper(map, (state | 1 << (i - 1)), n, desiredTotal - i)) {
    			map.put(state, true);
    			return true;
    		}
    	}
    	map.put(state, false);
    	return false;
    }
    
    public static void main(String[] args) {
		System.out.println(canIWin(10, 11));
	}

}
