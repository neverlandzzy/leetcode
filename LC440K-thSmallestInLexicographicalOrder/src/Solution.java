
public class Solution {
	/*
	 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
	 * 
	 * Note: 1 ≤ k ≤ n ≤ 109.
	 * 
	 * Example:
	 * 
	 * Input:
	 * n: 13   k: 2
	 * 
	 * Output:
	 * 10
	 * 
	 * Explanation:
	 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
	 */
	
	// https://discuss.leetcode.com/topic/64624/concise-easy-to-understand-java-5ms-solution-with-explaination/2
    public static int findKthNumber(int n, int k) {
    	int cur = 1;
    	k--;
    	
    	while (k > 0) {
    		//计算n时，从cur 走到cur + 1需要的步数
    		int steps = steps(n, cur, cur + 1);   		
    		if (steps <= k) {
    			// 当steps <= k时，说明可以从cur 走到cur + 1，需要steps步，因此 k -= steps;
    			cur += 1;
    			k -= steps;
    		} else {
    			// 当steps > k时，说明从cur不能走到cur + 1, 则走一步到 cur * 10，也就是cur最左的子节点
    			cur *= 10;
    			k -= 1;
    		}
    	}
    	
        return cur;
    }
    
    // 计算n个数时，n1到n2的距离
    // 当n2 <= n 时，说明n2存在，因此step = n2 - n1.例如：n = 13, n1 = 1, n2 = 2时， n2 < n，说明2存在在结果[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]中
    // 当n2 > n时，说明n在n1 到n2的路径上，因此step = n + 1 - n1.例如上例进一步循环，n1 *= 10 = 10, n2 *= 10 = 20时， n2 > n 说明n(13)在[10 ~ 19]中
    // 因此，当n = 13时， 1到2的距离为 5： 1, 10, 11, 12, 13, 2
    private static int steps(int n, long n1, long n2) {
    	int steps = 0;
    	while (n1 <= n) {
    		steps += Math.min(n + 1, n2) - n1;
    		n1 *= 10;
    		n2 *= 10;
    	}
    	return steps;
    }
    
    public static void main(String[] args) {
		//System.out.println(steps(13, 1, 2));
    	System.out.println(findKthNumber(13, 3));
	}
}
