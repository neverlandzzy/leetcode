
public class Solution {
	/*
	 * Convert a non-negative integer to its english words representation. 
	 * Given input is guaranteed to be less than 2^31 - 1.
	 * 
	 * For example,
	 * 123 -> "One Hundred Twenty Three"
	 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
	 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
	 * 
	 * 
	 * Hint:
	 * 1. Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
	 * 2. Group the number by thousands (3 digits). You can write a helper function that takes a number 
	 * less than 1000 and convert just that chunk to words.
	 * 3. There are many edge cases. What are some good test cases? Does your code work with input such 
	 * as 0? Or 1000010? (middle chunk is zero and should not be printed out)
	 */
	
	private static final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public static String numberToWords(int num) {
        if (num == 0) {
        	return "Zero";
        }
        
        String word = "";
        int i = 0;
        
        while (num > 0) {
        	if (num % 1000 != 0) {
        		word = helper(num % 1000) + thousands[i] + " " + word;
        	}
        	
        	num = num / 1000;
        	i++;
        }
        
        return word.trim();
    }
    
    private static String helper(int num) {
    	if (num == 0) {
    		return "";
    	} else if (num < 20) {
    		return belowTwenty[num] + " ";
    	} else if (num < 100) {
    		return tens[num/10] + " " + helper(num % 10);
    	} else {
    		return belowTwenty[num/100] + " Hundred " + helper(num % 100);
    	}
    	
    }
    
    public static void main(String[] args) {
    	
		System.out.println(numberToWords(123));
		System.out.println(numberToWords(12345));
		System.out.println(numberToWords(1234567));
		System.out.println(numberToWords(0));
		System.out.println(numberToWords(1000010));
		
    	/*
    	System.out.println(helper(123));
    	System.out.println(helper(100));
    	System.out.println(helper(10));
    	*/
		
	}
}
