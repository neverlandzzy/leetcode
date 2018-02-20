import java.util.Arrays;
import java.util.Comparator;


public class Solution {
	/*
	 * An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.
	 * 
	 * Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.
	 * 
	 * Example 1:
	 * Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
	 * Output: 3
	 * Explanation:
	 * Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
	 * Also, there isn't a smaller size set that fulfills the above condition.
	 * Thus, we output the size of this set, which is 3.
	 * 
	 * Example 2:
	 * Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
	 * Output: 5
	 * Explanation:
	 * An example of a minimum sized set is {1, 2, 3, 4, 5}.
	 * 
	 * Note:
	 * 1. intervals will have length in range [1, 3000].
	 * 2. intervals[i] will have length 2, representing some integer interval.
	 * 3. intervals[i][j] will be an integer in [0, 10^8].
	 */
	
	// https://discuss.leetcode.com/topic/115540/java-o-nlogn-solution-greedy/3
    public static int intersectionSizeTwo(int[][] intervals) {
    	// 将intervals按终点排序
    	Arrays.sort(intervals, new Comparator<int[]>(){
    		public int compare(int[] o1, int[] o2) {
    			return o1[1] - o2[1];
    		}
    	});
    	
    	int result = 0;
    	int max1 = -1; 
    	int max2 = -1;
    	
    	// 比较下一个interval起点和上一个interval终点的重叠关系
    	for (int[] interval: intervals) {
    		int start = interval[0];
    		int end = interval[1];
    		
    		if (start > max1) {
    			// 下一个interval的起点和上一个终点不重叠
    			result += 2;
    			max1 = end;
    			max2 = end - 1;
    		} else if (start > max2) {
    			// 下一个interval的起点和上一个终点重叠
    			result++;
    			// e.g. test4
    			max2 = max1 == end ? max1 - 1 : max1;
    			max1 = end;
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	int[][] test1 = {{1, 3}, {1, 4}, {2, 5}, {3, 5}};
    	int[][] test2 = {{1, 2}, {2, 3}, {2, 4}, {4, 5}};
    	int[][] test3 = {{1, 2}, {8, 9}};
    	//int[][] test4 = {{1, 2}, {2, 4}, {3, 4}, {4, 5}};
    	System.out.println(intersectionSizeTwo(test1));
    	System.out.println(intersectionSizeTwo(test2));
    	System.out.println(intersectionSizeTwo(test3));
    	//System.out.println(intersectionSizeTwo(test4));
	}
}
