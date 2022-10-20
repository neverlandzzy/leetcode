
public class Solution {
/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
	
    public static String intToRoman(int num) {
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] M = {"", "M", "MM", "MMM"};

		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
    
    public static void main(String[] args) {
    	int test1 = 999;
    	int test2 = 324;
    	int test3 = 1;
    	int test4 = 0;
    	int test5 = 10;
    	int test6 = 2993;
    	
    	System.out.println(intToRoman(test1));
    	System.out.println(intToRoman(test2));
    	System.out.println(intToRoman(test3));
    	System.out.println(intToRoman(test4));
    	System.out.println(intToRoman(test5));
    	System.out.println(intToRoman(test6));
    }
}
