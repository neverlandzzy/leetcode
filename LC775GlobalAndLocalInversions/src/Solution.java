
public class Solution {
	/*
	 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
	 *
	 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
	 *
	 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
	 *
	 * Return true if and only if the number of global inversions is equal to the number of local inversions.
	 *
	 * Example 1:
	 *
	 * Input: A = [1,0,2]
	 * Output: true
	 *
	 * Explanation: There is 1 global inversion, and 1 local inversion.
	 *
	 * Example 2:
	 *
	 * Input: A = [1,2,0]
	 * Output: false
	 * Explanation: There are 2 global inversions, and 1 local inversion.
	 *
	 * Note:
	 * 1. A will be a permutation of [0, 1, ..., A.length - 1].
	 * 2. A will have length in range [1, 5000].
	 * 3. The time limit for this problem has been reduced.
	 */
	
	// local inversion 就是相邻的两个元素A[i] > A[i + 1]， 属于global inversion的一部分
	// 因此，只需检查是否存在A[i] > A[j] where i + 2 <= j即可，也就是从右向左扫描，记录当前i处的最小值，看是否有A[i - 2] > min
	public static boolean isIdealPermutation(int[] A) {
		int N = A.length; 
		int min = N; //  A 的范围是[0, 1, ..., N - 1]
		
		for (int i = N - 1; i >= 2; i--) {
			min = Math.min(min, A[i]);
			if (A[i - 2] > min) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[] test21 = {1, 0, 2};
		int[] test22 = {1, 2, 0};
		int[] test23 = {2, 0, 1};
		
		System.out.println(isIdealPermutation(test21));
		System.out.println(isIdealPermutation(test22));
		System.out.println(isIdealPermutation(test23));
	}
}
