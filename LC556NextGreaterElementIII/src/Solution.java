
public class Solution {
	/*
	 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits 
	 * existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
	 * 
	 * Example 1:
	 * 
	 * Input: 12
	 * Output: 21 
	 * 
	 * Example 2:
	 * 
	 * Input: 21
	 * Output: -1
	 */
	
	// 类似LC31
    public static int nextGreaterElement(int n) {
        char[] charArray = ("" + n).toCharArray();
        
        int pos = charArray.length - 1;
        
        while (pos > 0 && charArray[pos - 1] >= charArray[pos]) {
        	pos--;
        }
        
        if (pos == 0) {
        	return -1;
        }
        
        reverse(charArray, pos, charArray.length - 1);
        
        int next = pos;
        pos--;
        
        while (next < charArray.length && charArray[next] <= charArray[pos]) {
        	next++;
        }
        
        char c = charArray[pos];
        charArray[pos] = charArray[next];
        charArray[next] = c;
        
        long result = Long.parseLong(new String(charArray));
        
        if (result > Integer.MAX_VALUE) {
        	return -1;
        }
        return (int)result;
    }
    
    private static void reverse(char[] chrs, int start, int end) {
        while (start < end) {
            char c = chrs[start];
            chrs[start] = chrs[end];
            chrs[end] = c;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
		//System.out.println(nextGreaterElement(12));
		//System.out.println(nextGreaterElement(21));
    	System.out.println(Integer.MAX_VALUE);
		System.out.println(nextGreaterElement(1999999999));
	}
}
