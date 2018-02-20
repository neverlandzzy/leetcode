import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
	
	/*
	 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
	 * For example, Given s = “eceba” and k = 2,
	 * T is "ece" which its length is 3.
	 */
	
	
	// [*follow up*] 
	// The interviewer may say that the String is given as a steam. In this situation, we can't maintain a "left pointer" as the 
	// classical O(n) solution.
	// 
	// 由于String是stream，我们无法记录left pointer，因此，我们不再记录每个char出现的次数，而是用Map<Character, Integer>记录每个char最近一次出现的位置
	// 用TreeMap<Integer, Character> 记录最左边的字符最后一次出现的位置。
	// 若用PriorityQueue 可以用O(1)时间getMin。但PQ要用O(n)的时间来update node。 而TreeMap getMin和update都是O(log n)，因此用TreeMap
	// Time: O(k*logn)
	public static int lengthOfLongestSubstringKDistinctFollowUp(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
			return 0;
		}
		
		TreeMap<Integer, Character> lastOccurence = new TreeMap<>();
		Map<Character, Integer> map = new HashMap<>();
		
		int j = 0; 
		int result = 1;
		
		for (int i = 0; i < s.length(); i++) {
			char in = s.charAt(i);
			while (map.size() == k && !map.containsKey(in)) {
				// 当map的size == k时并且新的字符不在map中时，找到当前最左边的字符的最后出现位置first， 并在两个map中删除
				int first = lastOccurence.firstKey();
				char out = lastOccurence.get(first);
				map.remove(out);
				lastOccurence.remove(first);
				j = first + 1;
			}
			
			if (map.containsKey(in)) {
				lastOccurence.remove(map.get(in));
			}
			map.put(in, i);
			lastOccurence.put(i, in);
			result = Math.max(result, i - j + 1);
		}
		
		return result;
	}
	
	// https://discuss.leetcode.com/topic/48827/java-o-nlogk-using-treemap-to-keep-last-occurrence-interview-follow-up-question/6
	// similar to LC3 LC159
	// 常规解法和LC159一样
	//
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] map = new int[256];
        int result = 0;
        int counter = 0;
        int j = 0;
        
        for (int i = 0; i < s.length(); i++) {        
            
            while (j < s.length()) {
                if (map[s.charAt(j)] != 0) {
                    map[s.charAt(j)]++;
                } else {
                    if (counter == k) {
                        break;
                    } 
                    map[s.charAt(j)]++;
                    counter++;
                }
                result = Math.max(result, j - i + 1);
                j++;
            }
                        
            map[s.charAt(i)]--;
            
            if (map[s.charAt(i)] == 0) {
                counter--;
            }
        }
        
        return result;
    }
    
    
    
    public static void main(String[] args) {
		String test = "eceba";
		
		System.out.println(lengthOfLongestSubstringKDistinct(test, 2));
	}
}
