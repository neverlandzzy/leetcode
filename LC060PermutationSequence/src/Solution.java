import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.
	 * 
	 * By listing and labeling all of the permutations in order,
	 * We get the following sequence (ie, for n = 3):
	 * 
	 * "123"
	 * "132"
	 * "213"
	 * "231"
	 * "312"
	 * "321"
	 * Given n and k, return the kth permutation sequence.
	 * Note: Given n will be between 1 and 9 inclusive.
	 */
	
	//http://bangbingsyb.blogspot.com/2014/11/leetcode-permutation-sequence.html
	//https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n
	
    public static String getPermutation(int n, int k) {
        int[] factors = new int[n];
        
        factors[0] = 1;
        for (int i = 1; i < n; i++) {
            factors[i] = factors[i - 1] * i;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        k--; // k originally starts from 1
        
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factors[i];
            sb.append(list.get(index));
            k %= factors[i];
            list.remove(index);
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(getPermutation(3,5));
	}
}
