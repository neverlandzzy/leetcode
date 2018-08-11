import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
	 * 
	 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
	 * 
	 * Example:
	 * 
	 * matrix = [
	 *    [ 1,  5,  9],
	 *    [10, 11, 13],
	 *    [12, 13, 15]
	 * ],
	 * k = 8,
	 * 
	 * return 13.
	 * 
	 * Note: 
	 * You may assume k is always valid, 1 ≤ k ≤ n^2.
	 */
	
	// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85173/Share-my-thoughts-and-Clean-Java-Code
	// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/85193/Binary-Search-Heap-and-Sorting-comparison-with-concise-code-and-1-liners-Python-72-ms
	//
	// Solution 1: heap  Time: O(k * log(n)) -- worst case -- O(n^2 * log(n)) -- 当k == n^2时
	/*
    public static int kthSmallest(int[][] matrix, int k) {
    	int n = matrix.length; // n * n
    	
    	PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
    		public int compare(int[] o1, int[] o2) {
    			return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
    		}
    	});
    	
    	for (int i = 0; i < n; i++) {
    		heap.offer(new int[] {i, 0});
    	}
    	
    	for (int i = 0; i < k - 1; i++) {
    		int[] pos = heap.poll();
    		int x = pos[0];
    		int y = pos[1];
    		
    		if (y < n - 1) {
    			heap.offer(new int[] {x, y + 1});
    		}
    	}
    	
    	int[] pos = heap.poll();
    	return matrix[pos[0]][pos[1]];
    }
    */
    
    // Solution 2: binary search -- each of the rows and columns are sorted
	// Time: O(n * log(n) * log(n))  
    public static int kthSmallest(int[][] matrix, int k) {
    	int n = matrix.length; // n * n
    	int start = matrix[0][0];
    	int end = matrix[n - 1][n - 1];
    	
    	while (start < end) {
    		int mid = start + (end - start) / 2;
    		int count = 0;
    		int j = n - 1;
    		for (int i = 0; i < n; i++) {
    			while (j >= 0 && matrix[i][j] > mid) {
    				j--;
    			}
    			
    			count += j + 1;
    		}
    		
    		if (count < k) {
    			start = mid + 1;
    		} else {
    			end = mid;
    		}
    	}
    	
    	return start;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
		
		System.out.println(kthSmallest(test1, 8));
	}
}
