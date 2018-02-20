
public class Solution {
	
    public static boolean isOneBitCharacter(int[] bits) {

    }
    
    public static int compress(char[] chars) {
    	if (chars == null || chars.length == 0) {
    		return 0;
    	}
    	
    	int n = chars.length;
    	int counter = 1;
    	int len = 0;
    	int pre = 0;
    	
        for (int i = 1; i < n; i++) {
        	if (chars[i] == chars[i - 1]) {
        		counter++;
        	} else if (counter > 1) {
        		int j = pre + 1;

	        	String s = String.valueOf(counter);
	        	for (int k = 0; k < s.length(); k++) {
	        		chars[j] = s.charAt(k);
	        		j++;
	        	}
	        	
	        	chars[j] = chars[i];
	        	len += s.length() + 1;
	        	pre = j;
	        	counter = 1;
        	} else {
        		
        		pre++;
        		
        		len++;
        		chars[pre] = chars[i];
        	}
        }
        //System.out.println("pre = " + pre);
        if (counter > 1) {
        	int j = pre + 1;
        	String s = String.valueOf(counter);
        	for (int k = 0; k < s.length(); k++) {
        		chars[j] = s.charAt(k);
        		j++;
        	}
        	len += s.length() + 1;
        }
        
        if (pre == len) {
        	len++;
        }

        /*
        for (char c: chars) {
        	System.out.print(c + ", ");
        }
        
        System.out.println();
        */
        return len;
    }
    
    
    public static int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m][n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
        	dp[i][0] = A[i] == B[0] ? 1 : 0;
        }
        
        for (int i = 0; i < n; i++) {
        	dp[0][i] = B[i] == A[0] ? 1 : 0;
        }
        	
        for (int i = 1; i < m ;i++) {
        	for (int j = 1; j < n; j++) {
        		if (A[i] == B[j]) {
        			//System.out.println("A[i] = " + A[i]);
        			dp[i][j] = dp[i - 1][j - 1] + 1;
        		} else {
        			dp[i][j] = 0;
        		}
        		
        		max = Math.max(max, dp[i][j]);
        	}
        }
        
        for (int[] l: dp) {
        	for (int k: l) {
        		System.out.print(k + ", ");
        	}
        	System.out.println();
        }
       
        return max;
    }
    
	public static void main(String[] args) {
		int[] test11 = {1, 0, 0};
		int[] test12 = {1, 1, 1, 0};
		
		//System.out.println(isOneBitCharacter(test11));
		//System.out.println(isOneBitCharacter(test12));
		
		/*
		char[] test21 = {'a','a','b','b','c','c','c'};
		char[] test22 = {'a', 'b'};
		char[] test23 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
		char[] test24 = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
		char[] test25 = {'a','b','c','d','e','f','g','g','g','g','g','g','g','g', 'g', 'g', 'g', 'g','a','b','c'};
		
		System.out.println(compress(test21));
		System.out.println(compress(test22));
		System.out.println(compress(test23));
		System.out.println(compress(test24));
		System.out.println(compress(test25));
		*/
		
		int[] test31A = {1,2,3,2,1};
		int[] test31B = {3,2,1,4,7};
		int[] test32A = {0, 1, 1, 1, 1};
		int[] test32B = {1, 0, 1, 0, 1};
		System.out.println(findLength(test31A, test31B));
		System.out.println(findLength(test32A, test32B));
	}
}
