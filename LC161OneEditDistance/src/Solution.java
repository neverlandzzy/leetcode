
public class Solution {
	/*
	 * Given two strings S and T, determine if they are both one edit distance apart.
	 */
	
    public static boolean isOneEditDistance(String s, String t) {
        if (s.length() < t.length()) {
            return isOneEditDistance(t, s);
        }
        
        if (s.length() > t.length() + 1) {
            return false;
        }
        
        int i = 0;
        int n = t.length();
        
        while (i < n && s.charAt(i) == t.charAt(i)) {
            i++;
        }
        
        if (i == n) {
            return s.length() != t.length();
        }
        
        if (s.length() == t.length()) {
            i++;
            while (i < n && s.charAt(i) == t.charAt(i)) {
                i++;
            }
        } else {
            while (i < n && s.charAt(i + 1) == t.charAt(i)) {
                i++;
            }
        }
        
        return i == n;
    }
    
    /*
    public static boolean isOneEditDistance(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        
        if (sLength > tLength) return isOneEditDistance(t, s);
        
        if (tLength - sLength > 1) return false;
        
        int i = 0;
        
        while (i < sLength && s.charAt(i) == t.charAt(i)) {
        	i++;
        }
        
        // Appending
        if (i == sLength) {
        	if (i + 1 == tLength) {
        		return true;
        	} else {
        		return false;
        	}
        } else {
        	// Modify
        	if (sLength == tLength) {
        		i++;
        		while (i < sLength && s.charAt(i) == t.charAt(i)) {
                	i++;
                }
        	// Insert	
        	} else {
        		while (i < sLength && s.charAt(i) == t.charAt(i+1)) {
        			i++;
        		}
        	}
        }
        
        return i == sLength;
        
    }
    */
    
    public static void main(String[] args) {
		String s = "test";
		String t = "text";
		String m = "tst";
		String n = "none";
		
		System.out.println(isOneEditDistance(s,t));
		System.out.println(isOneEditDistance(s,m));
		System.out.println(isOneEditDistance(s,n));
	}
}
