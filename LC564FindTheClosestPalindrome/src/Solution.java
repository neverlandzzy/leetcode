import java.util.Arrays;


public class Solution {
	/*
	 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
	 *
	 * The 'closest' is defined as absolute difference minimized between two integers.
	 *
	 * Example 1:
	 * Input: "123"
	 * Output: "121"
	 * 
	 * Note:
	 * The input n is a positive integer represented by string, whose length will not exceed 18.
	 * If there is a tie, return the smaller one as answer.
	 */
	
	// https://discuss.leetcode.com/topic/87287/java-solution-with-full-explaination
	// 分别找大于和小于n的两个palindromic integer 返回相差绝对值较小的
	// 1. 把n的前半部分反序copy到后半部得到一个palindromic integer;e.g. 1343 -- > 1331
	// 2. 比较这个integer与原数的大小，从而找到一个大于或者小于原数的palindromic integer; e.g. 1331 < 1343 所以1331是小于1343的一个palindromic integer
	// 3. 接着找另一个palindromic integer，对于此例， 则是找大于1343的那个palindromic integer
	// 4. 将1331的上半部分最后一位(n.length - 1)/2 加上1，--> 1431，然后类似第一步，把1431的前半部分反序copy到后半部 --> 1441 这个即为大于1343的一个palindromic integer
	// corner case:
	// 5. 第4步中，若该位加完恰好为0(9+1 = 10)，或者减完小于0(0 - 1 = 9)，则将改为变为0或9然后继续加或减前1位
	// 6. 在减的情况中，若新数第一位为0，则新数的位数要-1 --> 1000 - 1 = 999
    public static String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        long larger = largerPalindromic(num + 1);
        long smaller = smallerPalindromic(num - 1);
        
        return Math.abs(num - smaller) > Math.abs(num - larger) ? String.valueOf(larger) : String.valueOf(smaller);
    }
    
    private static long largerPalindromic(long num) {
    	char[] s = String.valueOf(num).toCharArray(); // 原数 + 1；// e.g. 1344
    	int n = s.length;
    	char[] t = Arrays.copyOf(s, n);        // 结果； 
    	
    	// 把n的前半部分反序copy到后半部得到一个palindromic integer --> e.g. 1331
    	for (int i = 0; i < n / 2; i++) {
    		t[n - i - 1] = t[i];
    	}
    	
    	// 比较结果与原数的大小，若大于原数，则返回
    	if (Long.parseLong(String.valueOf(t)) >= num) {
    		return Long.parseLong(String.valueOf(t));
    	}
    	
    	// 否则，将结果的上半部分最后一位(n - 1)/2 加上1，--> 1431，注意corner case, 若该位加完恰好为0(9+1 = 10), 则将改为变为0或9然后继续加前1位
    	// 注意不是(n / 2 - 1)， 对于奇数位的数不对， e.g. 11011
    	for (int i = (n - 1) / 2; i >= 0; i--) {
    		if (t[i] + 1 > '9') {
    			t[i] = '0';
    		} else {
    			t[i]++;
    			break;
    		}
    	}
    	// 然后类似第一步，把1431的前半部分反序copy到后半部 --> 1441
    	for (int i = 0; i < n / 2; i++) {
    		t[n - i - 1] = t[i];
    	}
    	
    	return Long.parseLong(String.valueOf(t));
    }
    
    private static long smallerPalindromic(long num) {
    	char[] s = String.valueOf(num).toCharArray(); 
    	int n = s.length;
    	char[] t = Arrays.copyOf(s, n);  
    	
    	for (int i = 0; i < n / 2; i++) {
    		t[n - i - 1] = t[i];
    	}
    	
    	if (Long.parseLong(String.valueOf(t)) <= num) {
    		return Long.parseLong(String.valueOf(t));
    	}
    	
    	for (int i = (n - 1) / 2; i >= 0; i--) {
    		if (t[i] - 1 < '0') {
    			t[i] = '9';
    		} else {
    			t[i]--;
    			break;
    		}
    	}
    	
    	// 在减的情况中，若新数第一位为0，则新数的位数要-1 --> 1000 - 1 = 999
        if (t[0] == '0') {
            char[] c = new char[n - 1];
            Arrays.fill(c, '9');
            return Long.parseLong(String.valueOf(c)); 
        }
        
    	for (int i = 0; i < n / 2; i++) {
    		t[n - i - 1] = t[i];
    	}
    	
    	return Long.parseLong(String.valueOf(t));
    }
    
    public static void main(String[] args) {
		System.out.println(nearestPalindromic("123"));
	}
}
