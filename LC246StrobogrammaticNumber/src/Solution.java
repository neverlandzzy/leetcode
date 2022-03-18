
public class Solution {
	/**
	 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
	 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
	 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
	 */

	public static boolean isStrobogrammatic(String num) {
		if (num == null || num.length() == 0) {
			return true;
		}


		int i = 0;
		int j = num.length() - 1;

		while (i <= j) {
			char c1 = num.charAt(i);
			char c2 = num.charAt(j);

			if (c1 != c2) {
				if ((c1 == '6' && c2 == '9') || ((c1 == '9' && c2 == '6'))) {
					i++;
					j--;
					continue;
				} else {
					return false;
				}
			} else {
				if (c1 != '0' && c1 != '1' && c1 != '8') {
					return false;
				}
			}
			i++;
			j--;
		}

		return true;
	}
	/*
    public static boolean isStrobogrammatic(String num) {
    	
    	if (num == null || num.length() == 0) {
    		return true;
    	}
    	
        int[] map = new int[10];
        map[0] = map[1] = map[6] = map[8] = map[9] = 1;
        
        int i = 0; 
        int j = num.length() - 1;
        
        if (num.length() == 1) {
        	return num.charAt(0) == '0' || num.charAt(0) == '1' || num.charAt(0) == '8';
        }
        
        while (i <= j) {
        	if (map[num.charAt(i) - '0'] == 0) {
        		return false;
        	} else if (num.charAt(i) == '6') {
        		if (num.charAt(j) != '9') {
        			return false;
        		}
        		i++;
        		j--;
        	} else if (num.charAt(i) == '9') {
        		if (num.charAt(j) != '6') {
        			return false;
        		}
        		i++;
        		j--;
        	} else if (num.charAt(i) != num.charAt(j)){
        		return false;
        	} else {
        		i++;
        		j--;
        	}
        }
        
        return true;
    }
    */
    public static void main(String[] args) {
		String test1 = "619";
		String test2 = "818";
		String test3 = "1";
	    String test4 = "9116";
	    
	    System.out.println(isStrobogrammatic(test1));
	    System.out.println(isStrobogrammatic(test2));
	    System.out.println(isStrobogrammatic(test3));
	    System.out.println(isStrobogrammatic(test4));
	}
}
