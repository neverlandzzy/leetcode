
public class Solution {
	/*
	 * We have two integer sequences A and B of the same non-zero length.
	 *
	 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
	 *
	 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only 
	 * if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
	 *
	 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input 
	 * always makes it possible.
	 *
	 * Example:
	 * Input: A = [1,3,5,4], B = [1,2,3,7]
	 * Output: 1
	 * Explanation: 
	 * Swap A[3] and B[3].  Then the sequences are:
	 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
	 * which are both strictly increasing.
	 * 
	 * Note:
	 *
	 * A, B are arrays with the same length, and that length will be in the range [1, 1000].
	 * A[i], B[i] are integer values in the range [0, 2000].
	 */
	
	
	// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119830/Python-14-line-O(1)-space-O(n)-time-DP-solution
	// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119879/C++JavaPython-Easy-Understood-DP-Solution
	// A[i-1], A[i]
	// B[i-1], B[i]
	// remain[i]: 在i处不swap， swap[i]: 在i处swap
	// 两种情况：
	// 1: A[i] > A[i-1] && B[i] > B[i-1]:
	//    (1) 若在i-1处swap过，则在i处再次swap可以保证 A[i] > A[i-1] && B[i] > B[i-1]:
	//        swap[i] = min(swap[i], swap[i - 1] + 1)
	//    (2) 若在i-1处没有swap过，则在i处不swap可以保证 A[i] > A[i-1] && B[i] > B[i-1]:
	//        remain[i] = min(remain[i], remain[i - 1])
	//
	// 2: A[i] > B[i-1] && B[i] > A[i-1]:
	//    (1) 若在i-1处swap过，则在i处不swap可以保证 A[i] > A[i-1] && B[i] > B[i-1]:
	//        remain[i] = min(remain[i], swap[i - 1])
	//    (2) 若在i-1处没有swap过，则在i处swap可以保证 A[i] > A[i-1] && B[i] > B[i-1]:
	//        swap[i] = min(swap[i], remain[i - 1] + 1)
	
	// Solution 1: time: O(n), space: O(n)
	/*
    public static int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n];
        int[] remain = new int[n];
        
        swap[0] = 1;
        
        for (int i = 1; i < n; i++) {
        	swap[i] = n;
        	remain[i] = n;
        	if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        		swap[i] = swap[i - 1] + 1;
        		remain[i] = remain[i - 1];
        	}
        	
        	if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
        		swap[i] = Math.min(swap[i], remain[i - 1] + 1);
        		remain[i] = Math.min(remain[i], swap[i - 1]);
        	}
        }
        
        return Math.min(swap[n - 1], remain[n - 1]);
    }
    */
	
    // Solution 2: time: O(n), space: O(1)
    public static int minSwap(int[] A, int[] B) {
        int n = A.length;
        int swap = 1;
        int remain = 0;
        
        for (int i = 1; i < n; i++) {
        	int tmpSwap = n;
        	int tmpRemain = n;
        	if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
        		tmpSwap = swap + 1;
        		tmpRemain = remain;
        	}
        	
        	if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
        		tmpSwap = Math.min(tmpSwap, remain + 1);
        		tmpRemain = Math.min(tmpRemain, swap);
        	}
        	
        	swap = tmpSwap;
        	remain = tmpRemain;
        }
        
        return Math.min(swap, remain);
    }   
    public static void main(String[] args) {
		int[] test11 = {1,3,5,4};
		int[] test12 = {1,2,3,7};
		
		System.out.println(minSwap(test11, test12));
		
	}
}
