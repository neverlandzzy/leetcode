import java.util.HashMap;


public class Solution {
	/*
	 * Given a roman numeral, convert it to an integer.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 */
	
    public static int romanToInt(String s) {

    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	
    	map.put('I', 1);
    	map.put('V', 5);
    	map.put('X', 10);
    	map.put('L', 50);
    	map.put('C', 100);
    	map.put('D', 500);
    	map.put('M', 1000);
    	
    	int result = map.get(s.charAt(s.length()-1));
    	
    	for (int i = 0; i < s.length() - 1; i++) {
    		if(map.get(s.charAt(i)) >= map.get(s.charAt(i+1))) {
    			result += map.get(s.charAt(i));
    		} else {
    			result -= map.get(s.charAt(i));
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	String test1 = "CMXCIX";
    	String test2 = "CCCXXIV";
    	String test3 = "I";
    	String test5 = "X";
    	String test6 = "MMCMXCIII";
    	
    	System.out.println(romanToInt(test1));
    	System.out.println(romanToInt(test2));
    	System.out.println(romanToInt(test3));
    	System.out.println(romanToInt(test5));
    	System.out.println(romanToInt(test6));
    	
    }
}
