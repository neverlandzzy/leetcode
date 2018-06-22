
public class Solution {
	/*
	 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the 
	 * form f(x) = ax^2 + bx + c to each element x in the array.
	 * 
	 * The returned array must be in sorted order.
	 * 
	 * Expected time complexity: O(n)
	 * 
	 * Example:
	 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
	 * 
	 * Result: [3, 9, 15, 33]
	 * 
	 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
	 * 
	 * Result: [-23, -5, 1, 7]
	 */
	
	// https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution
	// 只有两种情况： a > 0时中间最小，a < 0时中间最大，从两端向中间遍历即可，类似于merge sorted array
    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        
        int index = a > 0 ? n - 1 : 0;
        int i = 0;
        int j = n - 1;
        
        while (i <= j) {
        	if (a > 0) {
        		result[index--] = calc(a, b, c, nums[i]) > calc(a, b, c, nums[j]) ? calc(a, b, c, nums[i++]) : calc(a, b, c, nums[j--]);
        	} else {
        		result[index++] = calc(a, b, c, nums[i]) > calc(a, b, c, nums[j]) ? calc(a, b, c, nums[j--]) : calc(a, b, c, nums[i++]);
        	}
        }
        
        return result;
    }
    
    private static int calc(int a, int b, int c, int x) {
    	return a * x * x + b * x + c;
    }
    
    // 另一种写法，一样的思路
    /*
     public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        int start = 0;
        int end = n - 1;
        
        if (a > 0) {
            for (int i = n - 1; i >=  0; i--) {
                int left = calc(a, b, c, nums[start]);
                int right = calc(a, b, c, nums[end]);
                
                if (left > right) {
                    result[i] = left;
                    start++;
                } else {
                    result[i] = right;
                    end--;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                int left = calc(a, b, c, nums[start]);
                int right = calc(a, b, c, nums[end]);
                
                if (left < right) {
                    result[i] = left;
                    start++;
                } else {
                    result[i] = right;
                    end--;
                }
            }
        }
        
        return result;
    }
    
    private int calc(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }
    */
    public static void main(String[] args) {
		int[] test1 = {-4, -2, 2, 4};
		int[] result1 = sortTransformedArray(test1, 1, 3, 5);
		int[] result2 = sortTransformedArray(test1, -1, 3, 5);
		
		print(result1);
		print(result2);
	}
    
    private static void print(int[] nums) {
    	for (int i: nums) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    }
}
