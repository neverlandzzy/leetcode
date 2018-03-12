package onsite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Search {
	/*
	 * 输入如下：
	 * N
	 * ...
	 * ...  --> document，有N行string，每一个string是space delimited
	 * ...
	 * M
	 * ...
	 * A
	 * B & C  --> M行query
	 * D | E
	 * ...
	 * 要求输出如下：对每一个query，依次print document中满足query条件的行号，其中行号根据key word出现频率排序，对于频率一样的，小的行号优先。
	 * 其中出现的频率按照如下定义：如果string中单词A cnt1次，单词B cnt2次，则A&B和A | B的频率都按照cnt1 + cnt2计算。
	 * 后面还有几道文字题目：分析时间和空间复杂度；如果给你一天时间完成，你还有什么优化，如果有很多很多记录，很多很多user querys，有什么优化。
	 * 
	 * a b     //1
	 * b a a   //2
	 * a b b   //3
	 * a       //4
	 * query(a),输出就是（2，1，3,4），query(b)输出(3,1,2)
	 * query(a & b) 输出(2,3,1)
	 * query(a | b) 输出(2,3,1,4)
	 * 因为query(a&b)(a|b)的频率计算都是单独算a和b出现次数然后求和的。
	 */
	/*  这个方法 query(a)会快，但没法处理其它两种search
	static Map<String, TreeMap<Integer, List<Integer>>> map; // Map<word, TreeMap<frequency, List<lineNum>>>
	
	public Search(String[] doc) {
		map = new HashMap<>();
		
		// 从第一行开始加入，保证了对于一样的频路，小的行号优先
		for (int i = 1; i <= doc.length; i++) {
			String line = doc[i - 1];
			String[] words = line.split(" ");
			Map<String, Integer> countMap = new HashMap<>();
			
			for (String w: words) {
				if (!map.containsKey(w)) {
					map.put(w, new TreeMap<>(Collections.reverseOrder()));
				}				
				countMap.put(w, countMap.getOrDefault(w, 0) + 1);
			}
			
			for (String key: countMap.keySet()) {
				if (!map.get(key).containsKey(countMap.get(key))) {
					map.get(key).put(countMap.get(key), new ArrayList<>());
				}
				map.get(key).get(countMap.get(key)).add(i);
			}
		}
		
		System.out.println(map);
	}
	*/
	static Map<String, Map<Integer, Integer>> map; // Map<word, Map<LineNumber, count>>
	
	public Search(String[] doc) {
		map = new HashMap<>();
		for (int i = 1; i <= doc.length; i++) {
			String line = doc[i - 1];
			String[] words = line.split(" ");
			Map<String, Integer> countMap = new HashMap<>();
			
			for (String w: words) {
				if (!map.containsKey(w)) {
					map.put(w, new HashMap<>());
				}				
				countMap.put(w, countMap.getOrDefault(w, 0) + 1);
			}
			
			for (String key: countMap.keySet()) {
				map.get(key).put(i, countMap.get(key));
			}
		}
		
		//System.out.println(map);
	}
	
	public static List<Integer> query(String s) {
		List<Integer> result = new ArrayList<>();
		if (s.indexOf('|') == -1 && s.indexOf('&') == -1) {
			TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>(Collections.reverseOrder());
			for (int lineNum: map.get(s).keySet()) {
				int count = map.get(s).get(lineNum);
				if (!treeMap.containsKey(count)) {
					treeMap.put(count, new ArrayList<>());
				}
				treeMap.get(count).add(lineNum);
			}
			
			for (int key: treeMap.keySet()) {
				for (int lineNum: treeMap.get(key)) {
					result.add(lineNum);
				}
			}
		} else if (s.indexOf('|') == -1) {   // 根据题目要求的形式 "A&B" or "A & B"
			String s1 = s.split("\\&")[0];
			String s2 = s.split("\\&")[1];
			
			TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>(Collections.reverseOrder());
			
			for (int lineNum: map.get(s1).keySet()) {
				if (map.get(s2).containsKey(lineNum)) {
					int count = map.get(s1).get(lineNum) + map.get(s2).get(lineNum);
					if (!treeMap.containsKey(count)) {
						treeMap.put(count, new ArrayList<>());
					}
					treeMap.get(count).add(lineNum);
				}
			}
			for (int key: treeMap.keySet()) {
				for (int lineNum: treeMap.get(key)) {
					result.add(lineNum);
				}
			}	
		} else {   // 根据题目要求的形式 "A|B" or "A | B"
			String s1 = s.split("\\|")[0];
			String s2 = s.split("\\|")[1];
			
			Map<Integer, Integer> temp = new HashMap<>();
			for (int lineNum: map.get(s1).keySet()) {
				temp.put(lineNum, map.get(s1).get(lineNum));
			}
			for (int lineNum: map.get(s2).keySet()) {
				temp.put(lineNum, temp.getOrDefault(lineNum, 0) + map.get(s2).get(lineNum));
			}
			
			TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>(Collections.reverseOrder());
			
			for (int lineNum: temp.keySet()) {
				int count = temp.get(lineNum);
				if (!treeMap.containsKey(count)) {
					treeMap.put(count, new ArrayList<>());
				}
				treeMap.get(count).add(lineNum);
			}
			
			for (int key: treeMap.keySet()) {
				for (int lineNum: treeMap.get(key)) {
					result.add(lineNum);
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] doc = {"a b", "b a a ", "a b b", "a"};
		
		Search search = new Search(doc);
		
		System.out.println(search.query("a"));
		System.out.println(search.query("b"));
		System.out.println(search.query("a&b"));
		System.out.println(search.query("a|b"));
	}
}
