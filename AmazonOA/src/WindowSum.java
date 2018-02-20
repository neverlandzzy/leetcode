
public class WindowSum {
	
	/*
	 * Given an array of n integer, and a moving window(size k), move the window at 
	 * each iteration from the start of the array, find the sum of the element inside the 
	 * window at each moving.
	 * 
	 * Example
	 * For array [1,2,7,8,5], moving window size k = 3. 
	 * 1 + 2 + 7 = 10
	 * 2 + 7 + 8 = 17
	 * 7 + 8 + 5 = 20
	 * return [10,17,20]
	 */
	
    public static int[] winSum(int[] nums, int k) {
        // write your code here
    	if (nums == null || nums.length == 0) {
    		return new int[0];
    	}
    	
    	int[] result = new int[nums.length - k + 1]; 
	
    	int sum = 0;
    	int i = 0;
    	int pre = 0;
    	
    	for (; i < k; i++) {
    		sum += nums[i];
    	}
    	
    	result[0] = sum;

    	for (; i < nums.length; i++) {
    		sum = sum - nums[pre] + nums[i];
    		result[pre + 1] = sum;
    		pre++;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		int[] test = {1, 2, 7, 8, 5};
		
		int[] result = winSum(test, 3);
		
		for (int n: result) {
			System.out.print(n + ", ");
		}
	}
}
