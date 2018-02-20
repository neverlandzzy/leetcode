
public class Solution {
	/*
	 *   Implement strStr().
	 *   
	 *   Returns the index of the first occurrence of needle in haystack, or -1 if needle 
	 *   is not part of haystack.
	 *   
	 */
	
    public static int strStr(String haystack, String needle) {
    	
        if (haystack.length() == 0 && needle.length() == 0) {
        	return 0;
        }
    	
    	if (haystack.length() < needle.length()) {
        	return -1;
        }
        
        int hLength = haystack.length();
        int nLength = needle.length();
        int j = 0;
        
        for (int i = 0; i < hLength; ) {
        	for(j = 0; j < nLength && i < hLength; ) {
        		//System.out.println("i = " + i + " j = " + j );
        		if(needle.charAt(j) == haystack.charAt(i)) {
        			j++;
        			i++;
        		} else {
        			i = i - j +1;
        			//System.out.println("##i - j +1 = " + i );
        			break;
        		}
           	 
        	}
        	if (j == nLength) {
        		return i - j;
        	}
        }
        return -1;
        
    }
    /*
    public static int strStr2(String haystack, String needle) {
	
 	    if (haystack.length() == 0 && needle.length() == 0) {
	    	return 0;
	    }
		
		if (haystack.length() < needle.length()) {
	    	return -1;
	    }
	    
	    int i = 0;
	    
	    while (i < haystack.length()) {
	        int j = 0;
	        int pos = i;
	        
	        while (j < needle.length() && i < haystack.length() && haystack.charAt(i) == needle.charAt(j)) {
	            i++;
	            j++;
	        }
	        
	        if (j == needle.length()) {
	            return pos;
	        } else if (i >= haystack.length()) {  // 没有这行会TLE
	        	break;
	        } else{
	            i = pos + 1;
	        }
	    }
	    
	    return -1;
    }
    */
    
    public static void main(String[] args) {
    	
		System.out.println(strStr("This is a test String", "a"));
		System.out.println(strStr("Nothing", "a"));
		System.out.println(strStr("This is test", "test"));
		System.out.println(strStr("", ""));
		System.out.println(strStr("mississippi", "issipi"));
	}
}
