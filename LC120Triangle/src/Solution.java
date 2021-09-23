import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a triangle, find the minimum path sum from top to bottom. Each step you 
	 * may move to adjacent numbers on the row below.
	 * 
	 * For example, given the following triangle
	 * 
	 * [
	 *      [2],
	 *     [3,4],   
	 *    [6,5,7],
	 *   [4,1,8,3]
	 * ]
	 * 
	 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	 * 
	 * Note:
	 * Bonus point if you are able to do this using only O(n) extra space, where n is the 
	 * total number of rows in the triangle.
	 */
	
    public static int minimumTotal(List<List<Integer>> triangle) {
    	
    	// Solution 1: Time: O(n^2)  Space: O(n^2)
    	
    	/*
        int n = triangle.size();
        
        int[][] d  = new int[n][n]; // d[i][j]: 从(i, j)到最底层的距离
        
        for (int i = 0; i < n; i++) {
        	d[n-1][i] = triangle.get(n-1).get(i);
        }
        
        for (int i = n-2; i >= 0; i--) {
        	for (int j = i; j >= 0; j--) {
        		d[i][j] = Math.min(d[i+1][j], d[i+1][j+1]) + triangle.get(i).get(j);
        	}
        }
        
        return d[0][0];
        */
    	
    	// Solution 2: Time: O(n^2)  Space: O(n)
    	
    	 int n = triangle.size();
    	 
    	 int[] d = new int[n];
    	 
         for (int i = 0; i < n; i++) {
         	d[i] = triangle.get(n-1).get(i);
         }
         
         for (int i = n-2; i >= 0; i--) {
        	 for (int j = 0; j <= i; j++) {  // 这里要从后往前算，因为否则d[j]已经被改过，需要一个tmp去保存未改之前的d[j]
        		 d[j] = Math.min(d[j], d[j+1]) + triangle.get(i).get(j);  
        	 }
         }
         

         return d[0];
    }
    
    public static void main(String[] args) {
    	List<List<Integer>> test = new ArrayList<List<Integer>>();
    	List<Integer> tmp1 = new ArrayList<Integer>();
    	List<Integer> tmp2 = new ArrayList<Integer>();
    	List<Integer> tmp3 = new ArrayList<Integer>();
    	List<Integer> tmp4 = new ArrayList<Integer>();
    	
    	tmp1.add(2);
    	tmp2.add(3);
    	tmp2.add(4);
    	tmp3.add(6);
    	tmp3.add(5);
    	tmp3.add(7);
    	tmp4.add(4);
    	tmp4.add(1);
    	tmp4.add(8);
    	tmp4.add(3);
    	
    	test.add(tmp1);
    	test.add(tmp2);
    	test.add(tmp3);
    	test.add(tmp4);
    	
    	System.out.println(test);
    	
    	System.out.println(minimumTotal(test));

    	
	}
}
