import java.util.Stack;

public class Solution {
	/**
	 * Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait 
	 * until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
	 * 
	 * For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
	 * 
	 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
	 */

	// Similar to LC901
	// Solution 1: Array - Time: O(n), Space: O(1)
	public static int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) {
			return temperatures;
		}

		int n = temperatures.length;
		int[] result = new int[n];
		int hottest = 0;

		for (int i = n - 1; i >= 0; i--) {
			int temperature = temperatures[i];
			if (temperature >= hottest) {
				hottest = temperature;
			} else {
				// 当目前的温度不是最高温度时，向后找最近的更高温度
				// 从 temperatures[i+1]开始找，如果不是，继续向后找，但不用找i+2，而是可以直接调到i + result[i + 1]
				// 因为result[i + 1]是下一个比temperatures[i+1] 更高的温度

				int days = 1;
				while (temperatures[i + days] <= temperature) {
					days += result[i + days];
				}

				result[i] = days;
			}
		}

		return result;
	}

	// Solution 2: Stack - Time: O(n), Space: O(n)
	/*
	public static int[] dailyTemperatures(int[] temperatures) {
		if (temperatures == null || temperatures.length == 0) {
			return temperatures;
		}

		int n = temperatures.length;
		int[] result = new int[n];

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			int temperature = temperatures[i];
			while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
				int index = stack.pop();
				result[index] = i - index;
			}

			stack.push(i);
		}

		return result;
	}
	*/

	// Brute Force - Time: O(n^2)
	/*
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
    */

    public static void main(String[] args) {
		int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
	    int[] result = dailyTemperatures(test);
	    for (int i: result) {
	    	System.out.print(i + ", ");
	    }
	    System.out.println();
	}
}
