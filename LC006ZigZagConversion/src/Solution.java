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
    public static String convert(String s, int numRows) {
        int n = s.length();
        int i = 0;
        StringBuilder[] convertedStrings = new StringBuilder[numRows];
        
        for (int k = 0; k < numRows; k++) {
            convertedStrings[k] = new StringBuilder();
        }
        
        while (i < n) {
            for (int k = 0; k < numRows && i < n; k++) {
                convertedStrings[k].append(s.charAt(i));
                i++;
            }
            
            for (int k = numRows - 2; k >= 1 && i < n; k--) {
                convertedStrings[k].append(s.charAt(i));
                i++;
            }
        }
        
        for (int j = 1; j < numRows; j++) {
            convertedStrings[0].append(convertedStrings[j]);
        }
        
        return convertedStrings[0].toString();
    }
    
	
	public static void main(String[] args) {
		String test = "ABCD";
		System.out.println(test);
		String convertedStr = convert(test, 3);
		System.out.println(convertedStr);
	}
}
