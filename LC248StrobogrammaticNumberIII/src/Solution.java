
public class Solution {
	/**
	 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
	 * 
	 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
	 * 
	 * For example,
	 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
	 * 
	 * Note:
	 * Because the range might be a large number, the low and high numbers are represented as string.
	 */
	
	private static final char[][] PAIRS = {{'0', '0'}, {'1' ,'1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}}; 
    
	public static int strobogrammaticInRange(String low, String high) {
        int[] count = new int[1];
        
        for (int len = low.length(); len <= high.length(); len++) {
        	char[] c = new char[len];
        	helper(low, high, c, 0, len - 1, count);
        }
        
        return count[0];
    }
	
	private static void helper(String low, String high, char[] c, int left, int right, int[] count) {
		if (left > right) {
			String s = new String(c);
			if (s.length() == low.length() && s.compareTo(low) < 0) {
				return;
			}
			
			if (s.length() == high.length() && s.compareTo(high) > 0) {
				return;
			}
			
			count[0]++;
			return;
		}
		
		for (char[] pair: PAIRS) {
			c[left] = pair[0];
			c[right] = pair[1];
			
			if (c.length != 1 && c[0] == '0') {
				continue;
			}
			
			if (left == right && pair[0] != pair[1]) {
				continue;
			}
			
			helper(low, high, c, left + 1, right - 1, count);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(strobogrammaticInRange("50", "808"));
		
	}
}
