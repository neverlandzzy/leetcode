
public class Solution {
	/**
	 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
	 * 
	 * For example,
	 * Given n = 3, there are a total of 5 unique BST's.
	 * 
	 *    1         3     3      2      1
	 *     \       /     /      / \      \
	 *      3     2     1      1   3      2
	 *     /     /       \                 \
	 *    2     1         2                 3
	 */
	
    public static int numTrees(int n) {
        /*
         * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
         * G(0)=1, G(1)=1. 
         * F(i, n) = G(i-1) * G(n-i)   1 <= i <= n
         * F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, 
         * and the sequence ranges from 1 to n. 当以i为root时，左子树为(0， i-1), 因此有G(i-1)种构成，右子数
         * 为(i+1, n), 因此有G(n-i)种构成。因此F(i, n) = G(i-1) * G(n-i)
         * 
         * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 
         * https://leetcode.com/discuss/24282/dp-solution-in-6-lines-with-explanation-f-i-g-i-1-g-n-i
         */
    	
    	int[] d = new int[n + 1];
    	d[0] = 1;
    	d[1] = 1;
    	for (int i = 2; i <= n; i++) {
    		for (int j = 0; j < i; j++)
    		d[i] += d[j] * d[i - j - 1];
    	}
    	
    	return d[n];
    }
    
    public static void main(String[] args) {
		System.out.println(numTrees(3));
		System.out.println(numTrees(5));
	}
}
