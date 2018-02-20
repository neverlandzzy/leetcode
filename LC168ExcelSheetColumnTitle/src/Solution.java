
public class Solution {
	/*
	 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
	 * For example:
	 * 
	 *     1 -> A
	 *     2 -> B
	 *     3 -> C
	 *       ...
	 *     26 -> Z
	 *     27 -> AA
	 *     28 -> AB 
	 */
	
    public static String convertToTitle(int n) {
        StringBuilder excelTitle = new StringBuilder();
        
        while (n > 0) {
        	n--;
        	excelTitle.append((char)('A' + n % 26));
        	n /= 26;
        }
        return excelTitle.reverse().toString();
    }
    
    public static void main(String[] args) {
		System.out.println(convertToTitle(1));
		System.out.println(convertToTitle(5));
		System.out.println(convertToTitle(26));
		System.out.println(convertToTitle(27));
		System.out.println(convertToTitle(1000));
	}
}
