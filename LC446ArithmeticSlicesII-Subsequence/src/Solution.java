import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two 
	 * consecutive elements is the same.
	 * 
	 * For example, these are arithmetic sequences:
	 * 
	 * 1, 3, 5, 7, 9
	 * 7, 7, 7, 7
	 * 3, -1, -5, -9
	 * The following sequence is not arithmetic.
	 * 
	 * 1, 1, 2, 5, 7
	 * 
	 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers 
	 * (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
	 * 
	 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. 
	 * In particular, this means that k ≥ 2.
	 * 
	 * The function should return the number of arithmetic subsequence slices in the array A.
	 * 
	 * The input contains N integers. Every integer is in the range of -2^31 and 2^31-1 and 0 ≤ N ≤ 1000. The output is guaranteed to 
	 * be less than 2^31-1.
	 * 
	 * Example:
	 * 
	 * Input: [2, 4, 6, 8, 10]
	 * 
	 * Output: 7
	 * 
	 * Explanation:
	 * All arithmetic subsequence slices are:
	 * [2,4,6]
	 * [4,6,8]
	 * [6,8,10]
	 * [2,4,6,8]
	 * [4,6,8,10]
	 * [2,4,6,8,10]
	 * [2,6,10]
	 */
	
	// https://discuss.leetcode.com/topic/67413/detailed-explanation-for-java-o-n-2-solution/12
	// http://www.cnblogs.com/grandyang/p/6057934.html
	
    public static int numberOfArithmeticSlices(int[] A) {
        List<HashMap<Integer, Integer>> mapList = new ArrayList<>();
        //每个map中存的是等差数列的差值和其长度之间的映射
        
        int result = 0;
        
        for (int i = 0; i < A.length; i++) {
        	HashMap<Integer, Integer> map = new HashMap<>();
        	mapList.add(map);
        	
        	for (int j = 0; j < i; j++) {
        		long diff = (long)(A[i]) - A[j]; 
        		if (diff >= Integer.MAX_VALUE || diff <= Integer.MIN_VALUE) {
        			continue;
        		}
        		
            	int d = (int) diff;

            	int c1 = 0; 
            	int c2 = 0;
            	
            	if (mapList.get(i).containsKey(d)) {
            		c1 = mapList.get(i).get(d);
            	}
            	if (mapList.get(j).containsKey(d)) {
            		c2 = mapList.get(j).get(d);
            	}
            	System.out.println("i = " + i + " j = " + j + " d = " + d + " c1 = " + c1 + " c2 = " + c2);
            	// 若c2有值，则说明已经可以构成等差数列
            	result += c2;
            	mapList.get(i).put(d, c1 + c2 + 1);
        	}

        }
        System.out.println(mapList);
        
        return result;
    }
    
    
    public static void main(String[] args) {
		int[] test = {2, 4, 6, 8, 10};
		//int[] test2 = {1, 2, 2, 3, 4};
		System.out.println(numberOfArithmeticSlices(test));
	}
}
