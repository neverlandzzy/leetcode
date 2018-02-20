import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", 
	 * and use the dial to spell a specific keyword in order to open the door.
	 * 
	 * Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the 
	 * keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.
	 * 
	 * Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the 
	 * string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 
	 * direction and then by pressing the center button. 
	 * 
	 * At the stage of rotating the ring to spell the key character key[i]:
	 * 
	 * You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to 
	 * align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
	 * If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 
	 * 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the 
	 * spelling.
	 * 
	 * Example:
	 * 
	 * Input: ring = "godding", key = "gd"
	 * Output: 4
	 * 
	 * Explanation:
	 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character. 
	 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
	 * Also, we need 1 more step for spelling.
	 * So the final output is 4.
	 * 
	 * Note:
	 * 1. Length of both ring and key will be in range 1 to 100.
	 * 2. There are only lowercase letters in both strings and might be some duplcate characters in both strings.
	 * 3. It's guaranteed that string key could always be spelled by rotating the string ring.
	 */
	
	// Solution 1: DFS + mem
	/*
    public static int findRotateSteps(String ring, String key) {
        Map<String, Integer> map = new HashMap<>();
        return helper(ring, key, 0, map);
    }
    
    private static int helper(String ring, String key, int index, Map<String, Integer> map) {
    	if (index == key.length()) {
    		return 0;
    	}
    	
    	String hashKey = ring + index;
    	if (map.containsKey(hashKey)) {
    		return map.get(hashKey);
    	}
    	
    	int result = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < ring.length(); i++) {
    		if (ring.charAt(i) == key.charAt(index)) {
    			// ring[0]为ring 12点钟方向的字符，每次转动后，需要重新编排ring的每个字符的顺序
    			String newRing = ring.substring(i, ring.length()) + ring.substring(0, i);
    			// ring[0]为ring 12点钟方向的字符，ring[i] 和 ring[ring.length() - i] 分别为从12点钟的字符向左或向右走到当前字符的步数，取最小的
    			int step = 1 + Math.min(i, ring.length() - i); 
    			step += helper(newRing, key, index + 1, map);
    			result = Math.min(result, step);
    		}
    	}
    	map.put(hashKey, result);
    	return result;
    }
    */
	
	// Solution 2: DP O(m * n^2)
    public static int findRotateSteps(String ring, String key) {
    	int m = key.length();
    	int n = ring.length();
    	
    	// dp[i][j]: 以ring[i - 1]在12点位置为起点，match key的[j ~ n]所需要的步数
    	int[][] dp = new int[m + 1][n];
    	
    	for (int i = m - 1; i >= 0; i--) {
    		for (int j = 0; j < n; j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    			for (int k = 0; k < n; k++) {
    				if (ring.charAt(k) == key.charAt(i)) {
    					int diff = Math.abs(j - k);
    					int step = Math.min(diff, n - diff);
    					dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
    				}
    			}
    		}
    	}
    	
    	return dp[0][0] + m;
    }
    
    public static void main(String[] args) {
    	System.out.println(findRotateSteps("godding", "gd"));
	}
}
