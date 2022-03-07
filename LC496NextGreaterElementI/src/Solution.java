import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Solution {
	/**
	 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
	 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
	 * 
	 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
	 * If it does not exist, output -1 for this number.
	 * 
	 * Example 1:
	 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	 * Output: [-1,3,-1]
	 * Explanation:
	 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
	 *     For number 1 in the first array, the next greater number for it in the second array is 3.
	 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
	 *     
	 * Example 2:
	 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
	 * Output: [3,-1]
	 * Explanation:
	 *     For number 2 in the first array, the next greater number for it in the second array is 3.
	 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
	 * Note:
	 * 	1. All elements in nums1 and nums2 are unique.
	 * 	2. The length of both nums1 and nums2 would not exceed 1000.
	 */
	
	// Time: O(m + n)
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num: nums2) {
        	while (!stack.isEmpty() && stack.peek() < num) {
        		map.put(stack.pop(), num);
        	}
        	stack.push(num);
        }

        
        int n = nums1.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
        	result[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
    
    private static void print(int[] nums) {
    	for (int n: nums) {
    		System.out.print(n + ", ");
    	}
    	
    	System.out.println();
    }
    
    public static void main(String[] args) {
		int[] test11 = {4, 1, 2};
		int[] test12 = {1, 3, 4, 2};
		int[] test21 = {2, 4};
		int[] test22 = {1, 2, 3, 4};
		
		int[] result1 = nextGreaterElement(test11, test12);
		int[] result2 = nextGreaterElement(test21, test22);
		
		print(result1);
		print(result2);
	}
}
