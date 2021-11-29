
public class Solution {
	/**
	 * Find the contiguous subarray within an array (containing at least one number) which 
	 * has the largest sum.
	 * 
	 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
	 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
	 * 
	 */
	
	// Solution 1: DP, Time: O(n), Space: O(n)
	/*
    public int maxSubArray(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int[] sum = new int[n]; // sum[i] -- maxSubArray ending with i;
        
        sum[0] = nums[0];
        int max = nums[0];
        
        for (int i = 1; i < n; i++) {
            sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
            max = Math.max(max, sum[i]);
        }
        
        return max;
    }
    */
	// Solution 2: DP, Time: O(n), Space: O(1)
	/*
    public static int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int sum = nums[0];
        int max = nums[0];

        for (int i = 1; i < n; i++) {
            sum = Math.max(0, sum) + nums[i];
            max = Math.max(max, sum);
        }

        return max;
    }
    */
	
	// Solution 3: Divide-and-conquer  Time: O(nlogn)
    /*
     * Step1. Select the middle element of the array.
     * So the maximum subarray may contain that middle element or not.
     * 
     * Step 2.1 If the maximum subarray does not contain the middle element, then we can apply the same algorithm to 
     * the the subarray to the left of the middle element and the subarray to the right of the middle element.
     * 
     * Step 2.2 If the maximum subarray does contain the middle element, then the result will be simply the maximum 
     * suffix subarray of the left subarray plus the maximum prefix subarray of the right subarray
     * 
     * Step 3 return the maximum of those three answer.
     */
    public static int helper(int left, int right, int[] A){

    	if (left == right) {
    		return A[left];
    	} 
    	
    	int middle = (left+right)/2;
    	
    	int leftAns = helper(left, middle, A);     // 2.1 如果不包括middle, 则在左右两个子集里找
    	int rightAns = helper(middle+1, right, A);
    	
    	int leftMax = A[middle];    // 2.2 如果包括middle， 则从middle分别向左向右找到最大和：leftMax + rightMax
    	int rightMax = A[middle+1];

    	int tmp = 0;
    	
    	for (int i = middle; i >= left; i--) {
    		tmp = A[i]+tmp;
    		if(tmp > leftMax) {
    			leftMax = tmp;
    		}
    		
    	}
    	
    	tmp = 0;
    	for (int i = middle+1; i <= right; i++) {
    		tmp = A[i] + tmp;
    		if (tmp > rightMax) {
    			rightMax = tmp;
    		}
    	}

    	
    	return Math.max(Math.max(leftAns, rightAns), leftMax+rightMax);
    	
    	
    }
    
    public static int maxSubArray(int[] nums) {
    	
    	return helper(0,nums.length-1, nums);
    }
    
    
    public static void main(String[] args) {
		int[] test = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(test));
	}
}
