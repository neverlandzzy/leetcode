
public class Solution {
	/*
	 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
	 * 
	 * Formally the function should:
	 * Return true if there exists i, j, k 
	 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
	 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
	 * 
	 * Examples:
	 * Given [1, 2, 3, 4, 5],
	 * return true.
	 * 
	 * Given [5, 4, 3, 2, 1],
	 * return false.
	 */
	
    public static boolean increasingTriplet(int[] nums) {
    	
    	if (nums == null || nums.length <= 2) {
    		return false;
    		
    	}
    	
    	int small = Integer.MAX_VALUE;
    	int large = Integer.MAX_VALUE;
    	
    	for (int i: nums) {
    		if (i <= small) {
    			small = i;
    		} else if (i <= large) {
    			large = i;
    		} else {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 4, 5};
		int[] test2 = {3, 1, 4, 2, 5};
		int[] test3 = {5, 4, 3, 2, 1};
		int[] test4 = {1, 0, 0, 1};
		
		System.out.println(increasingTriplet(test1));
		System.out.println(increasingTriplet(test2));
		System.out.println(increasingTriplet(test3));
		System.out.println(increasingTriplet(test4));
	}
}
