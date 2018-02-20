import java.math.BigInteger;


public class Solution {
	/*
	 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
	 * 
	 * Now given a string representing n, you should return the smallest good base of n in string format. 
	 * 
	 * Example 1:
	 * Input: "13"
	 * Output: "3"
	 * Explanation: 13 base 3 is 111.
	 * 
	 * Example 2:
	 * Input: "4681"
	 * Output: "8"
	 * Explanation: 4681 base 8 is 11111.
	 * 
	 * Example 3:
	 * Input: "1000000000000000000"
	 * Output: "999999999999999999"
	 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
	 * 
	 * Note:
	 * The range of n is [3, 10^18].
	 * The string representing n is always valid and will not have leading zeros.
	 */
	
    // https://discuss.leetcode.com/topic/78148/java-o-logn-2-binary-search-solution
	// 以k为base表示n：n = f(k, m) = 1 + k + k^2 + ... + k^(m-1) = (k^m - 1)/(k-1) -- 等比数列求和
	// 对于f(k, m) = (k^m - 1)/(k-1)， 若m固定，f以k单调递增，若k固定，f以m单调递增
	// 因此，对于n = f(k1, m1) == f(k2, m2), 若m1 > m2则k1 < k2
	// 因此，当m取最大值的时候，k为最小值
	// 由于k >= 2, n = (k^m - 1)/(k-1), 所以m <= log(n + 1)(以2为底)
	// 由于n >= 3, 所以，m至少大于2
	// 因此   2<= m <= log(n + 1)
	// 因此，从m的上限log(n + 1)开始，如果可以找到k满足n = (k^m - 1)/(k - 1)，则返回答案，否则，m--继续找
	// 当m == 2时，总会有k = n - 1这个解，因此若上述循环无法找到合适的解，则返回k = n - 1
	//
	// 在每一步循环中，即m固定为某一值的时候，用binary search 找合适的k
	// 由于 n > k^(m-1) --> 因此 k < n^(1/(m-1))
	// 由于 n <= k^m - 1 --> 因此 k >= (n+1)^(1/m)
	// 所以，binary search的上下界分别为 (n+1)^(1/m) <= k <= n^(1/(m-1))
	
    public static String smallestGoodBase(String n) {
    	long num = Long.parseLong(n);
    	
    	// log(n + 1) == ln(n + 1)/ln2 ---> 换底公式
    	for (int m = (int)(Math.log(num + 1)/Math.log(2)); m >= 2; m--) {
    		long start = (long)(Math.pow(num + 1, 1.0 / (m)));
    		long end = (long)(Math.pow(num, 1.0 / (m - 1)));
    		
    		while (start <= end) {
    			long mid = start + (end - start) / 2;
    			long f = 0;
    			
    			// 计算 f = (long)(Math.pow(mid, m) - 1)/(mid - 1);
    			// 直接用上面的计算会溢出
     			for (int i = 0; i < m; i++) {
     				f = f * mid + 1;
     			}
    			
    			if (num == f) {
    				return String.valueOf(mid);
    			} else if (num > f) {
    				start = mid + 1;
    			} else {
    				end = mid - 1;
    			}
    		}
    	}
    	
    	return String.valueOf(num - 1);
    }
    
    public static void main(String[] args) {
		System.out.println(smallestGoodBase("13"));
		System.out.println(smallestGoodBase("4681"));
		System.out.println(smallestGoodBase("1000000000000000000"));
	}
}
