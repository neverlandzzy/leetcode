import java.util.Arrays;


public class Solution {
	/*
	 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, 
	 * please find out a way you can make one square by using up all those matchsticks. You should not break any stick, 
	 * but you can link them up, and each matchstick must be used exactly one time.
	 * 
	 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, 
	 * to represent whether you could make one square using all the matchsticks the little match girl has.
	 * 
	 * Example 1:
	 * Input: [1,1,2,2,2]
	 * Output: true
	 * 
	 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
	 * 
	 * Example 2:
	 * Input: [3,3,3,3,4]
	 * Output: false
	 * 
	 * Explanation: You cannot find a way to form a square with all the matchsticks.
	 * 
	 * Note:
	 * 1. The length sum of the given matchsticks is in the range of 0 to 10^9.
	 * 2. The length of the given matchstick array will not exceed 15.
	 */
	
    public static boolean makesquare(int[] nums) {
    	if (nums == null || nums.length < 4) {
    		return false;
    	}
    
    	int target = 0;
    	
    	for (int n: nums) {
    		target += n;
    	}
    	
    	if (target % 4 != 0) {
    		return false;
    	} else {
    		target = target / 4;
    	}
    	
    	Arrays.sort(nums); //sort 后从大的边开始选，并不会提高worst case的时间复杂度，但在failure时会更早退出。
    	return helper(nums, new int[4], target, nums.length - 1);
    }
    
    private static boolean helper(int[] nums, int[] sides, int target, int index) {
    	if (index == 0) {
    		if (sides[0] == target && sides[1] == target && sides[2] == target) {
    			return true;
    		}
    		return false;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		if (sides[i] + nums[index] > target) {
    			continue;
    		}
    		sides[i] += nums[index];
    		if (helper(nums, sides, target, index - 1)) {
    			return true;
    		}
    		sides[i] -= nums[index];
    	}
    	return false;
    }
    
    public static void main(String[] args) {
		System.out.println(makesquare(new int[] {1,1,2,2,2}));
		
		System.out.println(makesquare(new int[] {3,3,3,3,4}));
	}
}
