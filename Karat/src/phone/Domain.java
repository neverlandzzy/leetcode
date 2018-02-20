package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Domain {
	/*
	 * 【第一题】给广告在每个domain上被click的次数， 要求返回domain及其所有sub domain 被click的总次数
	 *  输入：[
 	 *            ["google.com", "60"],
	 *            ["yahoo.com", "50"],
	 *            ["sports.yahoo.com", "80"]
	 *          ]
	 *  输出：[
	 *             ["com", "190"], (60+50+80)
	 *             ["google.com", "60"], 
	 *             ["yahoo.com", "130"] (50+80)
	 *             ["sports.yahoo.com", "80"]
	 *          ]
	 *          
	 * 【第二题】给每个user访问历史记录，找出两个user之间longest continuous common history -- 类似 LC718
	 *  输入： [
	 *             ["3234.html", "xys.html", "7hsaa.html"], // user1
	 *             ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
	 *          ], user1 and user2 （指定两个user求intersect）
	 *  输出： ["xys.html", "7hsaa.html"]
	 */
	
	// 第一题
	public static List<List<String>> clickSum(List<List<String>> input) {
		List<List<String>> result = new ArrayList<>();
		if (input == null || input.size() == 0) {
			return result;
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < input.size(); i++) {
			String[] domains = input.get(i).get(0).split("\\.");
			int count = Integer.valueOf(input.get(i).get(1));
			String s = "";
			for (int j = domains.length - 1; j >= 0; j--) {
				s = domains[j] + s;
				map.put(s, map.getOrDefault(s, 0) + count);
				s = "." + s;
			}
		}
		
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			List<String> list = new ArrayList<>();
			list.add(entry.getKey());
			list.add(String.valueOf(entry.getValue()));
			
			result.add(list);
		}
		
		return result;
	}
	
	// 第二题  -- 类似LC718
	
	public static List<String> commonHistory(List<List<String>> input) {
		List<String> result = new ArrayList<>();
		if (input == null || input.size() == 0) {
			return result;
		}
		int m = input.get(0).size();
		int n = input.get(1).size();
		int max = Integer.MIN_VALUE;
		int index = -1;
		
		// dp[i][j] be the longest common domain of input.get(0) and input.get(1)
		int[][] dp = new int[m][n];
		
		if (input.get(0).get(m - 1).equals(input.get(1).get(n - 1))) {
			dp[m - 1][n - 1] = 1;
			index = m - 1;
			max = 1;
		}
		
		for (int i = m - 2; i >= 0; i--) {
			if (input.get(0).get(i).equals(input.get(1).get(n - 1))) {
				dp[i][n - 1] = 1;
				index = i;
				max = 1;
			}
		}
		
		for (int j = n - 2; j >= 0; j--) {
			if (input.get(0).get(m - 1).equals(input.get(1).get(j))) {
				dp[m - 1][j] = 1;
				index = m - 1;
				max = 1;
			}
		}
		
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				if (input.get(0).get(i).equals(input.get(1).get(j))) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					if (dp[i][j] > max) {
						max = dp[i][j];
						index = i;
					}
				}
			}
		}
		
		if (index == -1) {
			return result;
		}

		for (int i = index; i <= max; i++) {
			result.add(input.get(0).get(i));
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【第一题】输出domain及其所有sub domain 被click的总次数 ***********");
		List<List<String>> test11 = new ArrayList<>(Arrays.asList(Arrays.asList("google.com", "60"), 
													              Arrays.asList("yahoo.com", "50"), 
													              Arrays.asList("sports.yahoo.com", "80")));
		System.out.println(clickSum(test11));
		System.out.println();
		System.out.println("*********** 【第二题】输出两个user之间longest continuous common history ***********");
		List<List<String>> test21 = new ArrayList<>(Arrays.asList(Arrays.asList("3234.html", "xys.html", "7hsaa.html"), 
				                       						      Arrays.asList("3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html")));
		
		System.out.println(commonHistory(test21));
	}
}
