import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Solution {
	
	/*
	 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
	 * 
	 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
	 * 
	 * Example 1:
	 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
	 * Output: [20,24]
	 * Explanation: 
	 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
	 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
	 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
	 * 
	 * Note:
	 * 1. The given list may contain duplicates, so ascending order means >= here.
	 * 2. 1 <= k <= 3500
	 * 3. -10^5 <= value of elements <= 10^5.
	 * 4. For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, 
	 *    you'll see this point.
	 */
	
	// https://discuss.leetcode.com/topic/94445/java-code-using-priorityqueue-similar-to-merge-k-array/
    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
			
				return o1[0] - o2[0];
			}
        	
        });
        
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.size(); i++) {
        	int num = nums.get(i).get(0);
        	heap.offer(new int[] {num, i, 0});
        	max = Math.max(max, num);
        }
        
        int start = 0;
        int range = Integer.MAX_VALUE;
        
        while (heap.size() == nums.size()) {
        	// n[0] = val; n[1] = row; n[2] = index
        	int[] n = heap.poll();
        	
        	if (max - n[0] < range) {
        		range = max - n[0];
        		start = n[0];
        	}
        	
        	// 如果n所在的list里还有元素，则加入pq中
        	if (n[2] + 1 < nums.get(n[1]).size()) {
        		int num = nums.get(n[1]).get(n[2] + 1);
        		heap.offer(new int[] {num, n[1], n[2] + 1});
        		max = Math.max(max, num);       		
        	}
        }
        
        return new int[] {start, start + range};
    }
    
    public static void main(String[] args) {
		List<List<Integer>> test = new ArrayList<>();
		test.add(Arrays.asList(4,10,15,24,26));
		test.add(Arrays.asList(0,9,12,20));
		test.add(Arrays.asList(5,18,22,30));
		
		int[] result = smallestRange(test);
		
		for (int i: result) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
