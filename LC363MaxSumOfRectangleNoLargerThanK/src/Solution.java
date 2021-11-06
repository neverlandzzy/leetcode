import java.util.TreeSet;


public class Solution {
	
	/**
	 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in 
	 * the matrix such that its sum is no larger than k.
	 * 	
	 * Example:
	 * 	Given matrix = [
	 * 	  [1,  0, 1],
	 * 	  [0, -2, 3]
	 * 	]
	 * 
	 * 	k = 2
	 * 
	 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no 
	 * larger than k (k = 2).
	 * 	
	 * Note:
	 * The rectangle inside the matrix must have an area > 0.
	 * What if the number of rows is much larger than the number of columns?
	 */

	// Time: O(min(m,n)^2 * max(m,n) * logmax(m,n))
    public static int maxSumSubmatrix(int[][] matrix, int k) {
    	
		// 1. Compute the cumulative sum (val) of the array;
		// 2. Find a pair of i and j, constrained to i < j and cum[j] - cum[i] <= k;
		// 3. The above inequation is cum[j] - k <= cum[i], so we need to find the min of 
		//    cum[i] to maximize cum[j] - cum[i], that is TreeSet.ceiling(cum[j] - k);
    	// 4. If founded in the TreeSet, the value is actually cum[i], but subtracting cum[i]
    	//    from cum[j], we update the result.
    	
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int max = Math.max(m, n);
        int min = Math.min(m, n);
        int result = Integer.MIN_VALUE;
        
        for (int i = 0; i < min; i++) {
            
            int[] dp = new int[max];
            
            for (int j = i; j < min; j++) {
                
                int val = 0;
                TreeSet<Integer> set = new TreeSet<>();
                // 记得加上0，相当于数组为空的时候的值，这样才可以计算当前全部的val的值
                set.add(0);
                
                for (int l = 0; l < max; l++) {
                    dp[l] += (m < n) ? matrix[j][l] : matrix[l][j];
                    val += dp[l];
                    
                    Integer tmp = set.ceiling(val - k);
        			// ceiling(): Returns the least element in this set greater than or equal to the given element, 
        			// or null if there is no such element.
                    
                    if (tmp != null) {
                        result = Math.max(result, val - tmp);
                    }
                    set.add(val);
                }
            }
        }
        
        return result;
    }
    public static void main(String[] args) {
		int[][] test = {{1, 0, 1, 5}, {0, -2, 3, 6}, {1, 2, 3, 4}};
		
		System.out.println(maxSumSubmatrix(test, 2));
		
		/*
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		set.add(0);
		set.add(1);
		set.add(2);
		
		Integer tmp = set.ceiling(3);
		
		if(tmp == null) {
			System.out.println("null");
		}
		System.out.println(tmp);
		*/
	}
}
