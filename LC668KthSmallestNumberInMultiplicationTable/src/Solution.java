
public class Solution {
	
	/*
	 * Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
	 * 
	 * Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest 
	 * number in this table.
	 * 
	 * Example 1:
	 * Input: m = 3, n = 3, k = 5
	 * Output: 3
	 * 
	 * Explanation: 
	 * The Multiplication Table:
	 * 1	2	3
	 * 2	4	6
	 * 3	6	9
	 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
	 * 
	 * Example 2:
	 * Input: m = 2, n = 3, k = 6
	 * Output: 6
	 * 
	 * Explanation: 
	 * The Multiplication Table:
	 * 1	2	3
	 * 2	4	6
	 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
	 * 
	 * Note:
	 * 1. The m and n will be in the range [1, 30000].
	 * 2. The k will be in the range [1, m * n]
	 */

	// O(m * log(m * n)) 
	// 在表中用二分法查找是否有k或多余k的元素小于或等于mid，若是，则说明mid太大了，移动end，否则移动start
    public static int findKthNumber(int m, int n, int k) {
        int start = 1;
        int end = m * n; 
        
        while(start < end) {
        	int mid = start + (end - start) / 2;
        	if (enough(mid, m, n, k)) {
        		end = mid;
        	} else {
        		start = mid + 1;
        	}
        }
        
        return start;
    }
    
    // if there are k or more elements smaller than or equal to x
    private static boolean enough(int x, int m, int n, int k) {
    	int count = 0;
    	
    	for (int i = 1; i <= m; i++) {
    		
    		// 对于第i行， 1*i, 2*i, ..., k*i, ... n*i
    		// 若要 k*i <= x --> k <= x / i 也就是在i行，比k小的数的个数是x / i个， 如果n小于x / i， 则有n个
    		count += Math.min(n, x / i);
    	}
    	
    	return count >= k;
    }
    public static void main(String[] args) {
		System.out.println(findKthNumber(3, 3, 5));
		System.out.println(findKthNumber(2, 3, 6));
	}
    
}
