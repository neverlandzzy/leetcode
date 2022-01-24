import java.util.Arrays;


public class Solution {
	/**
	 * Given an unsorted array of integers, find the number of longest increasing subsequence.
	 * 
	 * Example 1:
	 * Input: [1,3,5,4,7]
	 * Output: 2
	 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
	 * 
	 * Example 2:
	 * Input: [2,2,2,2,2]
	 * Output: 5
	 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
	 * 
	 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
	 */
	
	// https://leetcode.com/problems/number-of-longest-increasing-subsequence/solution/
	// http://www.cnblogs.com/grandyang/p/7603903.html
	
    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int n = nums.length;
        // length of longest subsequence ending in nums[i];
        int[] length = new int[n];
        // counts of longest subsequence ending in nums[i];
        int[] count = new int[n];
        Arrays.fill(count, 1);
     
        int max = 0;
        int result = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < i; j++) {
        		if (nums[j] < nums[i]) {
        			if (length[j] >= length[i]) {
        				// 找到一条新的更长的子序列，1,3,5 -> 1, 3, 5, 7 因此需要更新count[i]和length[i]
        				length[i] = length[j] + 1;
        				count[i] = count[j];
        			} else if (length[i] == length[j] + 1) {
        				// 找到1，3，4，7，发现1,3,4,7的length和已经找到的最长length(1, 3, 5, 7)一样，因此不用更新length[i]，并且把1,3,4,7的count加到当前的count中去
        				count[i] += count[j];
        			}
        		}
        	}
			max = Math.max(max, length[i]);
		}
        
        for (int i = 0; i < n; i++) {
        	if (length[i] == max) {
        		result += count[i];
        	}
        }
        
        return result;
    }
    
   
    public static void main(String[] args) {
		int[] test1 = {1, 3, 5, 4, 7};
		int[] test2 = {2, 2, 2, 2, 2};
		
		System.out.println(findNumberOfLIS(test1));
		//System.out.println(findNumberOfLIS(test2));
	}
}
