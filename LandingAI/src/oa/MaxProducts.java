package oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MaxProducts {
	
	/*
	 * 	http://www.1point3acres.com/bbs/thread-314603-1-1.html
     *  
	 *  大意就是有一个工厂有很多机器和原材料，机器用"mi"表示，比如m1表示１号机器，m2表示２号机器，等等。
	 *  原材料用＂Si＂表示，S1表示１号原料，S2表示２号原料，等等。每种原材料都给定了数量，原材料部分的输入用dict表示，比如{"S1":9, "S2":13, "S3":11}表示1号原料数量9， 
	 *  2号原料数量13，3号原料数量11
	 *  每台机器需要一定数量的input，然后产生output，input可以是原材料，也可以是其他机器的output，每种input都给定了数量，比如对于1号机器m1， 
	 *  input:{"S1":2, "S2":5, "m2":2}, output:1，表示1号机器需要使用2个数量的S1， 5个数量的S2，以及2个数量的2号机器的output，产生1个数量的output。
	 *  可以认为题目输入为一个Directed Acyclic Graph，而且保证只有一台机器产生最后的产品，求计算工厂可以生产的最大产品数量.
	 * 
	 * 【思路】e.g. https://ibb.co/bZpCj6 
	 *  原料：{"S1":90, "S2":130, "S3":110}, m1:{"S1":4, "S2":2}, m2:{"S2":3, "S3":5}, m3:{"m1":1, "m2":2}
	 *  先拓扑排序，然后从最后的产品开始逆序遍历，就得到每个产品需要消耗的原材料的数量。接下来你需要算原料数量。你拓扑排序的结果就是M1 M2 M3， 
	 *  得到1个M1产品，2个M2产品，然后发现需要4 S1, 8 S2，10 S3。然后除一下，取floor(min(90/4, 130/8, 110/10)).
	 *  
	 *  
	 */
	
	// Time: O(V + E)
	// Space: O(V + E) -- adj
	static class Digraph {
		private int V; 
		private Map<String, List<String>> adj;
		private Map<String, Integer> indegree;
		private Queue<String> queue;
		
		public Digraph(Map<String, HashMap<String, Integer>> map) {
			this.V = map.size();
			indegree = new HashMap<>();
			adj = new HashMap<>();
			
			for (Map.Entry<String, HashMap<String, Integer>> entry: map.entrySet()) {
				String key = entry.getKey();
				HashMap<String, Integer> req = entry.getValue();
				adj.put(key, new ArrayList<>());
				if (!indegree.containsKey(key)) {
					indegree.put(key, 0);
				}
				
				for (Map.Entry<String, Integer> reqEntry: req.entrySet()) {
					if (reqEntry.getKey().startsWith("m")) {
						adj.get(reqEntry.getKey()).add(key);
						indegree.put(key, indegree.getOrDefault(key, 0) + 1);
					}
				}
			}
		}
		
		public List<String> topologicalOrders() {
			List<String> topologicalOrder = new ArrayList<>();
			queue = new LinkedList<String>();
			int counter = 0;
			
			for (Map.Entry<String, Integer> entry: indegree.entrySet()) {
				if (entry.getValue() == 0) {
					queue.offer(entry.getKey());
				}
			}
		
			while (!queue.isEmpty()) {
				String v = queue.poll();
				topologicalOrder.add(v);
				counter++;
				
				for (String neighbor: adj.get(v)) {
					if (indegree.get(neighbor) - 1 == 0) {
						queue.offer(neighbor);
					}
					indegree.put(neighbor, indegree.get(neighbor) - 1);
				}
			}

			if (counter < V) {
				return null;
			}
			return topologicalOrder;
		}
	}
	
	public static int getMaxProducts(Map<String, HashMap<String, Integer>> map, Map<String, Integer> resource) {
		Digraph digraph = new Digraph(map);
		List<String> topologicalOrder = digraph.topologicalOrders();
		
		if (topologicalOrder == null || topologicalOrder.size() == 0) {
			return 0;
		}
		
		Map<String, Integer> machine = new HashMap<>();
		Map<String, Integer> cost = new HashMap<>();
		
		for (String r: resource.keySet()) {
			cost.put(r, 0);
		}
		
		machine.put(topologicalOrder.get(topologicalOrder.size() - 1), 1);
		
		for (int i = topologicalOrder.size() - 1; i >= 0; i--) {
			String s = topologicalOrder.get(i);
			for (Map.Entry<String, Integer> entry: map.get(s).entrySet()) {
				String key = entry.getKey();
				int val = entry.getValue();
				
				if (key.startsWith("m")) {
					machine.put(key, machine.getOrDefault(key, 0) + val * machine.get(s));
				} else {
					cost.put(key, cost.get(key) + val * machine.get(s));
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for (Map.Entry<String, Integer> entry: cost.entrySet()) {
			min = Math.min(min, resource.get(entry.getKey()) / entry.getValue());
		}
		
		return min;
	}
	
	public static void main(String[] args) {
		Map<String, HashMap<String, Integer>> map1 = new HashMap<>();
		Map<String, Integer> resource1 = new HashMap<>();
		
		resource1.put("S1", 90);
		resource1.put("S2", 130);
		resource1.put("S3", 110);
		
		HashMap<String, Integer> m11 = new HashMap<>();
		m11.put("S1", 4);
		m11.put("S2", 2);
		
		HashMap<String, Integer> m21 = new HashMap<>();
		m21.put("S2", 3);
		m21.put("S3", 5);
		
		HashMap<String, Integer> m31 = new HashMap<>();
		m31.put("m1", 1);
		m31.put("m2", 2);
		m31.put("S3", 3);
		
		map1.put("m1", m11);
		map1.put("m2", m21);
		map1.put("m3", m31);
		
		System.out.println("========= Solution =========");
		//System.out.println(map);
		//System.out.println(resource);
		System.out.println(getMaxProducts(map1, resource1));
	}
}
