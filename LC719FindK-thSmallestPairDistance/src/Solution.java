import java.util.Arrays;


public class Solution {
	/*
	 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined 
	 * as the absolute difference between A and B.
	 * 
	 * Example 1:
	 * Input:
	 * nums = [1,3,1]
	 * k = 1
	 * Output: 0 
	 * 
	 * Explanation:
	 * Here are all the pairs:
	 * (1,3) -> 2
	 * (1,1) -> 0
	 * (3,1) -> 2
	 * 
	 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
	 * Note:
	 * 2 <= len(nums) <= 10000.
	 * 0 <= nums[i] < 1000000.
	 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
	 */
	
	// https://leetcode.com/problems/find-k-th-smallest-pair-distance/solution/
	// O(n * log n + n * log W) (W: 数组元素值的范围(nums[n - 1] - nums[0])) 
	// log W来自于binary search。 binary search的每一步，有O(n) 步找最小left
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int n = nums.length;
        
        // 答案肯定在0和首位元素的差之间
        int start = 0;
        int end = nums[n - 1] - nums[0];
        
        while (start < end) {
        	int mid = start + (end - start) / 2;
        	int count = 0;
        	int left = 0;
        	
        	for (int right = 0; right < n; right++) {
        		// 对于找到当前right，使得nums[right] - nums[left] <= mid(guess)的最小left
        		while (nums[right] - nums[left] > mid) {
        			left++;
        		}
        		// 对于当前的right，一共有多少对元素的差值 <= mid(guess)
        		count += right - left;
        	}
        	
        	// 当count >= k时，说明有多于k对元素的差值 <= mid, mid偏大，因此end = mid
        	// 否则，少于k对元素的差值 <= mid，mid偏小，因此start = mid + 1;
        	if (count >= k) {
        		end = mid;
        	} else {
        		start = mid + 1;
        	}       			
        }
        return start;
    }
    
    public static void main(String[] args) {
		int[] test = {1, 3, 1};
		
		System.out.println(smallestDistancePair(test, 1));
	}
}
