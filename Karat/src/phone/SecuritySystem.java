package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecuritySystem {
	/*
	 * 【第一问】 We are working on a security system for a badged-access room in our company's building
	 *          Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
	 *          All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exit.
	 *          All employees who didn't use their badge while entering the room  – they recorded an exit without a matching enter. 
	 *          badge_records = [
	 *        	  ["Martha",   "exit"],
	 *        	  ["Paul",     "enter"],
	 *         	  ["Martha",   "enter"],
	 *         	  ["Martha",   "exit"],
	 *         	  ["Jennifer", "enter"], 
	 *         	  ["Paul",     "enter"],
	 *         	  ["Curtis",   "enter"],
	 *         	  ["Paul",     "exit"],
	 *            ["Martha",   "enter"],
	 *         	  ["Martha",   "exit"],
	 *            ["Jennifer", "exit"],
	 *          ]
	 *          find_mismatched_entries(badge_records)
	 *          Expected output: ["Paul", "Curtis"], ["Martha"]
	 *          
	 * 【第二问】 We want to find employees who badged into our secured room unusually often. We have an unordered list of names and access 
	 *          times over a single day (不用考虑23:59-0:01). Access times are given as three or four-digit numbers using 24-hour time, such as "800" or "2250".
     * 			Write a function that finds anyone who badged into the room 3 or more times in a 1-hour period, and returns each time that 
     *          they badged in during that period. (If there are multiple 1-hour periods where this was true, just return the first one.)
     *
     *          badge_records = [
     *           ["Paul", 1355],
     *           ["Jennifer", 1910],
     *           ["John", 830],
     *           ["Paul", 1315],
     *           ["John", 835],
     *           ["Paul", 1405],
     *           ["Paul", 1630],
     *           ["John", 855],
     *           ["John", 915],
     *           ["John", 930],
     *           ["Jennifer", 1335],
     *           ["Jennifer", 730],
     *           ["John", 1630],
     *          ]
     *          Expected output:
     *          John: 830 835 855 915 930
     *          Paul: 1315 1355 1405
	 */
	
	// 第一问
	public static List<List<String>> misMatched(List<List<String>> record) {
		List<List<String>> result = new ArrayList<>();
		if (record == null || record.size() == 0) {
			return result;
		}
		Set<String> noExit = new HashSet<>();
		Set<String> noEnter = new HashSet<>();
		
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < record.size(); i++) {
			String name = record.get(i).get(0);
			String status = record.get(i).get(1);
			
			if (!map.containsKey(name)) {
				map.put(name, 0);
			}
			
			if (status.equals("exit")) {
				map.put(name, map.get(name) - 1);
			} else {
				map.put(name, map.get(name) + 1);
			}
			
			if (map.get(name) < 0) {
				noEnter.add(name);
			}
			
			if (map.get(name) > 1) {
				noExit.add(name);
			}
		}
		
		for (Map.Entry<String, Integer> entry: map.entrySet()) {
			if (entry.getValue() > 0) {
				noExit.add(entry.getKey());
			}
		}
		
		result.add(new ArrayList<>(noExit));
		result.add(new ArrayList<>(noEnter));
		return result;
	}
	
	// 第二问 
	public static Map<String, List<String>> unusuallyOften(List<List<String>> record) {
		Map<String, List<String>> result = new HashMap<>();
		Map<String, List<Integer>> map = new HashMap<>();
		
		for (List<String> r: record) {
			String name = r.get(0);
			if (!map.containsKey(name)) {
				map.put(name, new ArrayList<>());
			}
			map.get(name).add(Integer.parseInt(r.get(1)));
		}
		//System.out.println(map);
		for (String key: map.keySet()) {
			Collections.sort(map.get(key));
			List<String> list = helper(map.get(key));
			if (list.size() > 0) {
				result.put(key, list);
			}
		}
		//System.out.println(map);
		return result;
	}
	
	private static List<String> helper(List<Integer> list) {
		List<String> result = new ArrayList<>();
		
		int i = 0;
		int j = 1;
		
		while (j < list.size()) {
			while (j < list.size() && list.get(j) - list.get(i) <= 100) {
				j++;
			}
			
			if (j - i > 2) {
				for (int k = i; k < j; k++) {
					result.add(list.get(k) + "");
				}
				break;
			}
			i++;
			j = i + 1;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【第一问】输出两个list，第一个是离开没有用badge的员工，第二个是进入没有用badge的员工 ***********");
		List<List<String>> test1 = new ArrayList<>(Arrays.asList(Arrays.asList("Martha", "exit"), Arrays.asList("Paul", "enter"),
																 Arrays.asList("Martha", "enter"), Arrays.asList("Martha", "exit"),
																 Arrays.asList("Jennifer", "enter"), Arrays.asList("Paul", "enter"),
																 Arrays.asList("Curtis", "enter"), Arrays.asList("Paul", "exit"),
																 Arrays.asList("Martha", "enter"), Arrays.asList("Martha", "exit"),
																 Arrays.asList("Jennifer", "exit")));
		System.out.println(misMatched(test1));
		System.out.println();
		
		System.out.println("*********** 【第二问】输出一小时内进入3次及以上的员工和他们的进入时间 ***********");
		List<List<String>> test2 = new ArrayList<>(Arrays.asList(Arrays.asList("Paul", "1355"), Arrays.asList("Jennifer", "1910"),
				 Arrays.asList("John", "830"), Arrays.asList("Paul", "1315"),
				 Arrays.asList("John", "835"), Arrays.asList("Paul", "1405"),
				 Arrays.asList("Paul", "1630"), Arrays.asList("John", "855"),
				 Arrays.asList("John", "915"), Arrays.asList("John", "930"),
				 Arrays.asList("Jennifer", "1335"), Arrays.asList("Jennifer", "730"), 
				 Arrays.asList("John", "1630")));
		
		System.out.println(unusuallyOften(test2));
		
		/*
		List<Integer> test21 = new ArrayList<>(Arrays.asList(830, 835, 855, 915, 930, 1630));
		List<Integer> test22 = new ArrayList<>(Arrays.asList(1315, 1355, 1405, 1630));
		List<Integer> test23 = new ArrayList<>(Arrays.asList(730, 1335, 1910));
		List<Integer> test24 = new ArrayList<>(Arrays.asList(730, 820, 840, 850, 930, 940, 950, 960));
		List<Integer> test25 = new ArrayList<>(Arrays.asList(730, 750, 840, 900, 910));
		
		System.out.println(helper(test21));
		System.out.println(helper(test22));
		System.out.println(helper(test23));
		System.out.println(helper(test24));
		System.out.println(helper(test25));
		*/
	}
}
