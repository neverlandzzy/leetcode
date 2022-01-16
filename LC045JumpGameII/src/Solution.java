
public class Solution {
	/**
	 * Given an array of non-negative integers, you are initially positioned at the first 
	 * index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that position.
	 * Your goal is to reach the last index in the minimum number of jumps.
	 * 
	 * For example:
	 * Given array A = [2,3,1,1,4]
	 * 
	 * The minimum number of jumps to reach the last index is 2. 
	 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
	 * 
	 */
	
	// 用max 记录在每一点上能跳到的最远的位置，并将next更新为max。当max == i时，说明没法到达终点。当next == i时，说明已经到了之前点能到达的最远距离，
	// 因此要从这个点再跳一次，即jump + 1

    public static int jump(int[] nums) {
        int next = 0;
    	int max = 0;
    	int jump = 0;
    	int n = nums.length;
        
    	for (int i = 0; i < n; i++) {
    		if (next >= n - 1) {
                return jump;
            }
            
            if (max < i) {
                return 0;
            }
            
            max = Math.max(max, i + nums[i]);
            
            if (next == i) {                           
                jump++;
                next = max;
            }
    	}
    	
    	return jump;
    }
    
    public static void main(String[] args) {
    	int[] test1 = {2,3,1,1,4};
    	int[] test2 = {3,2,1,0,4};
    	int[] test3 = {2,0,0};
    	int[] test4 = {1,4,5,1,1,1,1};
    	int[] test5 = {2,1};
    	int[] test6 = {1};
    	
		System.out.println(jump(test1));
		System.out.println(jump(test2));
		System.out.println(jump(test3));
		System.out.println(jump(test4));
		System.out.println(jump(test5));
    	System.out.println(jump(test6));
	}
}
