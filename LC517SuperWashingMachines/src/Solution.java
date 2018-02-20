
public class Solution {
	/*
	 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
	 * 
	 * For each move, you could choose any m (1 ? m ? n) washing machines, and pass one dress of each washing machine to one of 
	 * its adjacent washing machines at the same time .
	 * 
	 * Given an integer array representing the number of dresses in each washing machine from left to right on the line, you 
	 * should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible 
	 * to do it, return -1.
	 * 
	 * Example1
	 * 
	 * Input: [1,0,5]
	 * 
	 * Output: 3
	 * 
	 * Explanation: 
	 * 1st move:    1     0 <-- 5    =>    1     1     4
	 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
	 * 3rd move:    2     1 <-- 3    =>    2     2     2   
	 * 
	 * Example2
	 * 
	 * Input: [0,3,0]
	 * 
	 * Output: 2
	 * 
	 * Explanation: 
	 * 1st move:    0 <-- 3     0    =>    1     2     0    
	 * 2nd move:    1     2 --> 0    =>    1     1     1     
	 * 
	 * Example3
	 * 
	 * Input: [0,2,0]
	 * 
	 * Output: -1
	 * 
	 * Explanation: 
	 * It's impossible to make all the three washing machines have the same number of dresses. 
	 * 
	 * Note:
	 * The range of n is [1, 10000].
	 * The range of dresses number in a super washing machine is [0, 1e5].
	 */
	
	// https://discuss.leetcode.com/topic/80013/very-intuitive-o-n-solution
    public static int findMinMoves(int[] machines) {
    	if (machines == null || machines.length == 0) {
    		return 0;
    	}
    	
    	long sum = 0;
    	int n = machines.length;
    	
    	for (int k: machines) {
    		sum += k;
    	}
    	
    	if (sum % n != 0) {
    		return -1;
    	}
    	// leftSum[i]:  current sum of dresses of machines [0...i-1]
    	// rightSum[i]: current sum of dresses of machines [i+1...n-1]
    	int[] leftSum = new int[n];
    	int[] rightSum = new int[n];
    	
    	for (int i = 1; i < n; i++) {
    		leftSum[i] = leftSum[i - 1] + machines[i - 1];
    	}
    	
    	for (int i = n - 2; i >= 0; i--) {
    		rightSum[i] = rightSum[i + 1] + machines[i + 1];
    	}
    	
    	int average = (int)(sum / n);
    	int result = 0;
    	
    	for (int i = 0; i < n; i++) {
    		int leftExpectedSum = average * i;
    		int rightExpectedSum = average * (n - i - 1);
    		int moveToLeft = 0;
    		int moveToRight = 0;
    		
    		// 当左边的机器数目小于期望数目时，向左移
    		if (leftSum[i] < leftExpectedSum) {
    			moveToLeft = leftExpectedSum - leftSum[i];
    		}
    		// 当右边的机器数目小于期望数目时，向右移
    		if (rightSum[i] < rightExpectedSum) {
    			moveToRight = rightExpectedSum - rightSum[i];
    		}
    		
    		result = Math.max(result, (moveToLeft + moveToRight));
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 0, 5};
		int[] test2 = {0, 3, 0};
		int[] test3 = {0, 2, 0};
		
		System.out.println(findMinMoves(test1));
		System.out.println(findMinMoves(test2));
		System.out.println(findMinMoves(test3));
	}
}
