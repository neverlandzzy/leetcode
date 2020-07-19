import java.util.HashMap;
import java.util.Map;


public class Solution {

	/**
	 * Given an array of integers, find two numbers such that they add up to a specific 
	 * target number. The function twoSum should return indices of the two numbers such 
	 * that they add up to the target, where index1 must be less than index2. Please note 
	 * that your returned answers (both index1 and index2) are not zero-based.
	 * 
	 * You may assume that each input would have exactly one solution.
	 * 
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2
	 */
	
	// Time: O(n), Space: O(n)
    public static int[] twoSum(int[] nums, int target) {
    	
        int[] result = new int[2];
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]) ;
            }
            map.put(nums[i], i);
        }
        
        return result;
        
    }
    
    public static void main(String[] args) {
    	
    	int[] numbers = {2,7,11,15};
    	int[] numbers2 = {3,2,4};
    	int target = 17;
    	int target2 = 6;
    	int [] index = new int[2];
    	
    	index = twoSum(numbers, target);
    	System.out.println(index[0]);
    	System.out.println(index[1]);
    	
    	index = twoSum(numbers2, target2);
    	System.out.println(index[0]);
    	System.out.println(index[1]);
    }
}
