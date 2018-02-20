import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
	 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
	 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
	 * 
	 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * ["with", "example", "science"], "thehat"
	 * Output:
	 * 3
	 * Explanation:
	 * We can use 2 "with" stickers, and 1 "example" sticker.
	 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
	 * Also, this is the minimum number of stickers necessary to form the target string.
	 * 
	 * Example 2:
	 * Input:
	 * ["notice", "possible"], "basicbasic"
	 * Output:
	 * -1
	 * Explanation:
	 * We can't form the target "basicbasic" from cutting letters from the given stickers.
	 * 
	 * Note:
	 * stickers has length in the range [1, 50].
	 * 
	 * stickers consists of lowercase English words (without apostrophes).
	 * 
	 * target has length in the range [1, 15], and consists of lowercase English letters.
	 * 
	 * In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target 
	 * was chosen as a concatenation of two random words.
	 * 
	 * The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
	 */
	
	// Time: O(2^m 26n), m is length of target, n is number of stickers.
    public static int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickersMap = new int[n][26];
        
        for (int i = 0; i < n; i++) {
        	for (char c: stickers[i].toCharArray()) {
        		stickersMap[i][c - 'a']++;
        	}
        }
        
        // Minimal stickers required for targets
        Map<String, Integer> map = new HashMap<>();
        map.put("", 0);
        
        return helper(map, stickersMap, target);
    }
	
    private static int helper(Map<String, Integer> map, int[][] stickersMap, String target) {
    	if (map.containsKey(target)) {
    		return map.get(target);
    	}
    	
    	int n = stickersMap.length;
    	int result = Integer.MAX_VALUE;
    	
    	int[] targetMap = new int[26];
    	
    	for (char c: target.toCharArray()) {
    		targetMap[c - 'a']++;
    	}
    	
    	for (int i = 0; i < n; i++) {
    		
    		// 优化
    		// If the target can be spelled out by a group of stickers, at least one of them has to contain character target[0]. 
    		// So I explicitly require next sticker containing target[0], which significantly reduced the search space.
    		if (stickersMap[i][target.charAt(0)-'a'] == 0) {
    			continue;
    		}
    		
    		StringBuilder sb = new StringBuilder();
    		for (int j = 0; j < 26; j++) {
    			if (targetMap[j] > 0) {
    				// 若 targetMap[j] - stickersMap[i][j] <= 0， 则说明当前的sticker上的当前字母，可以满足target上当前字母的需求， 否则，将
    				// 超过的部分加到sb中
    				for (int k = 0; k < Math.max(0,  targetMap[j] - stickersMap[i][j]); k++) {
    					sb.append((char)('a' + j));
    				}
    			}
    		}
    		
    		// s: target用过当前sticker之后，剩下的不能完成的string
    		String s = sb.toString();
    		
    		// 若s.length() == target.length()，则说明当前的sticker对target没有任何贡献，因此不要再继续搜索
    		if (s.length() != target.length()) {
    			int tmp = helper(map, stickersMap, s);
    			if (tmp != -1) {
    				result = Math.min(result, tmp + 1);
    			}
    		}
    	}
    	
    	map.put(target, result == Integer.MAX_VALUE ? -1 : result);
    	return map.get(target);
    }

	public static void main(String[] args) {
		String[] stickers1 = {"with", "example", "science"};
		String target1 = "thehat";
		
		String[] stickers2 = {"notice", "possible"};
		String target2 = "basicbasic";
		
		System.out.println(minStickers(stickers1, target1));
		//System.out.println(minStickers(stickers2, target2));
	}
}
