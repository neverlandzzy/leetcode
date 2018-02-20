package oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution2 {
	
    static class MachineGraph {
        public Map<String, Integer> input;
        public int output;
        
        public MachineGraph() {
        	input = new HashMap<>();
        	output = 1;
        }
    }
    
    static class Graph {
        public Map<String, Integer> sources; 
        public Map<String, MachineGraph> machines;
        
        public Graph() {
        	sources = new HashMap<>();
        	machines = new HashMap<>();
        }
    }
    
    static class Digraph {
        private Map<String, List<String>> adj;
        private Map<String, Integer> indegree;
        private Queue<String> queue;
        
        public Digraph(Map<String, MachineGraph> machines) {
            indegree = new HashMap<>();
            adj = new HashMap<>();
            
            for (String m: machines.keySet()) {
            	adj.put(m, new ArrayList<>());
            	indegree.put(m, 0);
            }
            
            for (Map.Entry<String, MachineGraph> entry: machines.entrySet()) {
            	String key = entry.getKey();
            	Map<String, Integer> input = entry.getValue().input;
            	adj.put(key, new ArrayList<>());
            	if (!indegree.containsKey(key)) {
            		indegree.put(key, 0);
            	}

            	for (Map.Entry<String, Integer> inputEntry: input.entrySet()) {
            		if (inputEntry.getKey().startsWith("m")) {
            			adj.get(inputEntry.getKey()).add(key);
            			indegree.put(key, indegree.getOrDefault(key, 0) + 1);
            		}
            	}
            }
        }
        
        public List<String> topologicalOrder() {
            List<String> topologicalOrder = new ArrayList<>();
            queue = new LinkedList<>();
            
            for (Map.Entry<String, Integer> entry: indegree.entrySet()) {
                if (entry.getValue() == 0) {
                    queue.offer(entry.getKey());
                }
            }
            
            while (!queue.isEmpty()) {
                String v = queue.poll();
                topologicalOrder.add(v);
                
                for (String neighbor: adj.get(v)) {
                    if (indegree.get(neighbor) - 1 == 0) {
                        queue.offer(neighbor);
                    }
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                }
            }
            
            return topologicalOrder;
        }
    }
    
    public static int findMaxOutput(Graph g) {
        // TODO: implement here
        Digraph digraph = new Digraph(g.machines);
        
        /*
        if (digraph.noResource) {
        	return 0;
        }
        */
        List<String> topologicalOrder = digraph.topologicalOrder();
        
        if (topologicalOrder == null || topologicalOrder.size() == 0) {
            return 0;
        }
        
        Map<String, Integer> machine = new HashMap<>();
        Map<String, Integer> cost = new HashMap<>();
        
        for (String s: g.sources.keySet()) {
            cost.put(s, 0);
        }
        
        machine.put(topologicalOrder.get(topologicalOrder.size() - 1), 1);
        
        for (int i = topologicalOrder.size() - 1; i >= 0; i--) {
            String s = topologicalOrder.get(i);
            for (Map.Entry<String, Integer> entry: g.machines.get(s).input.entrySet()) {
                String key = entry.getKey();
                int val = entry.getValue();
                
                if (key.startsWith("m")) {
                    machine.put(key, machine.getOrDefault(key, 0) + val * machine.get(s));
                } else {
                	if (!cost.containsKey(key)) {
                		return 0;
                	}
                    cost.put(key, cost.get(key) + val * machine.get(s));
                }
            }
            
        }
        
        int min = Integer.MAX_VALUE;
        
        for (Map.Entry<String, Integer> entry: cost.entrySet()) {
            min = Math.min(min, g.sources.get(entry.getKey()) / entry.getValue());
        }
        
        return min;
    }
    
    
    
	
	public static void main(String[] args) {
		
		// Test1
		
		MachineGraph m1 = new MachineGraph();
		m1.input.put("s1", 4);
		m1.input.put("s2", 2);
		
		MachineGraph m2 = new MachineGraph();
		m2.input.put("s3", 5);
		
		MachineGraph m3 = new MachineGraph();
		m3.input.put("m1", 1);
		m3.input.put("m2", 2);
		m3.input.put("s2", 5);
		
		Graph g = new Graph();
		
		g.sources.put("s1", 100);
		g.sources.put("s2", 130);
		g.sources.put("s3", 110);
		
		g.machines.put("m1", m1);
		g.machines.put("m2", m2);
		g.machines.put("m3", m3);
		
		System.out.println(findMaxOutput(g));
		
		/*
		MachineGraph m1 = new MachineGraph();
		m1.input.put("s1", 4);
		m1.input.put("s2", 2);
		
		MachineGraph m2 = new MachineGraph();
		m2.input.put("s2", 3);
		m2.input.put("s3", 5);
		
		MachineGraph m3 = new MachineGraph();
		m3.input.put("m1", 1);
		m3.input.put("m2", 3);
		
		Graph g = new Graph();
		
		g.sources.put("s1", 90);
		g.sources.put("s2", 130);
		g.sources.put("s3", 110);
		
		g.machines.put("m1", m1);
		g.machines.put("m2", m2);
		g.machines.put("m3", m3);
		
		System.out.println(findMaxOutput(g));
		*/
	}
}
