package Sums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	/*
	 * http://www.1point3acres.com/bbs/thread-296345-1-1.html
	 * 给一个int[], 输出所有 A + B = C + D 的unique index pairs, AB 下标不同，CD 下标不同，如果输出ABCD就不能输出CDAB, 可以输出ABDC BADC BACD
	 */
	
	
	public static List<int[]>  find(int[] nums) {
		int n = nums.length;
		List<int[]>  result = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int sum = nums[i] + nums[j];
				Map<Integer, List<Integer>> map = new HashMap<>(); //number -> index list				
				for (int k = i + 1; k < n; k++) {
                    if (k == j) { 
                        continue;
                    }
                    List<Integer> index = map.get(sum - nums[k]);
                    if (index != null) {
                    	for (int id: index) {
                    		addPairs(result, new int[]{i, j, k, id});
                    	}
                    }
                    
                    List<Integer> curIndex = map.get(nums[k]);
                    if (curIndex == null) {
                    	curIndex = new ArrayList<>();
                        map.put(nums[k], curIndex);
                    }
                    curIndex.add(k); //reference, = map.get(nums[k]).add(k);
				}
			}
		}
		
		return result;
	}
	
    private static void addPairs(List<int[]> result, int[] pair) {
        int[] p1 = new int[]{pair[1], pair[0], pair[2], pair[3]}; //BACD
        int[] p2 = new int[]{pair[1], pair[0], pair[3], pair[2]}; //BADC
        int[] p3 = new int[]{pair[0], pair[1], pair[3], pair[2]}; //ABDC

        result.add(pair);
        result.add(p1);
        result.add(p2);
        result.add(p3);
    }
	
	
	public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 4};
		int[] test2 = {1, 1, 1, 1, 1, 1, 1, 1};
		int[] test3 = {2, 2, 0, 4, 1, 3};
		int[] test4 = {1, 2, 2, 1};
		
		List<int[]> result1 = find(test1);
		List<int[]> result2 = find(test2);
		List<int[]> result3 = find(test3);
		List<int[]> result4 = find(test4);
		
		print(result1);
		//print(result2);
		//print(result3);
		print(result4);
	}
	
	private static void print(List<int[]> result) {
		for (int[] p: result) {
			System.out.print("[" + p[0] + "," + p[1] + "," + p[2] + "," + p[3] + "]");
			System.out.println();
		}
		System.out.println("========");
	}
	
	
}
