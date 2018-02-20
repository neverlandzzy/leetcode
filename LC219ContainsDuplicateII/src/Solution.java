import java.util.HashMap;


public class Solution {
	/*
	 * Given an array of integers and an integer k, find out whether there are two distinct indices 
	 * i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
	 */
	
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map  = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
        	if (!map.containsKey(nums[i])) {
        		map.put(nums[i], i);
        	} else {
        		if (i - map.get(nums[i]) <= k) {
        			return true;
        		} else {
        			map.put(nums[i], i);
        		}
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
