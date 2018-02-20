
public class Solution {
	/*
	 * Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do 
	 * NOT contain consecutive ones.
	 * 
	 * Example 1:
	 * Input: 5
	 * Output: 5
	 * Explanation: 
	 * Here are the non-negative integers <= 5 with their corresponding binary representations:
	 * 0 : 0
	 * 1 : 1
	 * 2 : 10
	 * 3 : 11
	 * 4 : 100
	 * 5 : 101
	 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
	 * Note: 1 <= n <= 10^9
	 */
	
	// https://discuss.leetcode.com/topic/90571/java-solution-dp/2
	// https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
	
    public static int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();
        
        // a[i]：长度为i + 1的不含连续1， 以0结尾的binary string长度
        // b[i]：长度为i + 1的不含连续1， 以1结尾的binary string长度
        int[] a = new int[n];
        int[] b = new int[n];
        a[0] = 1;
        b[0] = 1;
        
        for (int i = 1; i < n; i++) {
        	a[i] = a[i - 1] + b[i - 1];
        	b[i] = a[i - 1];
        }
        
        // 此时，result为长度n的所有不含连续1的binary字符串数目。里面包括了比num大的数，所以要减去这些数
        int result = a[n - 1] + b[n - 1];
        
        // 将num反转后倒序做，即是从MSB开始，因为只有MSB会决定当前数字是否大于num。 翻转并倒序是因为要考虑到重复的个数b[i]
        // 重复的情况：
        // 遇到11时：说明统计到的数字肯定比num小，因此不会再有重复，break
        // 遇到01时：根据DP的递推关系式，统计的只有00， 01两种情况，都不会大于当前num，所以没有重复，继续；
        // 遇到10时：根据DP的递推关系式，同理，统计的只有10这种情况，不会大于num，所以没有重复，继续；
        // 遇到00时：根据DP的递推关系式，会统计00, 01两种情况，其中01的大于当前num，要减去。01情况的数目，即为长度为i + 1以1结尾的binary string长度， 即b[i]
        for (int i = n - 2; i >= 0; i--) {
        	if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') {
        		break;
        	}
        	
        	if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') {
        		result -= b[i];
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		 System.out.println(findIntegers(5));
	}
}
