import javafx.util.Pair;

import java.util.*;

public class Solution {
	/**
	 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times
	 * as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is
	 * the time it takes for a signal to travel from source to target.
	 *
	 * We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
	 * If it is impossible for all the n nodes to receive the signal, return -1.
	 *
	 * Example 1:
	 *
	 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
	 * Output: 2
	 *
	 * Example 2:
	 *
	 * Input: times = [[1,2,1]], n = 2, k = 1
	 * Output: 1
	 *
	 * Example 3:
	 *
	 * Input: times = [[1,2,1]], n = 2, k = 2
	 * Output: -1
	 *
	 *
	 * Constraints:
	 *
	 * 1 <= k <= n <= 100
	 * 1 <= times.length <= 6000
	 * times[i].length == 3
	 * 1 <= ui, vi <= n
	 * ui != vi
	 * 0 <= wi <= 100
	 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
	 */

	// Solution 1: BFS Time: O(N * E), Space: O(N * E)
	public static int networkDelayTime(int[][] times, int n, int k) {
    	List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>(); // i: source, Pair<dest, time>,
		int[] signalReceived = new int[n + 1];
		Arrays.fill(signalReceived, Integer.MAX_VALUE);

    	for (int i = 0; i <= n; i++) {
    		adjacencyList.add(new ArrayList<>());
		}

    	for (int[] time: times) {
    		int u = time[0];
    		int v = time[1];
    		int w = time[2];
			adjacencyList.get(u).add(new Pair<>(v, w));
		}

		Queue<Integer> queue = new LinkedList<>();
    	queue.offer(k);
		signalReceived[k] = 0;

    	while (!queue.isEmpty()) {
    		int node = queue.poll();
    		if (adjacencyList.get(node).size() == 0) {
    			continue;
			}

    		for (Pair<Integer ,Integer> pair: adjacencyList.get(node)) {
    			int neighbor = pair.getKey();
    			int time = pair.getValue();

    			int signalArrivalTime = signalReceived[node] + time;
    			if (signalArrivalTime < signalReceived[neighbor]) {
    				queue.offer(neighbor);
					signalReceived[neighbor] = signalArrivalTime;
				}
			}
		}

    	int result = -1;

    	for (int i = 1; i <= n; i++) {
    		result = Math.max(signalReceived[i], result);
		}

    	return result == Integer.MAX_VALUE ? -1 : result;
    }

	public static void main(String[] args) {
		int[][] test1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
		int[][] test2 = {{1, 2, 1}};
		int[][] test3 = {{1, 2, 1}};

		System.out.println(networkDelayTime(test1, 4, 2));
		System.out.println(networkDelayTime(test2, 2, 1));
		System.out.println(networkDelayTime(test3, 2, 2));
	}
}
