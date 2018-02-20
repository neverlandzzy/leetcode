package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArray {
	/*
	 * 【题目】intersection of two sorted lists
	 * 【follow up 1】有duplicate怎么办， 同basic解法
	 * 【follow up 2】其中一个list非常大怎么办
	 * 【follow up 3】找n个list的intersection
	 *  
	 * 
	 *  另外准备LC349, LC350 这两道题不是排序的input，用hashset/hashmap做
	 */
	
	// 1. 基本题， intersection of two sorted lists， 没有重复 
	// 2.【follow up 1】 有重复
	// 通用解法
	// Time: O(n1 + n2)
	// Space: O(1) -- 不考虑输出
	public static List<Integer> intersectionI(List<Integer> list1, List<Integer> list2) {
		List<Integer> result = new ArrayList<>();
		
		if (list1.size() == 0 || list2.size() == 0) {
			return result;
		}
		
		int n1 = list1.size();
		int n2 = list2.size();
		int i = 0;
		int j = 0;
		
		while (i < n1 && j < n2) {
			if (list1.get(i) < list2.get(j)) {
				i++;
			} else if (list1.get(i) > list2.get(j)) {
				j++;
			} else {
				result.add(list1.get(i));
				i++;
				j++;
			}
		}
		
		return result;
	}
	
	//【follow up 2】其中一个list非常大怎么办
	// 上述解法时间复杂度为O(n1 + n2)， 假如n1很大，那么可以对list2里每一个元素在list1中进行二分查找，确定其是否在list1中。
	// 这样时间复杂度变为O(n2 * logn1)， 当n1很大时，这个更好。 
	public static List<Integer> intersectionII(List<Integer> list1, List<Integer> list2) {
		List<Integer> result = new ArrayList<>();
		
		if (list1.size() == 0 || list2.size() == 0) {
			return result;
		}
		
		if (list1.size() < list2.size()) {
			return intersectionII(list2, list1);
		}
		
		for (int k: list2) {
			//int index = Collections.binarySearch(list1, k);
			int index = binarySearch(list1, k); //-- 自己写
			if ( index >= 0) {
				result.add(k);
				list1.remove(index); // 找到后要删掉不然没法处理重复
			}
			
		}
		
		return result;
	}
	
	private static int binarySearch(List<Integer> list, int k) {
		int start = 0;
		int end = list.size() - 1;
		
		while (start < end) {
			int mid = start + (end - start) / 2;
			
			if (list.get(mid) == k) {
				return mid;
			}
			
			if (list.get(mid) > k) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		
		return -1;
	}
	
	// 【follow up 3】找n个list的intersection
	
	public static List<Integer> intersectionIII(List<List<Integer>> lists) {
		List<Integer> result = new ArrayList<>();
		if (lists == null || lists.size() == 0) {
			return result;
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int k: lists.get(0)) {
			map.put(k, map.getOrDefault(k, 0) + 1);
		}
		
		for (List<Integer> list: lists) {
			
		
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("1. 输入有重复或者无重复, O(n1 + n2)");
		List<Integer> test11 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 5, 6, 7, 8));
		List<Integer> test12 = new ArrayList<>(Arrays.asList(2, 2, 5, 5, 7));
		System.out.println(intersectionI(test11, test12));
		System.out.println();
		
		System.out.println("2. 输入有重复或者无重复, O(n2 * logn1)");
		List<Integer> test21 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 5, 6, 7, 8));
		List<Integer> test22 = new ArrayList<>(Arrays.asList(2, 2, 5, 5, 7));
		System.out.println(intersectionII(test21, test22));
		System.out.println();
		
		
		System.out.println("3. 找n个list的intersection");
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 5, 6, 7, 8));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 2, 5, 5, 7));
		List<Integer> list3 = new ArrayList<>(Arrays.asList(3, 4, 5, 5, 7, 8));
		List<Integer> list4 = new ArrayList<>(Arrays.asList(4, 5, 2));
		List<List<Integer>> test31 = new ArrayList<>(Arrays.asList(list1, list2, list3, list4)); 
		System.out.println(intersectionIII(test31));
		System.out.println();
				
	}
}
