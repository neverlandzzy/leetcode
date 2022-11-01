import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Solution {
	/**
	 * Given an array of integers, find out whether there are two distinct indices i and j in 
	 * the array such that the difference between nums[i] and nums[j] is at most t and the 
	 * difference between i and j is at most k.
	 */
	
	// Solution 1: TreeSet O(nlogk)
	// 注意溢出，LC上solution给的解法在int[] test2 = {2147483647, 2147483647}; 时不对
	/*
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
    	if (nums == null || nums.length == 0 || indexDiff <= 0 || valueDiff < 0) {
    		return false;
    	}
    	
    	TreeSet<Long> set = new TreeSet<>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		Long floor = set.floor((long)nums[i]); // 最大的比nums[i]小的数
    		Long ceiling = set.ceiling((long)nums[i]); // 最小的比nums[i]大的数
    		
            if (floor != null && nums[i] - floor <= valueDiff ) {
                return true;
            }

            if (ceiling != null && ceiling - nums[i] <= valueDiff) {
                return true;
            }
    		
    		set.add((long)nums[i]);
    		
    		if (i >= indexDiff) {
    			set.remove((long)nums[i - indexDiff]);
    		}
    	}
    	
    	return false;
    }
    */
	
    // Solution 2: Bucket O(n)
    
    private static int getBucketID(int i, int valueDiff) {
    	return (i < 0) ? (i + 1) / valueDiff - 1 : i / valueDiff;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
    	
    	if (indexDiff < 1 || valueDiff < 0) return false;
		valueDiff++;
    	
    	Map<Integer, Integer> map = new HashMap<>();

    	for (int i = 0; i < nums.length; i++) {
     		
    		if (i > indexDiff) {
    			map.remove(getBucketID(nums[i - indexDiff - 1], valueDiff));
    		}
    		
    		int id = getBucketID(nums[i], valueDiff);
    		
    		if (map.containsKey(id)) return true;
    		// 每个bucket(相同id)里只需要保存一个数，不用担心之前的数被overwrite，因为之前的数anyway都不满足条件
			// 否则之前就可以return true
    		if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < valueDiff) return true;
    		if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < valueDiff) return true;
    			
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
