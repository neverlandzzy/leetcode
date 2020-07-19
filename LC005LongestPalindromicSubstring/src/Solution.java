
public class Solution {
    
	/**
	 * Given a string S, find the longest palindromic substring in S. 
	 * 
	 * You may assume that the maximum length of S is 1000, and there exists one 
	 * unique longest palindromic substring.
	 * 
	 */
	
	// Solution 1: expand around center - Time: O(n^2)  Space: O(1)
	/*
	public static String longestPalindrome(String s) {
		int start = 0;
		int end = 0;
		
		if (s == null || s.length() == 0) {
			return s;
		}
		
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			
			int len = Math.max(len1, len2);
			
			if (len > end - start + 1) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
			}
		}
		
		return s.substring(start, end + 1);
	}
	
	
	private static int expandAroundCenter(String s, int left, int right) {
		
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		
		return right - left - 1;
	}
	*/
	
	// Solution 2: DP - Time: O(n^2) Space: O(n^2)
	
	public static String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		
		int n = s.length();
		int start = 0;
		int end = 0;
		int max = 0;
		
		boolean[][] dp = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				dp[j][i] = (s.charAt(i) == s.charAt(j)) && (i - j <= 2 || dp[j + 1][i - 1]);
				
				if (dp[j][i] && max < i - j + 1) {
					start = j;
					end = i;
					max = i - j + 1;
				}
			}
		}
		
		return s.substring(start, end + 1);
	}
	
	/*
	public static String longestPalindrome(String s) {
        
        int longestLen = 0;
        int longestIndex = 0;
        
        for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
        	if (isPalindrome(s, currentIndex-longestLen, currentIndex)) {
        		longestLen = longestLen + 1;
        		longestIndex = currentIndex;
        	} else if ((currentIndex-longestLen-1 >= 0) && isPalindrome(s, currentIndex-longestLen-1, currentIndex)) {
        		longestLen = longestLen + 2;
        		longestIndex = currentIndex;
        	}
        }
        
        longestIndex = longestIndex + 1;
        
        return s.substring(longestIndex-longestLen, longestIndex);
    }
	 */
	public static boolean isPalindrome(String s, int startIndex, int endIndex) {
	
			for(int i = startIndex, j = endIndex; i < j; i++, j--) {
				if(s.charAt(i) != s.charAt(j)) {
					return false;
				}
			}
		
		return true;
	}
	
	public static void main(String[] args) {
		String test1 = "abbad";
		String test2 = "abbca";
		
		System.out.println(longestPalindrome(test1));
		System.out.println(longestPalindrome(test2));

	}
		
}
