
public class Solution {
	/*
	 * You are a professional robber planning to rob houses along a street. Each house has 
	 * a certain amount of money stashed, the only constraint stopping you from robbing each of 
	 * them is that adjacent houses have security system connected and it will automatically 
	 * contact the police if two adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of each house, 
	 * determine the maximum amount of money you can rob tonight without alerting the police.
	 */
	
    public static int rob(int[] nums) {
    	
    	 // Space: O(1)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
                
        if (n == 1) {
        	return nums[0];
        }
        
        int pre = nums[0];
        int cur = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < n; i++) {
            int tmp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = tmp;
        }
        
        return cur;
        
        // Space: O(n)
    	/*
        int len = nums.length;
        if (len == 0) {
        	return 0;
        }
        
        if (len == 1) {
        	return nums[0];
        }
        int[] maxProfit = new int[len];
        
        maxProfit[0] = nums[0];
        maxProfit[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < len; i++) {
        	maxProfit[i] = Math.max(maxProfit[i - 2] + nums[i], maxProfit[i - 1]);
        }
        
        return maxProfit[len - 1];
        */
    }
    
    public static void main(String[] args) {
		int[] money = {3, 5, 1, 10, 8, 7, 12, 5, 9};
		
		System.out.println(rob(money));
	}
}
