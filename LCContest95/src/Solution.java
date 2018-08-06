
public class Solution {
	
	// LC486
    public static boolean stoneGame(int[] piles) {
		int[][] dp = new int[piles.length][piles.length];
        for (int len = 1; len < piles.length; len++) {
            for (int start = 0; start + len < piles.length; start++) {
                int end = start + len;
                dp[start][end] = Math.max(piles[start] - dp[start + 1][end], piles[end] - dp[start][end - 1]);
            }
        }
		return dp[0][piles.length - 1] >= 0;        
    }

	
    public static int nthMagicalNumber(int N, int A, int B) {
        int a = 1;
        int b = 1;
        int counter = 0;
        
        int M = 1000000007;
        
        int result = 1;
        
        while (counter < N) {
        	int tmp1 = (A % M * a % M) % M;
        	int tmp2 = (B % M * b % M) % M;
        	
	        if (tmp1 < tmp2) {    
	        	result = (tmp1) % M;
	        	if ((tmp1) % B == 0) {
	        		counter--;
	        	}
	        	a++;
	        	//System.out.println(a);
	        } else {
	        	result = (tmp2) % M;

	        	b++;
	        }
	       //System.out.println(result);
	       counter++;
        }
        System.out.println(A * a);
        System.out.println(A * a % M);
        System.out.println(b);
        System.out.println(B*b);
        System.out.println((B % M) * (b % M));
        System.out.println(B * b % M);
        return result;
    }
    
    
	public static void main(String[] args) {
		int[] test11 = {5,3,4,5};
		//System.out.println(stoneGame(test11));
		
		/*
		System.out.println(nthMagicalNumber(1, 2, 3));
		System.out.println(nthMagicalNumber(4, 2, 3));
		System.out.println(nthMagicalNumber(5, 2, 4));
		System.out.println(nthMagicalNumber(3, 6, 4));
		*/
		//System.out.println(nthMagicalNumber(1000000000, 40000, 40000)); //999720007
		System.out.println(nthMagicalNumber(1000000000, 39999, 40000));
		
	}

}
