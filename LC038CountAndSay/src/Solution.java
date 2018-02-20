
public class Solution {
	/*
	 * The count-and-say sequence is the sequence of integers beginning as follows:
	 * 
	 * 1, 11, 21, 1211, 111221, ...
	 * 
	 * 1 is read off as "one 1" or 11.
	 * 11 is read off as "two 1s" or 21.
	 * 21 is read off as "one 2, then one 1" or 1211.
	 * Given an integer n, generate the nth sequence.
	 * 
	 * Note: The sequence of integers will be represented as a string.
	 * 
	 */
	
    public static String countAndSay(int n) {
        StringBuilder str = new StringBuilder("1");
        
        for (int i = 1; i < n; i++) {
        	StringBuilder next = new StringBuilder();
    		int count = 1;
        	for (int j = 0; j < str.length(); j++){
        		if (j+1 < str.length() && str.charAt(j) == str.charAt(j+1)) {
        			count++;
        		} else {
        			next.append(count);
        			next.append(str.charAt(j));
        			count = 1;
        		}
        	}
        	str = next;
        }
        
        return str.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(countAndSay(6));
	}
}
