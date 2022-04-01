import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * Given two sparse matrices A and B, return the result of AB.
	 * 
	 * You may assume that A's column number is equal to B's row number.
	 * 
	 * Example:
	 * 
	 * A = [
	 *   [ 1, 0, 0],
	 *   [-1, 0, 3]
	 * ]
	 * 
	 * B = [
	 *   [ 7, 0, 0 ],
	 *   [ 0, 0, 0 ],
	 *   [ 0, 0, 1 ]
	 * ]
	 * 
	 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
	 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
	 *                   | 0 0 1 |
	 */
	
	// http://www.cnblogs.com/grandyang/p/5282959.html
	// https://liut2.gitbooks.io/crazystuff/content/leetcode_311_sparse_matrix_multiplication.html
	// https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76150/Java-and-Python-Solutions-with-and-without-Tables
	
	// Regular way for matrix multiplication 但对于sparse matrices，浪费时间 -- TLE 
	// Time: O(m1 * n1 * n2)
	/*
    public static int[][] multiply(int[][] A, int[][] B) {
        int m1 = A.length;
        int n1 = A[0].length; // A[0].length == B.length
        int n2 = B[0].length;
        int[][] result = new int[m1][n2];
        
        for (int i = 0; i < m1; i++) {
        	for (int j = 0; j < n2; j++) {
        		for (int k = 0; k < n1; k++) {
        			result[i][j] += A[i][k] * B[k][j];
        		}
        	}
        }
        
        return result;
    }
    */
	
	// Solution 1: 在上面方法基础上改进， 只计算A[i][k]和B[k][j]不为0的情形
	// Time: worst case 一样， 即不是sparse matrix O(m1 * n1 * n2)
	/*
    public static int[][] multiply(int[][] A, int[][] B) {
        int m1 = A.length;
        int n1 = A[0].length; // A[0].length == B.length
        int n2 = B[0].length;
        int[][] result = new int[m1][n2];
        
        // 为了处理当A[i][k]为0时skip掉loop，交换j，k的内外位置
        for (int i = 0; i < m1; i++) {
        	for (int k = 0; k < n1; k++) {
        		if (A[i][k] != 0) {
        			for (int j = 0; j < n2; j++) {
        				if (B[k][j] != 0) {
        					result[i][j] += A[i][k] * B[k][j];
        				}
        			}
        		}
        	}
        }
        
        return result;
    }
    */
	
    // Solution 2: Using HashMap to record non-zero entries only
	// Time: worst case 一样， 即不是sparse matrix O(m1 * n1 * n2)
    public static int[][] multiply(int[][] A, int[][] B) {
    	Map<Integer, Map<Integer, Integer>> tableA = new HashMap<>(); // map<i, map<j, A[i][j]>>
    	Map<Integer, Map<Integer, Integer>> tableB = new HashMap<>(); // map<i, map<j, B[i][j]>>
        int m1 = A.length;
        int n1 = A[0].length; // A[0].length == B.length
        int n2 = B[0].length;
        int[][] result = new int[m1][n2];
        
        for (int i = 0; i < m1; i++) {
        	for (int j = 0; j < n1; j++) {
        		if (A[i][j] != 0) {
        			if (!tableA.containsKey(i)) {
        				tableA.put(i, new HashMap<>());
        			}
        			tableA.get(i).put(j, A[i][j]);
        		}
        	}
        }
        
        for (int i = 0; i < n1; i++) {
        	for (int j = 0; j < n2; j++) {
        		if (B[i][j] != 0) {
        			if (!tableB.containsKey(i)) {
        				tableB.put(i, new HashMap<>());
        			}
        			tableB.get(i).put(j, B[i][j]);
        		}
        	}
        }

        
        //System.out.println(tableA);
        //System.out.println(tableB);
        
        for (int i: tableA.keySet()) {
        	for (int k: tableA.get(i).keySet()) {
        		if (tableB.containsKey(k)) {
        			for (int j: tableB.get(k).keySet()) {
        				result[i][j] += tableA.get(i).get(k) * tableB.get(k).get(j);
        			}
        		}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[][] test11 = {{1, 0, 0}, {-1, 0, 3}};
		int[][] test12 = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};
		
		int[][] result1 = multiply(test11, test12);
		print(result1);
	}
    
    private static void print(int[][] A) {
    	for (int[] a: A) {
    		for (int i: a) {
    			System.out.print(i + ", ");
    		}
    		System.out.println();
    	}
    	
    	System.out.println();
    }
}
