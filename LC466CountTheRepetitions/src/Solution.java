
public class Solution {
	/*
	 * Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".
	 * 
	 * On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such 
	 * that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be 
	 * obtained from “acbbe”.
	 * 
	 * You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. 
	 * Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] 
	 * can be obtained from S1.
	 * 
	 * Example:
	 * 
	 * Input:
	 * s1="acb", n1=4
	 * s2="ab", n2=2
	 * 
	 * Return:
	 * 2
	 */
	
	// http://www.cnblogs.com/grandyang/p/6149294.html
	// https://discuss.leetcode.com/topic/72105/c-solution-inspired-by-70664914-with-organized-explanation/9
	
    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[] repeatCount = new int[len2 + 1];
        int[] nextIndex   = new int[len2 + 1];
        int j = 0;
        int count = 0;
        
        for (int k = 1; k <= n1; k++) {
        	for (int i = 0; i < len1; i++) {
        		if (s1.charAt(i) == s2.charAt(j)) {
        			j++;
        			if (j == len2) {
        				j = 0;
        				count++;
        			}
        		}
        	}
        	repeatCount[k] = count;
        	nextIndex[k] = j;
        	
        	for (int start = 0; start < k; start++) {
        		if (nextIndex[start] == j) {
        			int interval = k - start;
        			int repeat = (n1 - start) / interval;
        			int patternCount = (repeatCount[k] - repeatCount[start]) * repeat;
        			int remainCount = repeatCount[start + (n1 - start) % interval];
        			return (patternCount + remainCount) / n2;
        		}
        	}
        }
        return repeatCount[n1] / n2;
    }
    
    public static void main(String[] args) {
		System.out.println(getMaxRepetitions("acb", 4, "ab", 2));
	}
}
