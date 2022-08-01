
public class Solution {
	/**
	 * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
	 * 
	 * We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; 
	 * Otherwise, it's not.
	 * 
	 * Since the answer may be very large, the answer should be modulo 109 + 7.
	 * 
	 * Example 1:
	 * Input: n = 3, k = 0
	 * Output: 1
	 * Explanation: 
	 * Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
	 * 
	 * Example 2:
	 * Input: n = 3, k = 1
	 * Output: 2
	 * Explanation: 
	 * The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
	 * 
	 * Note:
	 * The integer n is in the range [1, 1000] and k is in the range [0, 1000].
	 */
	
	// https://leetcode.com/problems/k-inverse-pairs-array/solution/
	// http://www.cnblogs.com/grandyang/p/7111385.html
	// 推导：
	// 1. 对于一个没有inverse pair的array， 将任一元素向左移y步，将产生y个inverse pair；
	// 2. 因此，对于一个有n - 1个元素，有k - i个inverse pair的数组，将第n个元素加入到离右边i远的位置，则刚好会产生一个有k个inverse pair的元素为从1到n的数组
	// 3. 因此，推导出dp关系： 
	//    dp[i][j] -- 从1到i个元素，有j个inverse pair的数组数量
	//    (1) n = 0时没有元素，所以dp[0][k] = 0；
	//    (2) k = 0时， 即为数组的升序排列，只有一种可能，所以dp[n][0] = 1;
	//    (3) 其它情况dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - p] p = min(j, i - 1);
	//        p = j时，新加入第i个元素时，不再需要引入新的inverse pair，因此p不能超过j(否则意味着需要引入负数个inverse pair)
	//        p = i - 1时，意味着新加入的第i个元素要产生i - 1个inverse pair即加入到第一个位置，因此p不能超过i - 1(否则意味着加入的第i个元素要产生超过i - 1个inverse pair)
    
	// O(n ^ 2 * k) -- TLE
	/*
	public static int kInversePairs(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		int M = 1000000007;
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				for (int p = 0; p <= Math.min(j, i - 1); p++) {
					dp[i][j] += (dp[i - 1][j - p]) % M;
				}
			}
		}
		
		return dp[n][k];
    }
	*/
	
	// 上面的方法会TLE， 继续优化：
	// 由上面的推导可知：
	// dp[n][k] = dp[n - 1][k] + dp[n - 1][k-1] + ... + dp[n - 1][k - n + 1]
	// dp[n][k+1] = dp[n - 1][k+1] + dp[n - 1][k] + ... + dp[n - 1][k + 1 - n + 1]
	// 两式相减可得
	// dp[n][k+1] = dp[n][k] + dp[n - 1][k+1] - dp[n - 1][k - n + 1]
	// 即
	// dp[n][k] = dp[n][k-1] + dp[n - 1][k] - dp[n - 1][k - n]
	// 当k>=n的时候，最后一项的数组坐标才能为非负数，从而最后一项才有值，所以我们再更新的时候只需要判断一下k和n的关系，如果k>=n的话，就要减去最后一项
	// O(n * k)
	public static int kInversePairs(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		int M = 1000000007;
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % M;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + M) % M;
				}
			}
		}
		
		return dp[n][k];
	}
    public static void main(String[] args) {
		System.out.println(kInversePairs(3, 0));
		System.out.println(kInversePairs(3, 1));
	}
}
