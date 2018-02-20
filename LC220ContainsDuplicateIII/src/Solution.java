import java.util.HashMap;


public class Solution {
	/*
	 * Given an array of integers, find out whether there are two distinct indices i and j in 
	 * the array such that the difference between nums[i] and nums[j] is at most t and the 
	 * difference between i and j is at most k.
	 */
	
	// Solution 1: TreeSet O(nlogk)
	/*
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
        	if ((set.floor(nums[i]) != null && set.floor(nums[i]) >= nums[i] - t) ||(set.ceiling(nums[i])
        			!= null && set.ceiling(nums[i]) <= nums[i] + t)) {
        				return true;
        	} else {
        		set.add(nums[i]);
        		if (i >= k) {
        			set.remove(nums[i - k]);
        		}        	
        	}
        }
        return false;
    }
    */
	
    // Solution 2: Bucket O(n)
    
    private static int getBucketID(int i, int t) {
    	return (i < 0) ? (i + 1) / t - 1 : i / t;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	
    	if (k < 1 || t < 0) return false;
    	t++;
    	
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    	for (int i = 0; i < nums.length; i++) {
     		
    		if (i > k) {
    			map.remove(getBucketID(nums[i - k - 1], t));
    		}
    		
    		int id = getBucketID(nums[i], t);
    		
    		if (map.containsKey(id)) return true;
    		if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < t) return true;
    		if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < t) return true;
   
    			
    		map.put(id, nums[i]);
    		
    	}
    	
    	return false;
    }
   


    public static void main(String[] args) {
		int[] test = {-1, 2147483647};
		
		System.out.println(containsNearbyAlmostDuplicate(test, 1, 2147483647));
		//System.out.println(containsNearbyAlmostDuplicate(test, 5, 1));
		//System.out.println(containsNearbyAlmostDuplicate(test, 3, 1));

	}
}
