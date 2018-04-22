import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given an array of integers and an integer k, find out whether there are two distinct indices 
	 * i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
	 */
	
	// Solution 1: Time: O(n), Space: O(n)
	/*
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            
            map.put(nums[i], i);
        }
        
        return false;
    }
    */
    // Solution 2: Time:O(n), Space: O(min(k, n)) -- better!
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
    	Set<Integer> set = new HashSet<>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (set.contains(nums[i])) {
    			return true;
    		}
            set.add(nums[i]);
    		if (set.size() > k) {
    			set.remove(nums[i - k]);
    		}
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
		int[] test = {1,0,1,1};
		System.out.println(containsNearbyDuplicate(test, 1));
		System.out.println(containsNearbyDuplicate(test, 5));
		
	}
}
