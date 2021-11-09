
public class Solution {
	/**
	 * Given an array of n positive integers and a positive integer s, find the minimal 
	 * length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	 * 
	 * For example, given the array [2,3,1,2,4,3] and s = 7,
	 * the subarray [4,3] has the minimal length under the problem constraint.
	 */
	
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int i = 0;
        int j = 0;
        int result = n + 1;
        int sum = 0;
        
        while (j < n) {
            sum += nums[j];
            
            while (sum >= target) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i];
                i++;
            }

            j++;
        }
        
        return result == n + 1 ? 0 : result;
    }
	
    /*
    public static int minSubArrayLen(int s, int[] nums) {
        
    	int prev = 0, sum = 0, len = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < nums.length; i++) {
    		sum += nums[i];
    		
    		while (sum >= s) {
    			len = Math.min(len, i - prev + 1);
    			sum -= nums[prev++];
    		}
    	}
    	
    	return len == Integer.MAX_VALUE ? 0: len;
    }
    
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int i = 0;
        int j = 0;
        int result = n + 1;
        int sum = 0;
        
        while (j < n) {
            sum += nums[j];
            
            if (sum >= s) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i];
                sum -= nums[j];
                i++; 
            } else {
                j++;
            }
        }
        
        return result == n + 1 ? 0 : result;
    }
    */
    
    public static void main(String[] args) {
		int[] test = {2,3,1,2,4,3};
		
		System.out.println(minSubArrayLen(7, test));
	}
}
