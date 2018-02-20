
public class Solution {
	/*
	 * Write a function to find the longest common prefix string amongst an array of strings.
	 */
	
	   public static String longestCommonPrefix(String[] strs) {
	        
	        StringBuilder sb = new StringBuilder();
	        
	        if (strs == null || strs.length == 0) {
	            return sb.toString();
	        }
	        
	        for (int i = 0; i < strs[0].length(); i++) {
	            char c = strs[0].charAt(i);
	            
	            for (String s: strs) {
	                if (i >= s.length()) {
	                    return sb.toString();
	                }
	                if (s.charAt(i) != c) {
	                    return sb.toString();
	                }
	            }
	            sb.append(c);
	        }
	        
	        return sb.toString();
	    }
	   
	   
	   public static void main(String[] args) {
		   String[] test = {"aaab", "aafk", "aaareds", "aafd", "aasedd", "aafk"};
		   String[] test2 = {};
		   String[] test3 = {"aca", "cba"};
		  
		   System.out.println(longestCommonPrefix(test));
		   System.out.println(longestCommonPrefix(test2));
		   System.out.println(longestCommonPrefix(test3));


	   }
}
