import java.util.Arrays;


public class Solution {
	/*
	 * This question is the same as "Max Chunks to Make Sorted (ver. 1)" except the integers of the given array are not necessarily distinct, 
	 * the input array could be up to length 2000, and the elements could be up to 10**8.
	 * 
	 * Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually 
	 * sort each chunk.  After concatenating them, the result equals the sorted array.
	 * 
	 * What is the most number of chunks we could have made?
	 * 
	 * Example 1:
	 * 
	 * Input: arr = [5,4,3,2,1]
	 * Output: 1
	 * Explanation:
	 * Splitting into two or more chunks will not return the required result.
	 * For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
	 * 
	 * Example 2:
	 * 
	 * Input: arr = [2,1,3,4,4]
	 * Output: 4
	 * Explanation:
	 * We can split into two chunks, such as [2, 1], [3, 4, 4].
	 * However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
	 * 
	 * Note:
	 * 1. arr will have length in range [1, 2000].
	 * 2. arr[i] will be an integer in range [0, 10**8].
	 */
	
	// 在任一点leftMax <= 下一点的rightMin即可拆一下，最后结果是拆的次数，要加1才是chunk的个数.
	// e.g.  								2,   1,   3,   4,   4
	//      leftMax     					2,   2,   3,   4,   4
	//      rightMin    					1,   1,   3,   4,   4
	//      leftMax[i] <= rightMin[i + 1]            cut, cut, cut
	//                  					2,   1,  |3,   |4,   |4
    public static int maxChunksToSorted(int[] arr) {
    	int n = arr.length;
    	int[] leftMax  = new int[n];
    	int[] rightMin = new int[n];
    	leftMax[0] = arr[0];
    	rightMin[n - 1] = arr[n - 1];
    	
    	for (int i = 1; i < n; i++) {
    		leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
    	}
    	
    	for (int i = n - 2; i >= 0; i--) {
    		rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
    	}
    	
    	int count = 1;
    	for (int i = 0; i < n - 1; i++) {
    		if (leftMax[i] <= rightMin[i + 1]) {
    			count++;
    		}
    	}
    	
    	return count;
    }
    
    public static void main(String[] args) {
    	int[] test1 = {5, 4, 3, 2, 1};
    	int[] test2 = {2, 1, 3, 4, 4};
    	
    	System.out.println(maxChunksToSorted(test1));
    	System.out.println(maxChunksToSorted(test2));
	}
}
