
public class Solution {
	/*
	 * Given two binary strings, return their sum (also a binary string).
	 * 
	 * For example,
	 * a = "11"
	 * b = "1"
	 * Return "100".
	 * 
	 */
	
    public static String addBinary(String a, String b) {
    	StringBuilder result = new StringBuilder();
    	int carry = 0;
    	int sum = 0;
    	
    	for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
    		int m = (i < a.length()) ? Character.getNumericValue(a.charAt(a.length() - i - 1)) : 0;
    		int n = (i < b.length()) ? Character.getNumericValue(b.charAt(b.length() - i - 1)) : 0;

    		sum   = (m + n + carry) % 2;
    		carry = (m + n + carry) / 2;
    		
    		result.append(sum);
    	}
    	if (carry == 1) {
    		result.append(carry);
    	}
    	return result.reverse().toString();
    }
	
    
    // Solution 2: 比较直观，容易想到
    /*
    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        } 
        
        if (b == null || b.length() == 0) {
            return a;
        } 
        
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        
        while (i >= 0 && j >= 0) {
            int add1 = Character.getNumericValue(a.charAt(i));
            int add2 = Character.getNumericValue(b.charAt(j));
            
            int sum = add1 + add2 + carry;
            if (sum >= 2) {
                carry = 1;
                sum %= 2;
            } else {
                carry = 0;
            }
            
            sb.append(sum);
            i--;
            j--;
        }
        
        if (i < 0) {
            while (j >= 0) {
                int add = Character.getNumericValue(b.charAt(j));
                int sum = add + carry;
                
                if (sum >= 2) {
                    carry = 1;
                    sum %= 2;
                } else {
                    carry = 0;
                }
            
                sb.append(sum);
                j--;
            }
        }
        
        if (j < 0) {
            while (i >= 0) {
                int add = Character.getNumericValue(a.charAt(i));
                int sum = add + carry;
               
                if (sum >= 2) {
                    carry = 1;
                    sum %= 2;
                } else {
                    carry = 0;
                }
            
                sb.append(sum);
                i--;
            }
        }
        
        if (carry == 1) {
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
	*/

    
	public static void main(String[] args) {
		String a = "11";
		String b = "111";
		
		System.out.println(addBinary(a,b));
	}

	
}

