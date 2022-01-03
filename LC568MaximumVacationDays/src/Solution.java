
public class Solution {
	/**
	 * LeetCode wants to give one of its best employees the option to travel among N cities to collect algorithm problems. 
	 * But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. 
	 * Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules 
	 * and restrictions you need to follow.
	 * 
	 * Rules and restrictions:
	 * 1. You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
	 * 2. The cities are connected by flights. The flights are represented as a N*N matrix (not necessary symmetrical), called flights 
	 *    representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, flights[i][j] = 0; 
	 *    Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
	 * 3. You totally have K weeks (each week has 7 days) to travel. You can only take flights at most once per day and can only take 
	 *    flights on each week's Monday morning. Since flight time is so short, we don't consider the impact of flight time.
	 * 4. For each city, you can only have restricted vacation days in different weeks, given an N*K matrix called days representing this 
	 *    relationship. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
	 * 5. You're given the flights matrix and days matrix, and you need to output the maximum vacation days you could take during K weeks.
	 * 
	 * Example 1:
	 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
	 * Output: 12
	 * Explanation: 
	 * Ans = 6 + 3 + 3 = 12. 
	 * 
	 * One of the best strategies is:
	 * 1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day. 
	 * (Although you start at city 0, we could also fly to and start at other cities since it is Monday.) 
	 * 2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
	 * 3rd week : stay at city 2, and play 3 days and work 4 days.
	 * 
	 * Example 2:
	 * Input:flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
	 * Output: 3
	 * Explanation: 
	 * Ans = 1 + 1 + 1 = 3. 
	 * 
	 * Since there is no flights enable you to move to another city, you have to stay at city 0 for the whole 3 weeks. 
	 * For each week, you only have one day to play and six days to work. 
	 * So the maximum number of vacation days is 3.
	 * 
	 * Example 3:
	 * Input:flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
	 * Output: 21
	 * Explanation:
	 * Ans = 7 + 7 + 7 = 21
	 * 
	 * One of the best strategies is:
	 * 1st week : stay at city 0, and play 7 days. 
	 * 2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
	 * 3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
	 * 
	 * Note:
	 * 1. N and K are positive integers, which are in the range of [1, 100].
	 * 2. In the matrix flights, all the values are integers in the range of [0, 1].
	 * 3. In the matrix days, all the values are integers in the range [0, 7].
	 * 4. You could stay at a city beyond the number of vacation days, but you should work on the extra days, which won't be counted as 
	 *    vacation days.
	 * 5. If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will count towards 
	 *    the vacation days of city B in that week.
	 * 6. We don't consider the impact of flight hours towards the calculation of vacation days.
	 */
	
	// https://leetcode.com/problems/maximum-vacation-days/solution/
	// Solution 1: DFS, Burte - Force O(n^K) -- TLE
	// week(0)有n种选择，week(1)有n*n种选择 ... week(k)有n^k种选择
	/*
    public static int maxVacationDays(int[][] flights, int[][] days) {
        return helper(flights, days, 0, 0);
    }
    
    private static int helper(int[][] flights, int[][] days, int curCity, int weekNum) {
    	if (weekNum == days[0].length) {
    		return 0;
    	}
    	
    	int result = 0;
    	
    	for (int i = 0; i < flights.length; i++) {
    		int cur = 0;
    		if (flights[curCity][i] == 1 || i == curCity) {
    			cur = days[i][weekNum] + helper(flights, days, i, weekNum + 1);
    		}
    		
    		result = Math.max(result, cur);
    	}
    	
    	return result;
    }
    */
    
	// Solution 2: DFS  + mem
	// 上面solution中重复计算发生在，每次计算在city[i]度过week[j]时，都要计算一次city[i] week[j + 1]。 
	// 因此用mem将这个结果记录下来，复杂度可以降至O(n^2 * k) --> 对于每个week，花O(n * n)的时间去找该week的最大值，找k次

    public static int maxVacationDays(int[][] flights, int[][] days) {
    	Integer [][] cache = new Integer[flights.length][days[0].length];
        return helper(flights, days, 0, 0, cache);
    }
    
    private static int helper(int[][] flights, int[][] days, int curCity, int weekNum, Integer[][] cache) {
    	if (weekNum == days[0].length) {
    		return 0;
    	}
    	
    	if (cache[curCity][weekNum] != null) {
    		return cache[curCity][weekNum];
    	}
    	
    	int result = 0;
    	
    	for (int i = 0; i < flights.length; i++) {
    		int cur = 0;
    		if (flights[curCity][i] == 1 || i == curCity) {
    			cur = days[i][weekNum] + helper(flights, days, i, weekNum + 1, cache);
    		}
    		
    		result = Math.max(result, cur);
    	}
    	
    	cache[curCity][weekNum] = result;
    	return result;
    }

    // Solution 3: DP
	/*
	public static int maxVacationDays(int[][] flights, int[][] days) {
		int n = flights.length;
		int k = days[0].length;
		// dp[i][j] 从i-th城市开始，第k-th个week可以待的最多天数
		
		int[][] dp = new int[n][k];
		
		for (int week = k - 1; week >= 0; week--) {
			for (int curCity = 0; curCity < n; curCity++) {
				// 待在同一个城市
				dp[curCity][week] = days[curCity][week] + (week == k - 1 ? 0 : dp[curCity][week + 1]);
				// 飞到不同的城市
				for (int toCity = 0; toCity < n; toCity++) {
					if (flights[curCity][toCity] == 1) {
						dp[curCity][week] = Math.max(days[toCity][week] + (week == k - 1 ? 0 : dp[toCity][week + 1]), dp[curCity][week]);
					}
				}
			}
		}	
		return dp[0][0];
	}
    */
    public static void main(String[] args) {
    	int[][] flights1 = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
    	int[][] days1 = {{1, 3, 1}, {6, 0, 3}, {3, 3, 3}};
    	
    	int[][] flights2 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    	int[][] days2 = {{1, 1, 1}, {7, 7, 7}, {7, 7, 7}};
    	
    	int[][] flights3 = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
    	int[][] days3 = {{7, 0, 0}, {0, 7, 0}, {0, 0, 7}};
    	
    	System.out.println(maxVacationDays(flights1, days1));
    	System.out.println(maxVacationDays(flights2, days2));
    	System.out.println(maxVacationDays(flights3, days3));
	}
}
