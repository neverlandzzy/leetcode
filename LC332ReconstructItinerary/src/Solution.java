import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
	 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the 
	 * itinerary must begin with JFK.
	 * 
	 * Note:
	 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical 
	 * order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
	 * 
	 * All airports are represented by three capital letters (IATA code).
	 * You may assume all tickets form at least one valid itinerary.
	 * 
	 * Example 1:
	 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
	 * 
	 * Example 2:
	 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
	 * 
	 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
	 */
	
    public static List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> hashmap = new HashMap<>();
        List<String> result = new ArrayList<>();
        
        for (String[] ticket: tickets) {
        	if (!hashmap.containsKey(ticket[0])) {
        		hashmap.put(ticket[0], new PriorityQueue<String>());
        	} 
        	
        	hashmap.get(ticket[0]).add(ticket[1]);
        }
        
        helper(hashmap, result, "JFK");
        
        return result;
    }
    
    private static void helper(Map<String, PriorityQueue<String>> hashmap, List<String> result, String departure) {
    	PriorityQueue<String> queue = hashmap.get(departure);

    	while (queue != null && !queue.isEmpty()) {
    		helper(hashmap, result, queue.poll());
    	}

    	result.add(0, departure);
    }
    
    public static void main(String[] args) {
		String[][] test1 = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		String[][] test2 = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
	
		System.out.println(findItinerary(test1));
		System.out.println(findItinerary(test2));

	}
}
