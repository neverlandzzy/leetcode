import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	/*
	 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
	 * 
	 * OJ's undirected graph serialization:
	 * Nodes are labeled uniquely.
	 * 
	 * We use # as a separator for each node, and , as a separator for node label and each neighbor 
	 * of the node. As an example, consider the serialized graph {0,1,2#1,2#2,2}.
	 * 
	 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
	 * 
	 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
	 * Second node is labeled as 1. Connect node 1 to node 2.
	 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
	 * Visually, the graph looks like the following:
	 * 
	 *        1
	 *       / \
	 *      /   \
     *     0 --- 2
     *          / \
     *          \_/
     *          
     *   public class UndirectedGraphNode {
     *       int label;
     *       List<UndirectedGraphNode> neighbors;
     *       UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     *   };
	 */
	
	// Solution 1: BFS -- recommended
	/*
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            for (UndirectedGraphNode nei: cur.neighbors) {
                if (!map.containsKey(nei)) {
                    queue.offer(nei);
                    UndirectedGraphNode newNei = new UndirectedGraphNode(nei.label);
                    map.put(nei, newNei);                    
                }
                
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        
        return newNode;
    }
    */
	
	// Solution 2: DFS 
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return helper(node, map);
	}
    
    private UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null) {
			return null;
		}
		
		if (map.containsKey(node)) {
			return map.get(node);
		}
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		List<UndirectedGraphNode> neighbors = new ArrayList<>();
		for (UndirectedGraphNode neighbor: node.neighbors) {
           
            if (neighbor == node) {
            	// add self-cycle;
                neighbors.add(clone);
            } else {
                neighbors.add(helper(neighbor, map));
            }
		}
		
		clone.neighbors = neighbors;
		map.put(node, clone);
		return clone;
    }
}
