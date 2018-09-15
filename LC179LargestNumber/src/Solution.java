import java.util.Arrays;
import java.util.Comparator;


public class Solution {
	/*
	 * Given a list of non negative integers, arrange them such that they form the largest number.
	 * 
	 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
	 * 
	 * Note: The result may be very large, so you need to return a string instead of an integer.
	 */

    public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // Arrays.sort(arr,comparator); will not work on primitives. 因此要转成String
        Arrays.sort(strs, new Comparator<String>(){
           public int compare(String s1, String s2) {
               String str1 = s1 + s2;
               String str2 = s2 + s1;
               
               for (int i = 0; i < str1.length(); i++) {
                   if (str1.charAt(i) > str2.charAt(i)) {
                       return -1;
                   } else if (str1.charAt(i) < str2.charAt(i)) {
                       return 1;
                   }
               }
               
               return 0;
           } 
        });
        
        // corner case: {0,0}
        if (strs[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (String s: strs) {
            sb.append(s);
        }
        
        return sb.toString();
    }
    
    /*
    public static String largestNumber(int[] nums) {
       
    	String[] numbers = new String[nums.length];
    	
    	for (int i = 0; i < nums.length; i++) {
    		numbers[i] = Integer.toString(nums[i]);
    	}
    	
    	Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String str1 = o1 + o2;
				String str2 = o2 + o1;
				
				for (int i = 0; i < str1.length(); i++) {
					if (str1.charAt(i) > str2.charAt(i)) {
						return -1;
					} else if (str1.charAt(i) < str2.charAt(i)){
						return 1;
					}
				}								
				return 0;
			}      	
        };
        
        Arrays.sort(numbers, comparator);
        
        StringBuilder result = new StringBuilder();
        
        for (String s: numbers) {
        	result.append(s);
        }

        int i = 0;
        while ((i < result.length() - 1) && (result.charAt(i) == '0')) {
        	i++;
        }
        return result.substring(i);
    }
    */
    public static void main(String[] args) {
		int test[] = {0,0};
		
		System.out.println(largestNumber(test));
	}
}
