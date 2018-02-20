import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
	 * Find all strobogrammatic numbers that are of length = n.
	 * 
	 * For example,
	 * Given n = 2, return ["11","69","88","96"].
	 */
	
    public static List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private static List<String> helper(int m, int n) {
    	if (n == 0) {
    		return new ArrayList<String>(Arrays.asList(""));
    	}
    	
    	if (n == 1) {
    		return new ArrayList<String>(Arrays.asList("0", "1", "8"));
    	}
    	
    	List<String> list = helper(m, n - 2);
    	List<String> result = new ArrayList<>();
    	
    	for (int i = 0; i < list.size(); i++) {
    		String s = list.get(i);
    		
    		if (n != m) {
    			result.add("0" + s + "0");
    		}
    		result.add("1" + s + "1");
    		result.add("8" + s + "8");
    		result.add("6" + s + "9");
    		result.add("9" + s + "6");
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(findStrobogrammatic(0));
		System.out.println(findStrobogrammatic(2));
		System.out.println(findStrobogrammatic(3));
	}
}
