import java.util.Collections;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
	 * So the median is the mean of the two middle value.
	 * 
	 * Examples: 
	 * [2,3,4] , the median is 3
	 * 
	 * [2,3], the median is (2 + 3) / 2 = 2.5
	 * 
	 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
	 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the 
	 * median array for each window in the original array.
	 * 
	 * For example,
	 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	 * 
	 * Window position                Median
	 * ---------------               -----
	 * [1  3  -1] -3  5  3  6  7       1
	 *  1 [3  -1  -3] 5  3  6  7       -1
	 *  1  3 [-1  -3  5] 3  6  7       -1
	 *  1  3  -1 [-3  5  3] 6  7       3
	 *  1  3  -1  -3 [5  3  6] 7       5
	 *  1  3  -1  -3  5 [3  6  7]      6
	 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
	 * 
	 * Note: 
	 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
	 */
	
	// 参考LC295和LC239
	// 不同之处在于：
	//   1. 每次新加元素之前，要先输出答案, 然后删除旧元素，再rebalance，最后加入新元素；
	//   2. 求平均时，要先将每个元素都转换成double再计算，不然可能会overflow：
	//      [correct] result[index++] = ((double)maxHeap.firstKey() + (double)minHeap.firstKey()) / 2;
	//      [wrong]   result[index++] = (double)(maxHeap.firstKey() + minHeap.firstKey()) / 2; 
	//   3. 循环结束后，还要再输出一次
	
    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        int n = nums.length - k + 1;
        if (n < 0) {
            return new double[0];
        }
        
        double[] result = new double[n];
        
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                result[i - k] = getMedian(maxHeap, minHeap);
                remove(nums[i - k], maxHeap, minHeap);
            } 
            
            if (i < nums.length) {
                add(nums[i], maxHeap, minHeap);
            }
        }
        
        return result;
    }
    
    private static void add(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (!minHeap.isEmpty() && num < minHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        
        if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
        
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    private static void remove(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (!minHeap.isEmpty() && num < minHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        
        if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
        
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    private static double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        
        if (maxHeap.size() == minHeap.size()) {
            return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2;
        } else {
            return (double)minHeap.peek();
        }
    }	
	/*
    public static double[] medianSlidingWindow(int[] nums, int k) {
    	TreeMap<Integer, Integer> maxHeap = new TreeMap<>(Collections.reverseOrder());
    	TreeMap<Integer, Integer> minHeap = new TreeMap<>();
    	int maxHeapSize = 0;
    	int minHeapSize = 0;
    	double[] result = new double[nums.length - k + 1];
    	int index = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		if (i >= k) {
    			int delete = nums[i - k];
    			//System.out.println("delete: " + delete);
    			output(result, index++, maxHeap, minHeap, maxHeapSize, minHeapSize);
				if (maxHeap.containsKey(delete)) {
					removeKey(maxHeap, delete);
					maxHeapSize--;
				} else {
					removeKey(minHeap, delete);
					minHeapSize--;
				}
				
	    		if (maxHeapSize - minHeapSize >= 2) {
	    			int key = maxHeap.firstKey();
	    			minHeap.put(key, minHeap.getOrDefault(key, 0) + 1);
	    			removeKey(maxHeap, key);
	    			maxHeapSize--;
	    			minHeapSize++;
	    		} else if (minHeapSize - maxHeapSize >= 2) {
	    			int key = minHeap.firstKey();
	    			maxHeap.put(key, maxHeap.getOrDefault(key, 0) + 1);
	    			removeKey(minHeap, key);
	    			minHeapSize--;
	    			maxHeapSize++;
	    		}
    		}

    		if (maxHeap.isEmpty() || nums[i] <= maxHeap.firstKey()) {
    			maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
    			maxHeapSize++;
    		} else {
    			minHeap.put(nums[i], minHeap.getOrDefault(nums[i], 0) + 1);
    			minHeapSize++;
    		}
    		
    		if (maxHeapSize - minHeapSize >= 2) {
    			int key = maxHeap.firstKey();
    			minHeap.put(key, minHeap.getOrDefault(key, 0) + 1);
    			removeKey(maxHeap, key);
    			maxHeapSize--;
    			minHeapSize++;
    		} else if (minHeapSize - maxHeapSize >= 2) {
    			int key = minHeap.firstKey();
    			maxHeap.put(key, maxHeap.getOrDefault(key, 0) + 1);
    			removeKey(minHeap, key);
    			minHeapSize--;
    			maxHeapSize++;
    		}
    	}
    	output(result, index++, maxHeap, minHeap, maxHeapSize, minHeapSize);
    	return result;
    }
    
    private static void removeKey(TreeMap<Integer, Integer> heap, int key) {
    	if (heap.get(key) == 1) {
    		heap.remove(key);
    	} else {
    		heap.put(key, heap.get(key) - 1);
    	}
    }
    
    private static void output(double[] result, int index,TreeMap<Integer, Integer> maxHeap, TreeMap<Integer, Integer> minHeap, int maxHeapSize, int minHeapSize) {
		if (maxHeapSize > minHeapSize) {
			result[index] = maxHeap.firstKey();
		} else if (maxHeapSize < minHeapSize) {
			result[index] = minHeap.firstKey();
		} else {
			result[index] = ((double)maxHeap.firstKey() + (double)minHeap.firstKey()) / 2;
		}
    }
    */
    public static void main(String[] args) {
		int[] test = {1,3,-1,-3,5,3,6,7};
		double[] result = medianSlidingWindow(test, 3);
		
		for (double r: result) {
			System.out.print(r + ", ");
		}
		
		System.out.println();
	}

}
