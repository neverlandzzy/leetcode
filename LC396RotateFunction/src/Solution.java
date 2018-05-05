
public class Solution {
	/*
	 * Given an array of integers A and let n to be its length.
	 * 
	 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
	 * 
	 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
	 * 
	 * Calculate the maximum value of F(0), F(1), ..., F(n-1).
	 * 
	 * Note:
	 * n is guaranteed to be less than 10^5.
	 * 
	 * Example:
	 * A = [4, 3, 2, 6]
	 * 
	 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
	 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
	 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
	 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
	 * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
	 */
	
	// https://leetcode.com/problems/rotate-function/discuss/87842/Java-Solution-O(n)-with-non-mathametical-explaination
	// F(0) = (0A) + (1B) + (2C) + (3D) + (4E)
	// F(1) = (4A) + (0B) + (1C) + (2D) + (3E)
	// F(2) = (3A) + (4B) + (0C) + (1D) + (2E)
	// --->
	// F'(0) = F(0) - sum = (-1A) + (0B) + (1C) + (2D) + (3E)
	// F'(0) + n * A = (4A) + (0B) + (1C) + (2D) + (3E) = F(1)
	// Time: O(n)
	
    public static int maxRotateFunction(int[] A) {
        int sum = 0;
        int f = 0;
        int n = A.length;
        
        for (int i = 0; i < n; i++) {
        	sum += A[i];
        	f += i * A[i];  
        }
        
        int max = f;
        
        for (int i = 0; i < n - 1; i++) {
        	f -= sum;
        	f += n * A[i];
        	max = Math.max(max, f);
        }
        
        return max;
    }
    
    public static void main(String[] args) {
		int[] A = {4, 3, 2, 6};
		System.out.println(maxRotateFunction(A));
	}
}
