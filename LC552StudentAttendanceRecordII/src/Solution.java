
public class Solution {
	/*
	 * Given a positive integer n, return the number of all possible attendance records with length n, which will be 
	 * regarded as rewardable. The answer may be very large, return it after mod 10^9 + 7.
	 * 
	 * A student attendance record is a string that only contains the following three characters:
	 * 
	 * 'A' : Absent.
	 * 'L' : Late.
	 * 'P' : Present.
	 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
	 * 
	 * Example 1:
	 * 
	 * Input: n = 2
	 * Output: 8 
	 * 
	 * Explanation:
	 * There are 8 records with length 2 will be regarded as rewardable:
	 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
	 * Only "AA" won't be regarded as rewardable owing to more than one absent times. 
	 *
	 * Note: The value of n won't exceed 100,000.
	 */
	
	// [关于 mod 10^9 + 7]
	// https://www.hackerearth.com/practice/notes/abhinav92003/why-output-the-answer-modulo-109-7/
	// A few distributive properties of modulo are as follows:
	// 	1. ( a + b ) % c = ( ( a % c ) + ( b % c ) ) % c
	//  2. ( a * b ) % c = ( ( a % c ) * ( b % c ) ) % c
	//  3. ( a – b ) % c = ( ( a % c ) - ( b % c ) ) % c ( see notes at bottom )
	//  4. ( a / b ) % c NOT EQUAL TO ( ( a % c ) / ( b % c ) ) % c
	//  So, modulo is distributive over +, * and - but not / .
	//  
	// **note**
	//  ( a – b ) % c = ( ( a % c ) - ( b % c ) ) %c is fine mathematically. 
	//  
	// But, while programming, don't use 
	//  a = (a % c);
	//  b = (b % c);
	//  ans = (a - b) % c;
	//  
	//  instead use
	//  a = a % c;
	//  b = b % c;
	//  ans = (a - b + c) % c;
	//
	// There are certain requirements on the choice of M:
	//	1. It should just be large enough to fit in an int data type.
	//	2. It should be a prime number.
	// 10^9 + 7 fits both criteria; which is why you nearly always find 10^9 + 7 in modulo type questions.
	// 
	// [关于本题]
	// https://leetcode.com/problems/student-attendance-record-ii/solution/
	// 当仅有L和P时：f(n) = f(n - 1)_'P' + f(n - 1)_'L' - f(n - 3)_'LLL' (= f(n - 4)_'PLLL') = 2f(n - 1) - f(n - 4)
	// 当有A时，f(n)中只能有1个A, 若在位置i,则此时的number of rewardable strings is f[i−1]∗f[n−i]。 因此答案为i 从1到n求和
	// 
	// 引入mod运算，则f(i) = 2f(i - 1) - f(i - 4) --> f(i) % M = 2f(i - 1) % M + (M - f(i - 4)) % M
	
	
    public static int checkRecord(int n) {
        long M = 1000000007;
        long[] dp = new long[n <= 5 ? 6 : n + 1];
        dp[0] = 1; // ""
        dp[1] = 2; // "P", "L"
        dp[2] = 4; // "PL", "LP", "PP", "LL";
        dp[3] = 7; // "PPP", "PPL", "PLP", "PLL", "LLP", "LPL", "LPP"
        
        for (int i = 4; i <=n; i++) {
        	dp[i] = (2 * dp[i - 1]) % M + (M - dp[i - 4]) % M;
        }
        
        long sum = dp[n]; // 没有A的情况；
        
        // A 在1 ~ n时的情况
        for (int i = 1; i <= n; i++) {
        	sum += (dp[i - 1] * dp[n - i]) % M;
        }
        
        return (int) (sum % M);
    }
    
    public static void main(String[] args) {
		System.out.println(checkRecord(2));
	}
}
