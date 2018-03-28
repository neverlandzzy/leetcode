package onsite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserQuery {
	
	/*
	 * 输入是一些列user + query word, 输出是每当进来一个query时，根据之前的query，要返回一个最相关的单词，这题能够保证同一个user，只会query某个单词一次。
	 * 具体看例子：
	 * Input:
	 * 7
	 * A python
	 * B java
	 * A java
	 * B php
	 * C python
	 * C java
	 * D java
	 * 
	 * Output:
	 * 0
	 * 0
	 * 0
	 * 0
	 * 1 java(因为目前A: pyhon java, B: java php, search过python的人中还search最多的是java，1次)
	 * 1 python php(此时 A: pyhon java, B: java php, C: python)
	 * 2 python(此时A: pyhon java, B: java php, C: python java)
	 */
	
	public static Map<String, Set<String>> userMap = new HashMap<>();  // user -> searched words
	public static Map<String, Map<String, Integer>> wordMap = new HashMap<>(); // word => related words and counts
	public static Map<String, maxCountList> maxRelatedMap = new HashMap<>();   // word => its max related words 
	
	static class maxCountList {
		public List<String> list;
		public int count;
		
		public maxCountList() {
			list = new ArrayList<>();
			count = 0;
		}
		
		/*
		@Override
		public String toString() {
			return list + " " + count;
		}
		*/
	}
	
	public static String search(String user, String word) {
		StringBuilder sb = new StringBuilder();
		
		if (!userMap.containsKey(user)) {
			userMap.put(user, new HashSet<>());
		}
		
		if (!wordMap.containsKey(word)) {
			wordMap.put(word, new HashMap<>());
		}
		
		if (!maxRelatedMap.containsKey(word)) {
			maxRelatedMap.put(word, new maxCountList());
		}
		
		// 先保存本次search需要的结果
		List<String> list = new ArrayList<>(maxRelatedMap.get(word).list);
		int counter = maxRelatedMap.get(word).count;
		
		sb.append(counter);
		for (String s: list) {
			sb.append(" ").append(s);
		}
		
		// 再更新3个map
		// [更新wordMap和maxRelatedMap] 从userMap中找到当前user的其它输入，加到wordMap中
		for (String w: userMap.get(user)) {
			// 将w加入到word的related words中
			wordMap.get(word).put(w, wordMap.get(word).getOrDefault(w, 0) + 1);
			
			if (maxRelatedMap.get(word).count == wordMap.get(word).get(w)) {
				maxRelatedMap.get(word).list.add(w);
			} else if (maxRelatedMap.get(word).count < wordMap.get(word).get(w)) {
				maxRelatedMap.get(word).list.clear();
				maxRelatedMap.get(word).list.add(w);
				maxRelatedMap.get(word).count = wordMap.get(word).get(w);
			}

			// 将word加入到w的related words中 - 此处不用检查wordMap和maxRelatedMap是否有w，w若在userMap里，则已经被处理过，所以必然在这两个map里
			wordMap.get(w).put(word, wordMap.get(w).getOrDefault(word, 0) + 1);
			
			if (maxRelatedMap.get(w).count == wordMap.get(w).get(word)) {
				maxRelatedMap.get(w).list.add(word);
			} else if (maxRelatedMap.get(w).count < wordMap.get(w).get(word)) {
				maxRelatedMap.get(w).list.clear();
				maxRelatedMap.get(w).list.add(word);
				maxRelatedMap.get(w).count = wordMap.get(w).get(word);
			}
		}
		
		// [更新userMap]
		userMap.get(user).add(word);
		
		/*
		System.out.println(userMap);
		System.out.println(wordMap);
		System.out.println(maxRelatedMap);
		*/
		return sb.toString();
	}
	
	public static void main(String[] args) {
		/*
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String[] info = scanner.nextLine().split(" ");
			String res = search(info[0], info[1]);
			System.out.println(res);
			System.out.println(people);
			System.out.println(finals);
			System.out.println(maximal);
		}
		*/
		
		/*
		System.out.println(search("A", "python"));
		System.out.println(search("B", "java"));
		System.out.println(search("A", "java"));
		System.out.println(search("B", "php"));
		System.out.println(search("C", "python"));
		System.out.println(search("C", "java"));
		System.out.println(search("D", "java"));
		*/
		
		// 测试数据2
		/*
		 * output:
		 *  0
		 *  0
		 *  0
		 *  1 restaurant sales
		 *  1 restaurant retail
		 *  0
		 *  1 retail sales
		 *  0
		 *  2 sales
		 */
		/*
		System.out.println(search("A", "retail"));
		System.out.println(search("A", "sales"));
		System.out.println(search("A", "restaurant"));
		System.out.println(search("B", "retail"));
		System.out.println(search("B", "sales"));
		System.out.println(search("B", "part_time"));
		System.out.println(search("C", "part_time"));
		System.out.println(search("C", "cashier"));
		System.out.println(search("C", "retail"));
		*/
		
		// 测试数据3
		/*
		 * output:
		 * 0
		 * 0
		 * 0
		 * 0
		 * 0
		 * 1 java
		 * 1 java software_engineer
		 */
		
		System.out.println(search("A", "java"));
		System.out.println(search("B", "python"));
		System.out.println(search("B", "java"));
		System.out.println(search("A", "software_engineer"));
		System.out.println(search("C", "truck_driver"));
		System.out.println(search("B", "software_engineer"));
		System.out.println(search("A", "python"));
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		/*
		System.out.println(search2("A", "python"));
		System.out.println(search2("B", "java"));
		System.out.println(search2("A", "java"));
		System.out.println(search2("B", "php"));
		System.out.println(search2("C", "python"));
		System.out.println(search2("C", "java"));
		System.out.println(search2("D", "java"));
		*/
	}
	
	
	
	public static Map<String, Set<String>> people = new HashMap<String, Set<String>>();
	public static Map<String, Map<String, Integer>> finals = new HashMap<String, Map<String, Integer>>();
	public static Map<String, Maximal> maximal = new HashMap<String, Maximal>();

	static class Maximal {
			public List<String> list;
			public int counter;
			public Maximal(){
				counter = 0;
				list = new ArrayList<String>();
			}
			
			@Override
			public String toString() {
				return list + " " + counter;
			}
	}
	
	public static String search2(String person, String language) {
		        
		        people.putIfAbsent(person, new HashSet<String>());		
				finals.putIfAbsent(language, new HashMap<String, Integer>());
			    maximal.putIfAbsent(language, new Maximal());
				StringBuilder sb = new StringBuilder();
							
				List<String> list = new ArrayList<String>(maximal.get(language).list);
				
				int counter = maximal.get(language).counter;
												
				if (!people.get(person).contains(language)) {
						for (String l : people.get(person)) {
							finals.get(language).put(l, finals.get(language).getOrDefault(l, 0) + 1);
							if (maximal.get(language).counter == finals.get(language).get(l)) {
								addList(maximal.get(language).list, l);
							} else if (maximal.get(language).counter < finals.get(language).get(l)) {
								maximal.get(language).list = new ArrayList<String>();
								maximal.get(language).list.add(l);
								maximal.get(language).counter = finals.get(language).get(l);
							}
							
		 					finals.get(l).put(language, finals.get(l).getOrDefault(language, 0) + 1);

							if (maximal.get(l).counter == finals.get(l).get(language)) {
								addList(maximal.get(l).list, language);
							} else if (maximal.get(l).counter < finals.get(l).get(language)) {
								maximal.get(l).list = new ArrayList<String>();
								maximal.get(l).list.add(language);
								maximal.get(l).counter = finals.get(language).get(l);
							}
						}
								
						people.get(person).add(language);	
					}
					Collections.sort(list);
					sb.append(counter);
					for (String s: list) {
						sb.append(" " + s);
					}
					return sb.toString().trim();
	}

	public static void addList(List<String> list, String target) {
		int index = Collections.binarySearch(list, target);
		if (index < 0) {
			index = -(index + 1);
		}
		if (index == list.size()) {
			list.add(target);
		} else {
			list.add(index, target);
		}
	}
	
	
}
