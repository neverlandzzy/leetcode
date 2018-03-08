package onsite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalizedTitle {
	/*
	 * Given a rawTitle, and a list(or array) of clean titles. For each clean title,
	 * the raw title can get a "match point". For example, if raw title is "senior software
	 * engineer" and clean titles are "software engineer" and "mechanical engineer", the
	 * "match point" will be 2 and 1. In this case we return "software engineer" because
	 * it has higher "match point".
	 * 【基本题】输入没有重复
	 * 【Follow up】 输入有重复 
	 * 
	 * 【思路】无论输入有没有重复，如果考虑单词顺序，则只能用dp做，类似于LCS。 面经里的代码对于abc, dc的例子是不对的。
	 *       若不考虑输入单词顺序，则基本题可以用HashSet做
	 * 
	 */
	
	// 基本题 -- 不考虑单词顺序 -- "senior software engineer" 和 "engineer software"的point是2
	public static String getHighestTitle1(String rawTitle, String[] cleanTitles){
		Set<String> set = new HashSet<>();
		String[] rawArray = rawTitle.split(" ");
		
		int maxPoint = 0;
		String result = cleanTitles[0];
		
		for (String r: rawArray) {
			set.add(r);
		}
		
		for (String t: cleanTitles) {
			int point = getPoint1(set, t);
			if (point > maxPoint) {
				result = t;
				maxPoint = point;
			}
		}
		
		return result;
	}
	
	private static int getPoint1(Set<String> set, String title) {
		String[] titleArray = title.split(" ");
		int result = 0;
		
		for (String t: titleArray) {
			if (set.contains(t)) {
				result++;
			}
		}
		return result;
	}
	
	// 若考虑单词顺序，则用DP解
	public static String getHighestTitle2(String rawTitle, String[] cleanTitles){
		String[] rawArray = rawTitle.split(" ");
		int maxPoint = 0;
		String result = cleanTitles[0];
		
		for (String t: cleanTitles) {
			String[] cleanArray = t.split(" ");
			int point = getPoint2(rawArray, cleanArray);
			if (point > maxPoint) {
				result = t;
				maxPoint = point;
			}
		}
		
		return result;
	}
	
	private static int getPoint2(String[] rawArray, String[] cleanArray) {
		int m = rawArray.length;
		int n = cleanArray.length;
		int[][] dp = new int[m][n];
		
		dp[0][0] = rawArray[0].equals(cleanArray[0]) ? 1 : 0;
		int result = dp[0][0];
		
		for (int i = 1; i < m; i++) {
			/* 若只考虑连续字符串  a b c b e 和 a c b 只算2（c b）
			if (rawArray[i].equals(cleanArray[0])) {
				dp[i][0] = 1;
				result = 1;
			}
			*/
			if (rawArray[i].equals(cleanArray[0])) {
				dp[i][0] = 1;
			} else {
				dp[i][0] = dp[i - 1][0];
			}
			result = dp[i][0];
		}
		
		for (int i = 1; i < n; i++) {
			/* 若只考虑连续字符串  a b c b e 和 a c b 只算2（c b）
			if (cleanArray[i].equals(rawArray[0])) {
				dp[0][i] = 1;
				result = 1;
			}
			*/
			if (cleanArray[i].equals(rawArray[0])) {
				dp[0][i] = 1;
			} else {
				dp[0][i] = dp[0][i - 1];
			}
			result = dp[0][i];
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				/* 若只考虑连续字符串  a b c b e 和 a c b 只算2（c b）
				if (rawArray[i].equals(cleanArray[j])) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
				*/
				if (rawArray[i].equals(cleanArray[j])) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
				
				result = Math.max(result, dp[i][j]);
			}
		}
		
		//System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		String rawTitle11 = "senior software engineer";
		String[] cleanTitle11 = {"software engineer", "mechanical engineer", "engineer senior software"};
		
		System.out.println(getHighestTitle1(rawTitle11, cleanTitle11));
		
		String rawTitle12 = "a a a b";
		String[] cleanTitle12 = {"a a b", "a a", "a a c"};
		System.out.println(getHighestTitle2(rawTitle11, cleanTitle11));
		System.out.println(getHighestTitle2(rawTitle12, cleanTitle12));
		
		String rawTitle13 = "a b c b e";
		String[] cleanTitle13 = {"a c b", "a b c e", "a a c"};
		System.out.println(getHighestTitle2(rawTitle13, cleanTitle13));
	}
}
