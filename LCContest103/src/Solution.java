import java.util.Arrays;


public class Solution {
	
    public static int smallestRangeI(int[] A, int K) {
        int min = 10001;
        int max = 0;
        
        for (int a: A) {
        	min = Math.min(a, min);
        	max = Math.max(a, max);
        }
        
        int diff = max - min;
        
        return diff <= K * 2 ? 0 : diff - K * 2; 
    }
    
    public static int smallestRangeII(int[] A, int K) {
    	Arrays.sort(A);
    	int n = A.length;
    	
    	double med = 0;
    	
    	if (n % 2 != 0) {
    		med = A[n / 2];
    	} else {
    		med = (A[n / 2] + A[n / 2 - 1]) / 2.0;
    	}
    	
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	
    	for (int a: A) {
    		if (a <= med) {
    			a += K;
    		} else {
    			a -= K;
    		}
    		
        	min = Math.min(a, min);
        	max = Math.max(a, max);    		    		
    	}
    	
    	return max - min;
    }
    
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int max = 0;
        int nextMove = 0;
        int result = 0;
        int pos = 0;

        boolean flag = true;
        
        for (int i = n - 1; i >= 0; i--) {
        	if (nextMove == pos) {
        		result++;
        		nextMove = max;
        	}
        	
        	if (nextMove >= n * n - 1) {
        		return result;
        	}
        	
        	if (flag) {
	        	for (int j = 0; j < n; j++) {
	        		int next = ((n - i - 1) * n + j % n) + 6;
	        		System.out.println("i =  " + i + " j = " + j + " next = " + next);
	        		if (board[i][j] != -1) {
	        			next = Math.max(board[i][j], next);
	        		} 
	        		max = Math.max(max, next);
	        	}
	        	flag = !flag;
        	} else {
	        	for (int j = n - 1; j >= 0; j--) {
	        		int next = ((n - i - 1) * n + (n - j - 1) % n) + 6;
	        		System.out.println("i =  " + i + " j = " + j + " next = " + next);
	        		
	        		if (board[i][j] != -1) {
	        			next = Math.max(board[i][j], next);
	        		} 
	        		max = Math.max(max, next);	        		
	        	}        		
        	}
        }
        
        return -1;
        
    }
    
	public static void main(String[] args) {
		/*
		int[] test11 = {1};
		int[] test12 = {0, 10};
		int[] test13 = {1, 3, 6};
		System.out.println(smallestRangeI(test11, 0));
		System.out.println(smallestRangeI(test12, 2));
		System.out.println(smallestRangeI(test13, 3));
		
		int[] test31 = {1};
		int[] test32 = {0, 10};
		int[] test33 = {1, 3, 6};
		int[] test34 = {3, 2, 4, 2};

		System.out.println(smallestRangeII(test31, 0));
		System.out.println(smallestRangeII(test32, 2));
		System.out.println(smallestRangeII(test33, 3));
		System.out.println(smallestRangeII(test34, 1));
		

		
		int[][] test21 = {{-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1}, {-1,35,-1,-1,13,-1}, {-1,-1,-1,-1,-1,-1}, {-1,15,-1,-1,-1,-1}};
		System.out.println(snakesAndLadders(test21));
		*/
		
		int[] person1 = {0,1,1,0,0,1,0};
		int[] time1 = {0,5,10,15,20,25,30};
		TopVotedCandidate tvc = new TopVotedCandidate(person1, time1);
		
		System.out.println(tvc.q(3));
		System.out.println(tvc.q(12));
		System.out.println(tvc.q(25));
		System.out.println(tvc.q(15));
		System.out.println(tvc.q(24));
		System.out.println(tvc.q(8));
	}
}
