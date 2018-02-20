import java.util.ArrayList;
import java.util.List;


public class Solution {

	/*
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
	 * of rows like this: (you may want to display this pattern in a fixed font for 
	 * better legibility)
	 * P   A   H   N
	 * A P L S I I G
	 * Y   I   R
	 * 
	 * And then read line by line: "PAHNAPLSIIGYIR"
	 * Write the code that will take a string and make this conversion given a number 
	 * of rows:
	 * 
	 * string convert(string text, int nRows);
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 */
	
	public static void main(String[] args) {
		String test = "ABCD";
		System.out.println(test);
		String convertedStr = convert(test, 3);
		System.out.println(convertedStr);
	}
	
	
    public static String convert(String s, int nRows) {
    	
    	int len = s.length();
    	int i = 0;
    	StringBuilder[] convertedString = new StringBuilder[nRows];
    	
    	for(int k = 0; k < nRows; k++) {
    		convertedString[k] = new StringBuilder();
    	}
    	
    	while(i < len) {
    		for (int k = 0; k < nRows && i < len; k++) {
    			convertedString[k].append(s.charAt(i));
    			i++;
    		}
    		for (int k = nRows-2; k >= 1 && i < len; k--) {
    			convertedString[k].append(s.charAt(i));
    			i++;
    		}
    	}
    	
    	for (int j = 1; j < nRows; j++) {
    		convertedString[0].append(convertedString[j]);
    	}
    	
    	return convertedString[0].toString();
    }
}
