import java.util.HashMap;


public class Solution {
	
    public static int maxProfit(int[] prices, int fee) {
    	if (prices == null || prices.length == 0) {
    		return 0;
    	}
    	int n = prices.length;
    	int min = prices[0];
        int[] dp = new int[n];
        
        for (int i = 1; i < n; i++) {
        	if (dp[i - 1] > prices[i] - min - fee + dp[i - 1]) {
        		dp[i] = dp[i - 1];       		
        	} else {
        		if (i < n - 1 && prices[i + 1] > prices[i]) {
        			continue;
        		}
        		dp[i] = prices[i] - min - fee + dp[i - 1];
        		min = prices[i];
        	}
        	min = Math.min(min, prices[i]);
        	//dp[i] = Math.max(dp[i - 1], prices[i] - min - fee);
        	System.out.println(min);
        }
        
        
        for (int k: dp) {
        	System.out.print(k + ", ");
        }

        System.out.println();
        
        return dp[n - 1];
        
    }
    
    
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int n = nums.length;
        int counter = 0;
        
        for (int i = 0; i < n; i++) {
        	int product = 1;
        	for (int j = i; j < n; j++) {
        		product *= nums[j];
        		if (product < k) {
        			counter++;
        		} else {
        			break;
        		}
        	}
        }        

        return counter;
    }
    

    
	public static void main(String[] args) {
		//int[] test1 = {1, 3, 7, 5, 10, 3};
		
		//System.out.println(maxProfit(test1, 3));
		int[] test2 = {10, 5, 2, 6};
		System.out.println(numSubarrayProductLessThanK(test2, 100));
	}
}
