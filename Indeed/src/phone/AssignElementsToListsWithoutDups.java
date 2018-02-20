package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AssignElementsToListsWithoutDups {
	/*
	 * 给一个list， 如何把里面的字符分配到尽量少的子list里，并且每个子list没有重复元素。
	 * 比如
	 * ['a','b','c','a','a','b']， 可以分成['a', 'b', 'c'], ['a', 'b'], ['a']
	 * ['a', 'a', 'a', 'b', 'b', 'b']，可以分成['a', 'b'], ['a', 'b'], ['a', 'b']
	 * 
	 * 类似LC358，但简单很多
	 */
	
	// Time: O(26 * n)
	// 输入字符
	public static List<List<Character>> arrange(List<Character> list) {
		// assume inputs are lower case only
		List<List<Character>> result = new ArrayList<>();
		
		if (list == null || list.size() == 0) {
			return result;
		}
		
		int[] map = new int[26];
		int size = 0;
		
		for (char c: list) {
			map[c - 'a']++;
			size = Math.max(size, map[c - 'a']);
		}
		
		
		for (int i = 0; i < size; i++) {
			List<Character> subList = new ArrayList<>();
			
			for (int j = 0; j < 26; j++) {
				if (map[j] > 0) {
					subList.add((char)(j + 'a'));
					map[j]--;
				}
			}
			result.add(subList);
		}
		
		return result;
	}
	
	// Case2: 输入数字
	public static List<List<Integer>> arrangeInt(List<Integer> list) {
		List<List<Integer>> result = new ArrayList<>();
		
		if (list == null || list.size() == 0) {
			return result;
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		int size = 0;
		
		// O(n)
		for (int k: list) {
			map.put(k, map.getOrDefault(k, 0) + 1);
			size = Math.max(size, map.get(k));
		}
		
		// output size * map.key.size()
		for (int i = 0; i < size; i++) {
			List<Integer> subList = new ArrayList<>();
			
			for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
				if (entry.getValue() > 0) {
					subList.add(entry.getKey());
					entry.setValue(entry.getValue() - 1);
				}
			}
			
			result.add(subList);
		}
		
		return result;
	}
	
	
	// 保证O(n)的解法
	public static List<List<Integer>> arrangeIntII(List<Integer> source) {
        List<List<Integer>> result = new ArrayList<>();
        while( source.size() > 0) {
                int i = 0;
                List<Integer> list = new ArrayList<>();
                Set<Integer> set = new HashSet<>();
                while(i<source.size()) {
                        if(!set.contains(source.get(i))) {
                                list.add(source.get(i));
                                set.add(source.get(i));
                                source.remove(i);
                        } else {
                                i++;
                        }
                }
                result.add(list);
        }
        return result;
	}
	
	public static void main(String[] args) {
		List<Character> test1 = new ArrayList<>(Arrays.asList('a','b','c','a','a','b'));
		List<Character> test2 = new ArrayList<>(Arrays.asList('a', 'a', 'a', 'b', 'b', 'b'));
		
		System.out.println(arrange(test1));
		System.out.println(arrange(test2));
		
		List<Integer> test3 = new ArrayList<>(Arrays.asList(1,2,3,2,3,4,4,5, 1, 1, 1, 1, 1, 1, 1, 1));
		System.out.println(arrangeInt(test3));
		System.out.println(arrangeIntII(test3));

	}
}
