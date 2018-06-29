import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Solution {
	/*
	 * Given an array of integers, find out whether there are two distinct indices i and j in 
	 * the array such that the difference between nums[i] and nums[j] is at most t and the 
	 * difference between i and j is at most k.
	 */
	
	// Solution 1: TreeSet O(nlogk)
	// 注意溢出，LC上solution给的解法在int[] test2 = {2147483647, 2147483647}; 时不对
	/*
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    	if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
    		return false;
    	}
    	
    	TreeSet<Long> set = new TreeSet<>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		Long floor = set.floor((long)nums[i] + t); // 最大的比nums[i] + t 小的数
    		Long ceiling = set.ceiling((long)nums[i] - t); // 最小的比nums[i] - t大的数
    		
    		if (floor != null && floor >= nums[i]) {
    			// floor <= nums[i] + t && floor >= nums[i] --> 0 < floor - nums[i] <= t
    			return true;
    		}
    		
    		if (ceiling != null && ceiling <= nums[i]) {
    			// ceiling >= nums[i] - t && ceiling <= nums[i] --> -t <= ceiling - nums[i] <= 0
    			return true;
    		}
    		
    		set.add((long)nums[i]);
    		
    		if (i >= k) {
    			set.remove((long)nums[i - k]);
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
    	
    	Map<Integer, Integer> map = new HashMap<>();

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
		int[] test1 = {-1, 2147483647};
		int[] test2 = {2147483647, 2147483647};
		//System.out.println(containsNearbyAlmostDuplicate(test1, 1, 2147483647));
		System.out.println(containsNearbyAlmostDuplicate(test2, 3, 3));
		//System.out.println(containsNearbyAlmostDuplicate(test, 3, 1));

	}
}
