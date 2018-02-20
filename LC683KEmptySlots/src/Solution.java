
public class Solution {
	/*
	 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. 
	 * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
	 * 
	 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
	 * 
	 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range 
	 * from 1 to N.
	 * 
	 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers 
	 * between them is k and these flowers are not blooming.
	 * 
	 * If there isn't such day, output -1.
	 * 
	 * Example 1:
	 * Input: 
	 * flowers: [1,3,2]
	 * k: 1
	 * Output: 2
	 * Explanation: In the second day, the first and the third flower have become blooming.
	 * 
	 * Example 2:
	 * Input: 
	 * flowers: [1,2,3]
	 * k: 1
	 * Output: -1
	 * 
	 * Note:
	 * The given array will be in the range [1, 20000].
	 */
	
	// https://discuss.leetcode.com/topic/104771/java-c-simple-o-n-solution
	
    public static int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        
        // days[i] is the blooming day of the flower in position i + 1.
        // flowers [1, 3, 2] --> at day 1, 2, 3, the flower at 1, 3, 2 blooms;
        // days[1, 3, 2] --> the flower at 1, 3, 2 blooms at day 1, 2, 3;
        // the question is to find a window of size k on days[], [left, i, ..., i + k - 1, right], 
        // where days[left] and days[right] < days[i, ..., i + k - 1] 
        // ==> 意味着： 在第max(left, right)天， 只有days[left] 和 days[right]开了，中间k个花都在第max(left, right)天之后才开
        // 当 (days[i] <= days[left] || days[i] <= days[right])时，有两种情况：
        //  (1) 找到window 不满足条件，因此update window边界；
        //  (2) days[i] == days[right]时， 已经找到size 为k的边界，因此更新结果
        
        int[] days = new int[n];
        
        for (int i = 0; i < n; i++) {
        	days[flowers[i] - 1] = i + 1;
        
        }
        
        /*
        for (int i: days) {
        	System.out.print(i + ", ");
        }
        System.out.println();
        
        for (int i: flowers) {
        	System.out.print(i + ", ");
        }
        System.out.println();
        */
        int left = 0;
        int right = k + 1;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; right < n; i++){
        	
        	// days[i] <= days[left] 或者days[i] < days[left]都可以，==的情况是i和left重合，即开始时的条件，边界并不会被更新
            if (days[i] <= days[left] || days[i] <= days[right]) {
                if (i == right) {
                	// 如果有多个结果，返回最小的（最早的日期） -- 题目描述没有说，但test case会报错(test3)
                	result = Math.min(result, Math.max(days[left], days[right]));
                }                              
                left = i; 
                right = k + 1 + i;
            }
        }
        
        return (result == Integer.MAX_VALUE) ? -1 : result;
    
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 3, 2};
		int[] test2 = {1, 2, 3};
		int[] test3 = {10,1,6,4,2,8,9,7,5,3};
		
		System.out.println(kEmptySlots(test1, 1));
		System.out.println(kEmptySlots(test2, 1));
		System.out.println(kEmptySlots(test3, 1));
	}
}
