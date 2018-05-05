import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) having a prime number of set bits in their 
	 * binary representation.
	 * 
	 * (Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary 
	 * is 10101 which has 3 set bits. Also, 1 is not a prime.)
	 * 
	 * Example 1:
	 * 
	 * Input: L = 6, R = 10
	 * Output: 4
	 * Explanation:
	 * 6 -> 110 (2 set bits, 2 is prime)
	 * 7 -> 111 (3 set bits, 3 is prime)
	 * 9 -> 1001 (2 set bits , 2 is prime)
	 * 10->1010 (2 set bits , 2 is prime)
	 * 
	 * Example 2:
	 * 
	 * Input: L = 10, R = 15
	 * Output: 5
	 * Explanation:
	 * 10 -> 1010 (2 set bits, 2 is prime)
	 * 11 -> 1011 (3 set bits, 3 is prime)
	 * 12 -> 1100 (2 set bits, 2 is prime)
	 * 13 -> 1101 (3 set bits, 3 is prime)
	 * 14 -> 1110 (3 set bits, 3 is prime)
	 * 15 -> 1111 (4 set bits, 4 is not prime)
	 * 
	 * Note:
	 * 1. L, R will be integers L <= R in the range [1, 10^6].
	 * 2. R - L will be at most 10000.
	 */
	
    public static int countPrimeSetBits(int L, int R) {
        Set<Integer> set = new HashSet<>();
        // 10^6 ~= (2^4)^6 ~= 2^24  -- 面试时候这样算比较方便，实际10^6 < 2^20, 因此set到19即可
        set.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23));
        
        int result = 0;
        
        for (int i = L; i <= R; i++) {
        	
        	// 可以用built-in method 替换
        	int counter = 0;
        	// 不能直接用i计算，否则i的值会改变，从而影响其作为循环控制变量的作用(i++)
        	int n = i;
        	while (n > 0) {
        		if ((n & 1) == 1) {
        			counter++;
        		}
        		n = (n >> 1);
        	}
        	
        	if (set.contains(counter)) {
        		result++;
        	}
        	// 可以用built-in method 替换
        	
        	// 用built-in method
        	/*
        	if (set.contains(Integer.bitCount(i))) {
        		result++;
        	}
        	*/
        }
        
        return result;
    }

    
    public static void main(String[] args) {
		System.out.println(countPrimeSetBits(6, 10));
		System.out.println(countPrimeSetBits(10, 15));
	}
}
