package oa;

import java.util.Scanner;

public class OA8 {
	/*
	 * 【题目描述】https://drive.google.com/file/d/0B9Fj5-El2B6lRXUweGFPR290NUU/view (截屏)
	 *  You are given an array A consisting of N non-negative integers;
	 *  You are give a threshold T;
	 *  Your task is to calculate the maximum sum of consecutive elements in A which doesn't exceed T;
	 *  
	 *  maximum sum：给一个数组（都是正数），给一个边界值，找到一个最大的subarray的sum，且这个sum不能大于边界值。Two Pointers即可。O(n)
	 *  
	 * 【代码】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=193901
	 */
	
	// T: O(n), S: O(1)
	private static int maxSum(int[]nums, int T) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int n = nums.length;
		int start = 0;
		int end = 0;
		int max = 0;
		int sum = 0;
		
		while (end < n) {
			if (sum == T) {
				return sum;
			}
			
			if (sum < T) {
				max = Math.max(max, sum);
				sum += nums[end];
				end++;
			} else {
				while (sum > T) {
					sum -= nums[start];
					start++;
				}
				max = Math.max(max, sum);
			}
		}
		
		while (sum > T) {
			sum -= nums[start];
			start++;
		}
		
		max = Math.max(max, sum);
		return max;
	}
	
	
	public static void main(String[] args) {
		/*
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int T = in.nextInt();
		int[] nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}
		in.close();
		
		System.out.println(maxSum(nums, T));
		*/
		
		int[] test1 = {3, 3, 5, 1, 7};
		int[] test2 = {4, 8, 2};
		
		System.out.println(maxSum(test1, 10)); // 9
		System.out.println(maxSum(test2, 1));  // 0
	}

}
