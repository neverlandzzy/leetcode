import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated 
	 * to another number in the set, which results in repetition of one number and loss of another number.
	 * 
	 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then 
	 * find the number that is missing. Return them in the form of an array.
	 * 
	 * Example 1:
	 * Input: nums = [1,2,2,4]
	 * Output: [2,3]
	 * 
	 * Note:
	 * 	1.The given array size will in the range [2, 10000].
	 * 	2.The given array's numbers won't have any order.
	 */
	
	// Solution 1: Time: O(n), Space: O(n)
	/*
    public static int[] findErrorNums(int[] nums) {
    	int duplicate = 0;
    	int missing = 0;
    	
    	int n = nums.length;
    	int[] map = new int[n + 1];
        
    	for (int i: nums) {
    		map[i]++;
    	}
    	
    	for (int i = 1; i <= n; i++) {
    		if (map[i] == 0) {
    			missing = i;
    		}
    		
    		if (map[i] == 2) {
    			duplicate = i;
    		}
    	}
    	
    	return new int[] {duplicate, missing};
    }
    */
    // Solution 2: Time: O(n), Space: O(1)
    public static int[] findErrorNums(int[] nums) {
    	int duplicate = 0;
    	int missing = 0;
    	
    	for (int i: nums) {
    		if (nums[Math.abs(i) - 1] < 0) {
    			duplicate = Math.abs(i);
    		} else {
    			nums[Math.abs(i) - 1] *= -1;
    		}
    	}
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] > 0) {
    			missing = i + 1;
    		}
    	}
    	
    	return new int[]{duplicate, missing};
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 3};
		int[] result1 = findErrorNums(test1);
		print(result1);
	}
    
    private static void print(int[] arr) {
    	
    	for (int i: arr) {
    		System.out.print(i + ", ");
    	}
    	System.out.println();
    }
}
