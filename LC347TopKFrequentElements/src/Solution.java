import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/**
	 * Given a non-empty array of integers, return the k most frequent elements.
	 *
	 * For example,
	 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
	 * 
	 * Note: 
	 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
	 */
	
	
	// Solution 1: naive solution with heap,  Time: O(nlogn), Space: O(n) 
	/*
    public static int[] topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
        	return result;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i: nums) {
        	map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
        	public int compare(Integer i1, Integer i2) {
        		return (map.get(i2) - map.get(i1));
        	}
        });
        
        for (int key: map.keySet()) {
        	heap.offer(key);
        }
        
        for (int i = 0; i < k; i++) {
        	if (!heap.isEmpty()) {
        		result.add(heap.poll());
        	}
        }
        
        return result.stream().mapToInt(x -> x).toArray();
    }
    */
	
    // Solution 2: Bucket sort, Time: O(n), Space: O(n)

	public static int[] topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
        	return new int[0];
        }
        
        Map<Integer, Integer> map = new HashMap<>();
		int maxFrequence = 0;

		for (int i: nums) {
			int frequence = 1;
			if (map.containsKey(i)) {
				frequence += map.get(i);
			}

			map.put(i, frequence);
			maxFrequence = Math.max(maxFrequence, frequence);
		}

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= maxFrequence; i++) {
        	list.add(new ArrayList<>());
        }
        
        for (int key: map.keySet()) {
        	int val = map.get(key);
        	list.get(val).add(key);
        }
        
        for (int i = list.size() - 1; i >= 0; i--) {
        	if (k > 0 && list.get(i).size() > 0) {
        		for (int n: list.get(i)) {
        			result.add(n);
        			k--;
        		}
        	}
        	
        	if (k == 0) {
        		break;
        	}
        }
        
        return result.stream().mapToInt(x -> x).toArray();
	}
    
    public static void main(String[] args) {
		int[] test1 = {1, 1, 1, 2, 2, 3};
		printArray(topKFrequent(test1, 2));
	}

	private static void printArray(int[] nums) {
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
