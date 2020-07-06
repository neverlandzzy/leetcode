import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	/**
	 * Given a reference of a node in a connected undirected graph.
	 *
	 * Return a deep copy (clone) of the graph.
	 *
	 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
	 *
	 * class Node {
	 *     public int val;
	 *     public List<Node> neighbors;
	 * }
	 *
	 *
	 * Test case format:
	 *
	 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
	 *
	 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
	 *
	 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
	 * Visually, the graph looks like the following:
	 * 
	 *        1
	 *       / \
	 *      /   \
     *     0 --- 2
     *          / \
     *          \_/
     *
	 */
	
	// Solution 1: BFS -- recommended
	/*
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        Node newHead = new Node(node.val);
        map.put(node, newHead);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (Node neighbor: cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    Node copy = new Node(neighbor.val);
                    map.put(neighbor, copy);
                    queue.offer(neighbor);
                }

                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }

        return newHead;
    }
    */
	
	// Solution 2: DFS 
	public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap<>();
        return helper(node, map);
	}
    
    private Node helper(Node node, HashMap<Node, Node> map) {
		if (node == null) {
			return null;
		}

		if (map.containsKey(node)) {
			return map.get(node);
		}

		Node copy = new Node(node.val);
		List<Node> neighbors = new ArrayList<>();
		map.put(node, copy);

		for (Node neighbor: node.neighbors) {
			if (neighbor == node) {
				// add self-cycle;
				neighbors.add(copy);
			} else {
				neighbors.add(helper(neighbor, map));
			}
		}

		copy.neighbors = neighbors;
		return copy;
    }
}
