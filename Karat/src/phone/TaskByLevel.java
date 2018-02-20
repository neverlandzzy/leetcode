package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TaskByLevel {
	/*
	 * 算task和pretask輸出task by level的那題. 是像這樣
	 * input = {
	 * {"cook", "eat"},   // do "cook" before "eat"
	 * {"study", "eat"},
	 * {"sleep", "study"}}
	 * output (steps of a workflow):
	 * {{"sleep", "cook"},
	 * {"study"},
	 * {"eat"}}
	 */
	
	public static List<List<String>> order (List<List<String>> tasks) {
		Map<String, List<String>> adj = new HashMap<>();
		Map<String, Integer> indegree = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		List<List<String>> result = new ArrayList<>();
		
		if (tasks == null || tasks.size() == 0) {
			return result;
		}
		
		for (List<String> task: tasks) {
			String preTask = task.get(0);
			String curTask = task.get(1);
			if (!adj.containsKey(preTask)) {
				adj.put(preTask, new ArrayList<>());
			}
			adj.get(preTask).add(curTask);
			if (!indegree.containsKey(preTask)) {
				indegree.put(preTask, 0);
			}
			indegree.put(curTask, indegree.getOrDefault(curTask, 0) + 1);
		}
		
		for (Map.Entry<String, Integer> entry: indegree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<String> list = new ArrayList<>();
			
			for (int i = 0; i < size; i++) {
				String s = queue.poll();
				list.add(s);

				if (adj.containsKey(s)) {
					for (String neighbor: adj.get(s)) {
						if (indegree.get(neighbor) - 1 == 0) {
							queue.offer(neighbor);
						}
						indegree.put(neighbor, indegree.get(neighbor) - 1);
					}
				}
			}
			result.add(list);
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<List<String>> test1 = new ArrayList<>();
		List<String> list11 = new ArrayList<>(Arrays.asList("cook", "eat"));
		List<String> list12 = new ArrayList<>(Arrays.asList("study", "eat"));
		List<String> list13 = new ArrayList<>(Arrays.asList("sleep", "study"));
		test1.add(list11);
		test1.add(list12);
		test1.add(list13);
		System.out.println(order(test1));
	}
}
