
public class Solution {
	/*
	 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: 
	 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
	 * 
	 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. 
	 * In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
	 * 
	 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
	 * 
	 * Example 1:
	 * Input: "a"
	 * Output: 1
	 * Explanation: Only the substring "a" of string "a" is in the string s.
	 * 
	 * Example 2:
	 * Input: "cac"
	 * Output: 2
	 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
	 * 
	 * Example 3:
	 * Input: "zab"
	 * Output: 6
	 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
	 */
	
	// https://discuss.leetcode.com/topic/70658/concise-java-solution-using-dp
    public static int findSubstringInWraproundString(String p) {
       
    	// count[i] 表示以i为结尾的最大子字符串数目
    	// 这个数目等于最大子字符串长度
    	// e.g. "abcd" -- 数目为 "abcd", "bcd", "cd", "d"  与"abcd"长度一样
    	int[] count = new int[26];
    	int maxLength = 0;
    	int sum = 0;
    	
    	for (int i = 0; i < p.length(); i++) {
    		//p[i]与p[i - 1]相邻或收尾相接("za")
    		if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25)) {
    			maxLength++;
    		} else {
    			maxLength = 1;
    		}
		   
    		count[p.charAt(i) - 'a'] = Math.max(count[p.charAt(i) - 'a'], maxLength); 
    	}
       
    	for (int n: count) {
    		sum += n;
    	}
       
    	return sum;
    }
    
    public static void main(String[] args) {
		String test = "zab";
		
		System.out.println(findSubstringInWraproundString(test));
	}
}
