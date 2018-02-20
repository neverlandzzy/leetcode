import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: 
	 * Si % Sj = 0 or Sj % Si = 0.
	 * 
	 * If there are multiple solutions, return any subset is fine.
	 * 
	 * Example 1:
	 * 
	 * nums: [1,2,3]
	 * 
	 * Result: [1,2] (of course, [1,3] will also be ok)
	 * 
	 * Example 2:
	 * 
	 * nums: [1,2,4,8]
	 * 
	 * Result: [1,2,4,8]
	 */
	
	// https://discuss.leetcode.com/topic/49741/easy-understood-java-dp-solution-in-28ms-with-o-n-2-time
	// https://www.jiuzhang.com/solution/largest-divisible-subset/
	
    public static List<Integer> largestDivisibleSubset(int[] nums) {
    	List<Integer> result = new ArrayList<>();
    	
    	if (nums == null || nums.length == 0) {
    		return result;
    	}
    	
        Arrays.sort(nums);
        int[] d = new int[nums.length];
        int[] pre = new int[nums.length];
        
        //for each element in nums, find the length of largest subset it has.
        for (int i = 0; i < nums.length; i++) {
        	d[i] = 1;
        	pre[i] = i;
        	
        	for (int j = 0; j < i; j++) {
        		if (nums[i] % nums[j] == 0 && d[i] < d[j] + 1) {
        			d[i] = d[j] + 1;
        			pre[i] = j;
        		}
        	}
        }   
        
        int max = 0;
        int maxIndex = 0;
        
        //pick the index of the largest element in dp.
        for (int i = 0; i < nums.length; i++) {
        	if (d[i] > max) {
        		max = d[i];
        		maxIndex = i;
        	}
        }

        result.add(nums[maxIndex]);
        // from nums[maxIndex] to 0, add every element belongs to the largest subset.
        
        // pre[] 里装的是当前元素的前一个可以整除元素的index 
        //（e.g. 1, 2, 4, 8的pre是0， 0， 1， 2, 即8是从4（index = 2）推过来的， 这样可以从最大的元素一直找到最长序列的头）
        while (maxIndex != pre[maxIndex]) {
        	maxIndex = pre[maxIndex];
        	result.add(nums[maxIndex]);
        }
        
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {2, 4, 9, 1};
		
		System.out.println(largestDivisibleSubset(test));
	}
}
