import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
	
	/*
	 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
	 * 
	 * Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price 
	 * from src to dst with up to k stops. If there is no such route, output -1.
	 * 
	 * Example 1:
	 * Input: 
	 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
	 * src = 0, dst = 2, k = 1
	 * Output: 200
	 * Explanation: 
	 * The graph looks like this:
	 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
	 * 
	 * Example 2:
	 * Input: 
	 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
	 * src = 0, dst = 2, k = 0
	 * Output: 500
	 * Explanation: 
	 * The graph looks like this:
	 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
	 * 
	 * Note:
	 * 1. The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
	 * 2. The size of flights will be in range [0, n * (n - 1) / 2].
	 * 3. The format of each flight will be (src, dst, price).
	 * 4. The price of each flight will be in the range [1, 10000].
	 * 5. k is in the range of [0, n - 1].
	 * 6. There will not be any duplicated flights or self cycles.
	 */
	
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] f: flights) {
        	if (!map.containsKey(f[0])) {
        		map.put(f[0], new HashMap<>());
        	}
        	map.get(f[0]).put(f[1], f[2]);
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return o1[0] - o2[0];
        	}
        }); 
        
        heap.offer(new int[] {0, src, K + 1}); // [price, src, stops]
        
        while (!heap.isEmpty()) {
        	int[] node = heap.poll();
        	int price = node[0];
        	int city  = node[1];
        	int stop  = node[2];
        	
        	if (city == dst) {
        		return price;
        	}
        	
        	if (stop > 0 && map.containsKey(city)) {
        		for (int key: map.get(city).keySet()) {
        			heap.offer(new int[] {price + map.get(city).get(key), key, stop - 1});
        		}
        	}
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
		int[][] test = {{0,1,100},{1,2,100},{0,2,500}};
		
		System.out.println(findCheapestPrice(3, test, 0, 2, 1));
		System.out.println(findCheapestPrice(3, test, 0, 2, 0));
	}
}
