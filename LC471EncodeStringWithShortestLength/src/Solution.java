
public class Solution {
	/*
	 * Given a non-empty string, encode the string such that its encoded length is the shortest.
	 * 
	 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
	 * 
	 * Note:
	 * 1. k will be a positive integer and encoded string will not be empty or have extra space.
	 * 2. You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
	 * 3. If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them 
	 * is fine.
	 * 
	 * Example 1:
	 * Input: "aaa"
	 * Output: "aaa"
	 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
	 * 
	 * Example 2:
	 * Input: "aaaaa"
	 * Output: "5[a]"
	 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
	 * 
	 * Example 3:
	 * Input: "aaaaaaaaaa"
	 * Output: "10[a]"
	 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
	 * 
	 * Example 4:
	 * Input: "aabcaabcd"
	 * Output: "2[aabc]d"
	 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
	 * 
	 * Example 5:
	 * Input: "abbbabbbcabbbabbbc"
	 * Output: "2[2[abbb]c]"
	 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
	 */
	
	// https://segmentfault.com/a/1190000008341304
	// http://www.cnblogs.com/grandyang/p/6194403.html
	
    public static String encode(String s) {
        int n = s.length();
        
        //dp[i][j]表示s在[i, j]范围内的字符串的缩写形式
        String[][] dp = new String[n][n];
        
        for (int i = 0; i < n; i++) {
        	dp[i][i] = "" + s.charAt(i);
        }
        
        // 遍历s的所有子字符串
        // 对于任意一段子字符串[i, j]，以中间任意位置k来拆分成两段, 比较dp[i][k]加上dp[k+1][j]的总长度和dp[i][j]的长度，将长度较小的字符串赋给dp[i][j]，
        for (int len = 1; len < n; len++) {
        	for (int i = 0; i + len < n; i++) {
        		int j = i + len;
        		//System.out.println("i = " + i + " j = " + j + " sub = " + s.substring(i, j + 1));
        		for (int k = i; k < j; k++) {
        			// 第一次循环，从left从dp[i][i]， right从dp[j][j]开始，否则其它dp都是null，因此k < j 而不是k <= j
        			int left = dp[i][k].length();
        			int right = dp[k + 1][j].length();
        			if (dp[i][j] == null || left + right < dp[i][j].length()) {
        				//System.out.println("before, dp[i][j] = " + dp[i][j]);
        				dp[i][j] = dp[i][k] + dp[k + 1][j];
        				//System.out.println("dp[i][k] = " + dp[i][k] + " dp[k+1][j]  = " + dp[k + 1][j] + " dp[i][j] = " + dp[i][j]);
        			}
        		}
        		
        		// 在s中取出[i, j]范围内的子字符串t进行合并，方法是：
        		// 在取出的字符串t后面再加上一个t，然后在这里面寻找子字符串t的第二个起始位置， 如果第二个起始位置小于t的长度的话，说明t包含重复字符串
        		// e.g. t = "abab", 那么t+t = "abababab"，我们在里面找第二个t出现的位置为2，小于t的长度4，说明t中有重复出现，重复的个数为t.size()/pos = 2个
        		// 用这个方法来判断字符串中可以合并的部分
        		String sub = s.substring(i, j + 1);
        		int index = (sub + sub).indexOf(sub, 1);
        		
        		if (index < sub.length()) {
        			//System.out.println("sub = " + sub);
        			sub = (sub.length() / index) + "[" + dp[i][i + index - 1] + "]";
        		}
        		
        		if (dp[i][j] == null || dp[i][j].length() > sub.length()) {
        			dp[i][j] = sub;
        		}
				//System.out.println();
        	}
        }
        
        return dp[0][n-1];
    }
    
    public static void main(String[] args) {
		System.out.println(encode("aabcaabcd"));
		//System.out.println(encode("abbbabbbcabbbabbbc"));
	}
}
