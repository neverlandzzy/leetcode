
public class Solution {
	/*
	 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
	 * 'A' : Absent.
	 * 'L' : Late.
	 * 'P' : Present.
	 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
	 * 
	 * You need to return whether the student could be rewarded according to his attendance record.
	 * 
	 * Example 1:
	 * Input: "PPALLP"
	 * Output: True
	 * 
	 * Example 2:
	 * Input: "PPALLL"
	 * Output: False
	 */
	
    public static boolean checkRecord(String s) {
    	
        if (s == null || s.length() <= 1) {
    		return true;
    	}
    	
        int countA = 0;
        int countL = 0;
        
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (c == 'A') {
        		countA++;
                if (countA > 1) {
                    return false;
                }
        	} else if (i > 0 && c == 'L' && s.charAt(i - 1) == 'L'){
        		countL++;
                if (countL >= 2) {
                    return false;
                }
        	} else {
        		countL = 0;
        	}
        }
         
        return true;
    }
    
    public static void main(String[] args) {
		System.out.println(checkRecord("PPALLP"));
		System.out.println(checkRecord("PPALLL"));
	}
}
