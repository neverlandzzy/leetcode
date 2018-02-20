
public class Solution {
	/*
	 * Given a non-negative number represented as an array of digits, plus one to the number.
	 * The digits are stored such that the most significant digit is at the head of the list.
	 */
	
    public static int[] plusOne(int[] digits) {
    	
    	for (int i = digits.length-1 ; i >= 0; i--) {
        	if (digits[i] + 1 < 10) {
        		digits[i] = digits[i] + 1;
        		return digits;
        	} else {
        		digits[i] = 0;
        		if (i == 0) {
        			int[] res = new int[digits.length + 1];
        			res[0] = 1; 
        			return res;
        		}
        	}
    	}

    	return digits;
    }
    
    public static void main(String[] args) {
		int[] test1 = {3,5,6};
		int[] test2 = {9,9,9};
		int[] test3 = {0};
		
		
		int [] res1 = plusOne(test1);
		int [] res2 = plusOne(test2);
		int [] res3 = plusOne(test3);
		
		for(int k : res1) {
			System.out.print(k);
		}
		
		System.out.println();
		
		for(int k : res2) {
			System.out.print(k);
		}
		
		System.out.println();
		
		for(int k : res3) {
			System.out.print(k);
		}
	}
    
}
