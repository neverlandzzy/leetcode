package oa;

import java.util.HashSet;
import java.util.Set;

public class TwoSum {
	/*
	 * 给一个数组，a1, a2, a3,...,an， 找出所有的(i, j)的个数， 使得ai +k = aj;
	 * 比如： 1，2，3，4； k=2； 则答案为2 （1+2=3， 2+2=4）
	 * 
	 * LC1 变种
	 */
	
	public static int twoSum(int[] nums, int k) {
		int counter = 0;
		
		Set<Integer> set = new HashSet<>();
		
		for (int n: nums) {
			if (set.contains(n + k) || set.contains(n - k)) {
				counter++;
			}
			set.add(n);
		}
		
		return counter;
	}
	
	public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 4};
		int[] test2 = {2, 5, 3, 4, 1};
		
		System.out.println(twoSum(test1, 2));
		System.out.println(twoSum(test2, 2));
		
	}
}
