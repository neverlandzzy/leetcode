
public class Solution {
	/*
	 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait 
	 * until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
	 * 
	 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
	 * 
	 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
	 */
	
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
        	return temperatures;
        }
        
        int n = temperatures.length;
        int[] result = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
        	int counter = 1;
        	for (int j = i + 1; j < n; j++) {
        		if (temperatures[j] <= temperatures[i]) {
        			counter++;
        		} else {
        			result[i] = counter;
        			break;
        		}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
	    int[] result = dailyTemperatures(test);
	    for (int i: result) {
	    	System.out.print(i + ", ");
	    }
	    System.out.println();
	}
}
