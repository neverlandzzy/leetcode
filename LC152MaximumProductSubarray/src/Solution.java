
public class Solution {
	/*
	 * Find the contiguous subarray within an array (containing at least one number) 
	 * which has the largest product.
	 * 
	 * For example, given the array [2,3,-2,4],
	 * the contiguous subarray [2,3] has the largest product = 6.
	 * Subscribe to see which companies asked this question
	 */
    public static int maxProduct(int[] nums) {
        
    	int n = nums.length;
    	
    	int maxProd = 1;
    	int minProd = 1;
    	int max = Integer.MIN_VALUE;
    	
    	// 求最小值
    	//int min = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < n; i++) {
    		int tmp = maxProd;
    		maxProd = Math.max(Math.max(maxProd * nums[i], nums[i]), minProd * nums[i]);
    		minProd = Math.min(Math.min(minProd * nums[i], nums[i]), tmp * nums[i]);
    		
    		max = Math.max(max, maxProd);
    		//min = Math.min(min, minProd);
    	}
    	
    	return max;
    }
    
    // Space O(n)
    /*
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        if (n == 1) {
            return nums[0];
        }
        
        int max = nums[0];
    
        int[] maxProd = new int[n];
        int[] minProd = new int[n];
        
        maxProd[0] = nums[0];
        minProd[0] = nums[0];
        
        for (int i = 1; i < n; i++) {
            maxProd[i] = Math.max(Math.max(nums[i], nums[i] * maxProd[i - 1]), nums[i] * minProd[i - 1]);
            minProd[i] = Math.min(Math.min(nums[i], nums[i] * maxProd[i - 1]), nums[i] * minProd[i - 1]);
            max = Math.max(max, maxProd[i]);
        }
        
        return max;
    }
    */
    public static void main(String[] args) {
		int[] test = {2,4,-6,3};
		int[] test2 = {0, 0, -1, 2};
		int[] test3 = {-4, 2, -1, -3};
		
		System.out.println(maxProduct(test));
		System.out.println(maxProduct(test2));
		System.out.println(maxProduct(test3));
	}
}
