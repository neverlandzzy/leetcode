import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Solution {
	/*
	 * Given an array of integers with possible duplicates, randomly output the index of a given target number. 
	 * You can assume that the given target number must exist in the array.
	 * 
	 * Note:
	 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
	 * 
	 * Example:
	 * 
	 * int[] nums = new int[] {1,2,3,3,3};
	 * Solution solution = new Solution(nums);
	 * 
	 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
	 * solution.pick(3);
	 * 
	 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
	 * solution.pick(1);
	 */
	
	
	// Solution 1: HashMap, very straightforward. O(n) space, O(n) init, O(1) pick
	/*
	Map<Integer, List<Integer>> map;
	
    public Solution(int[] nums) {
    	map = new HashMap<>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (!map.containsKey(nums[i])) {
    			map.put(nums[i], new ArrayList<>());
    		}
    		map.get(nums[i]).add(i);
    	}
    }
    
    public int pick(int target) {
        List<Integer> list = map.get(target);
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
    */
    // Solution 2: Reservoir Sampling(蓄水池算法) O(1) init, O(1) memory, but O(N) to pick. Good for data stream and big data set
	// 类似LC382
	// https://www.jianshu.com/p/63f6cf19923d
	
	int[] nums;
	
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int index = -1;
        int count = 1;
        Random random = new Random();
        
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == target) {
        		if (random.nextInt(count) == 0) {
        			index = i;
        		}
        		count++;
        	}
        }
        
        return index;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 3, 3};
		Solution solution = new Solution(test1);
		System.out.println(solution.pick(3));
		System.out.println(solution.pick(1));
	}
}
