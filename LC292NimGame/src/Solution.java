
public class Solution {
	/*
	 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns 
	 * to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
	 * 
	 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game 
	 * given the number of stones in the heap.
	 * 
	 * Example:
	 * 
	 * Input: 4
	 * Output: false 
	 * Explanation: If there are 4 stones in the heap, then you will never win the game;
	 *              No matter 1, 2, or 3 stones you remove, the last stone will always be 
	 *              removed by your friend.
	 */
	
	
	public static boolean canWinNim(int n) {
		return n % 4 != 0;
	}
	// 常规思路：DP， Memory Limit Exceeded 
	/*
    public static boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;
        
        for (int i = 4; i <= n; i++) {
        	dp[i] = !dp[i - 3] || !dp[i - 2] || !dp[i - 1];
        }
        
        return dp[n];
    }
    */
    public static void main(String[] args) {
		System.out.println(canWinNim(4));
		System.out.println(canWinNim(5));
		System.out.println(canWinNim(6));
		System.out.println(canWinNim(7));
		System.out.println(canWinNim(8));
	}
}
