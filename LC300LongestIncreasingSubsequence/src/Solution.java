import java.util.Arrays;


public class Solution {
	/*
	 * Given an unsorted array of integers, find the length of longest increasing subsequence.
	 * 
	 * For example,
	 * 
	 * Given [10, 9, 2, 5, 3, 7, 101, 18],
	 * 
	 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
	 * Note that there may be more than one LIS combination, it is only necessary for you to 
	 * return the length.
	 * 
	 * Your algorithm should run in O(n2) complexity.
	 * Follow up: Could you improve it to O(n log n) time complexity?
	 */
	
	/*
    public static int lengthOfLIS(int[] nums) {
    	int n = nums.length;
    	int[] dp = new int[n];
    	int len = 0;
    	
    	for (int m: nums) {
    		int i = Arrays.binarySearch(dp, 0, len, m);

    		if (i < 0) {
    			i = -(i + 1);
    		}
    		dp[i] = m;
    		if (i == len) {
    			len++;
    		}
    	}
    	
    	return len;

    }
    */
    
	// DP + Binary  O(nlogn) 
    public static int lengthOfLIS(int[] nums) {
    	int n = nums.length;
    	int[] dp = new int[n];
    	
    	// dp[] 用来存放当前的increasing subsequence
    	int len = 0;
    	
    	for (int m: nums) {
    		int i = 0;
    		int j = len;
    		
    		// 对每个m， 在当前的dp[]中，用binary search找到其位置 i。
    		// 若i == len， 说明m比当前dp[]中所有的元素都大，所以加在最后并把len++
    		// 最后len就是最大increasing subsequence的长度
    		while (i < j) {
    			int mid = i + (j - i) / 2;
    			if (dp[mid] < m) {
    				i = mid + 1;
    			} else {
    				j = mid;
    			}
    		}
    		
    		dp[i] = m;
    		if (i == len) {
    			len++;
    		}
    		/*
    		for (int k: dp) {
    			System.out.print(k + ", ");
    		}
    		System.out.println();
    		System.out.println("len =  " + len);
    		*/
    	}
    	
    	return len;
    	
    }
    
    public static void main(String[] args) {
    	
		int[] test = {9, 10, 2, 5, 3, 7, 101, 4};
		System.out.println(lengthOfLIS(test));

    	    	
	}
}
