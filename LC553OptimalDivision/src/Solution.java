import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
	 * 
	 * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add 
	 * parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain 
	 * redundant parenthesis.
	 * 
	 * Example:
	 * Input: [1000,100,10,2]
	 * Output: "1000/(100/10/2)"
	 * Explanation:
	 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
	 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
	 * since they don't influence the operation priority. So you should return "1000/(100/10/2)". 
	 * 
	 * Other cases:
	 * 1000/(100/10)/2 = 50
	 * 1000/(100/(10/2)) = 50
	 * 1000/100/10/2 = 0.5
	 * 1000/100/(10/2) = 2
	 * 
	 * Note:
	 * 
	 * 1. The length of the input array is [1, 10].
	 * 2. Elements in the given array will be in range [2, 1000].
	 * 3. There is only one optimal division for each test case.
	 */
	
	// O(n), 1/x单调递减，所以被除数越小，结果越大，所以除了第一个数以外，其它的数相除之后作为被除数即可获得最大结果
	/*
    public static String optimalDivision(int[] nums) {
    	if (nums.length == 1) {
    		return String.valueOf(nums[0]);
    	}
    	
    	if (nums.length == 2) {
    		return "" +  nums[0] + "/" + nums[1];
    	}
    	
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(");
        for (int i = 1; i < nums.length; i++) {
        	sb.append(nums[i]).append("/");
        }
        
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        
        return sb.toString();
    }
    */
	
	// O(n^3), 常规 DFS + mem
	// https://leetcode.com/problems/optimal-division/discuss/101684/Brute-force-with-memory-in-case-of-your-interviewer-forbid-tricky-solution
	
	static class Pair {
		double min;
		double max;
		String minS;
		String maxS;
		
		public Pair(double min, double max, String minS, String maxS) {
	        this.min = min;
	        this.minS = minS;
	        this.max = max;
	        this.maxS = maxS;
		}
	}
		
	public static String optimalDivision(int[] nums) {
		Map<String, Pair> map = new HashMap<>();
		Pair result = helper(nums, 0, nums.length - 1, map);
		return result.maxS;
	}
	
	private static Pair helper(int[] nums, int start, int end, Map<String, Pair> map) {
		String key = start + " " + end;
		if (map.containsKey(key)) {
			return map.get(key);
		}
		
		if (start == end) {
			return new Pair(nums[start], nums[start], "" + nums[start], "" + nums[start]);
		}
		
		Pair result = new Pair(0, 0, "", "");
		
		for (int i = start; i < end; i++) {
			Pair left = helper(nums, start, i, map);
			Pair right = helper(nums, i + 1, end, map);
			
			double min = left.min / right.max;
			String minS = left.minS + "/" + (i + 1 == end ? right.maxS : "(" + right.maxS + ")");
			double max = left.max / right.min;
			String maxS = left.maxS + "/" + (i + 1 == end ? right.minS : "(" + right.minS + ")");
			System.out.println("left max = " + left.max + " left min = " + left.min);
			if (max > result.max) {
				result.max = max;
				result.maxS = maxS;
			}
			
			if (result.min == 0 || min < result.min) {
				result.min = min;
				result.minS = minS;
			}
		}
		
		map.put(key, result);
		return result;
	}
	
    public static void main(String[] args) {
		int[] test1 = {1000, 100, 10, 2};
		System.out.println(optimalDivision(test1));
	}
}
