import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
	 * Given a string s and a dictionary of words dict, determine if s can be 
	 * segmented into a space-separated sequence of one or more dictionary words.
	 * 
	 * For example, given
	 * 
	 * s = "leetcode",
	 * dict = ["leet", "code"].
	 * Return true because "leetcode" can be segmented as "leet code".
	 */
	
	// Time: O(n^2)
	public static boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return true;
		}

		Set<String> set = new HashSet<>(wordDict);

		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] = dp[j] && set.contains(s.substring(j, i));
				if (dp[i])
				{
					break;
				}
			}
		}

		return dp[n];
	}

//    public static boolean wordBreak(String s, List<String> wordDict) {
//        int n = s.length();
//
//        boolean[] dp = new boolean[n + 1];
//
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (wordDict.contains(s.substring(0, i)) || (dp[j] && wordDict.contains(s.substring(j, i)))) {
//                    dp[i] = true;
//                    break;
//                }
//            }
//        }
//
//        return dp[n];
//    }
    
    public static void main(String[] args) {
		String test = "leetcode";
		List<String> stringList = new ArrayList<String>();
		
		stringList.add("leet");
		stringList.add("code");
		stringList.add("test");
		stringList.add("none");
		
		System.out.println(stringList);
		System.out.println(wordBreak(test, stringList));
	}
}
