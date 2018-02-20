
public class Solution {
	/*
	 * Given a string S, you are allowed to convert it to a palindrome by adding 
	 * characters in front of it. Find and return the shortest palindrome you can 
	 * find by performing this transformation.
	 * 
	 * For example:
	 * 
	 * Given "aacecaaa", return "aaacecaaa".
	 * Given "abcd", return "dcbabcd".
	 */
	
	// O(n^2)
	// 利用LC5的方法找从第一个字符开始的Palindrome，再把后面剩余的子串翻转后加在开头
    public static String shortestPalindrome(String s) {
		int start = 0;
		int end = 0;
		
		if (s == null || s.length() == 0) {
			return s;
		}
		
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			
			int len = Math.max(len1, len2);
			
			if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
			}
		}
        
		StringBuilder sb = new StringBuilder();
        
        sb.append(s.substring(end + 1)).reverse().append(s);
        
        return sb.toString();
	}
	
	
	private static int expandAroundCenter(String s, int left, int right) {
		
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}

		if (left == -1) {
			return right - left - 1;
		} else {
			return 0;
		}
	}
	/*
	// 利用LC5的方法找从第一个字符开始的Palindrome，再把后面剩余的子串翻转后加在开头。但这个做法TLE
	// O(n^2)
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int n = s.length();
        int end = 0;
        int max = 0;
        boolean[][] dp = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[j][i] = (s.charAt(i) == s.charAt(j)) && (i - j <= 2 || dp[j + 1][i - 1]);
                if (j == 0 && dp[j][i] && max < i - j + 1) {
                    end = i;
                    max = i - j + 1;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(s.substring(end + 1)).reverse().append(s);
        
        return sb.toString();
    }
    
    
    */
	
	// Solution 1: 
	// Manacher’s Algorithm O(n)
	// http://www.felix021.com/blog/read.php?2040
	// http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
	// http://wlcoding.blogspot.com/2015/03/palindrome-i-valid-num-ii-valid-str-iii.html?view=sidebar
    /*
	public static String shortestPalindrome(String s) {
        int R = 0;  // right bound
        int C = 0;  // center
        String t = preProcess(s);
        int n = t.length();
        int[] lps = new int[n];
        
    	int maxL = 0;
    	int center = 0;
        
        for (int i = 1; i <= n/2; i++) {
        	lps[i] = (i < R) ? Math.min(lps[2*C - i], R - i) : 1;   // since i-i'= 2*(i-C);
        	
        	while (t.charAt(i + lps[i]) == t.charAt(i - lps[i])) {  // Attempt to expand lps centered at i
        		lps[i]++;                
        	}
        	
        	if (i + lps[i] > R) {
        		C = i;
        		R = i + lps[i];
        	}
        	
            if (i == lps[i] && lps[i] > maxL) {
            	maxL = lps[i];
            	center = i;
            }
        	      	
        }
        
        int start = (center - maxL) / 2;
        int end = start + maxL - 1;
        
        StringBuilder result = new StringBuilder(s.substring(end));
        return result.reverse().append(s).toString();
        
    }
    
    private static String preProcess(String s) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("^");
    	
    	for (int i = 0; i < s.length(); i++) {
    		sb.append("#");
    		sb.append(s.charAt(i));
    	}
    	sb.append("#");
    	sb.append("$");
    	return sb.toString();
    }
    */
	// Solution 2: KMP O(n)  -- faster
	// https://www.youtube.com/watch?v=5i7oKodCRJo
	// https://leetcode.com/discuss/64309/clean-kmp-solution-with-super-detailed-explanation
	/*
	public static String shortestPalindrome(String s) {
		String newS = s + "#" + new StringBuilder(s).reverse().toString();
		int n = newS.length();
		int[] prefix = new int[n];
		int maxPrefix = 0;
		
		for (int i = 1; i < n; i++) {
			while (maxPrefix > 0 && newS.charAt(maxPrefix) != newS.charAt(i)) {
				maxPrefix = prefix[maxPrefix - 1];
			}
			
			if (newS.charAt(maxPrefix) == newS.charAt(i)) {
				maxPrefix++;
			}
			prefix[i] = maxPrefix;
		}
        
		return new StringBuilder(s.substring(prefix[n-1])).reverse().toString() + s;
	}
	*/
    public static void main(String[] args) {
		String str = "ecatacb";
		System.out.println(shortestPalindrome(str));
	}
}
