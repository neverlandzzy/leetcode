
public class Solution {
	/*
	 * Given an array of n positive integers and a positive integer s, find the minimal 
	 * length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	 * 
	 * For example, given the array [2,3,1,2,4,3] and s = 7,
	 * the subarray [4,3] has the minimal length under the problem constraint.
	 */
	
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
        while (j < nums.length) {
            sum += nums[j];
            
            while (i <= j && sum >= s) {
                min = Math.min(min, j - i + 1);
                sum -= nums[i];
                i++;
            }           
            
            j++;
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
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
    */
    
    public static void main(String[] args) {
		int[] test = {2,3,1,2,4,3};
		
		System.out.println(minSubArrayLen(7, test));
	}
}
