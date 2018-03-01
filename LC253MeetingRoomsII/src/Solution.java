import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Solution {
	/*
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of 
	 * conference rooms required.
	 * 
	 * For example,
	 * Given [[0, 30],[5, 10],[15, 20]],
	 * return 2.
	 */
	
	// Solution 1: MinHeap O(n * log n)
	/*
    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
    		return 0;
    	}
        
        Arrays.sort(intervals, new Comparator<Interval>() {
        	public int compare(Interval i1, Interval i2) {
        		return i1.start - i2.start;
        	}
        });
        
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
        	public int compare(Interval i1, Interval i2) {
        		return i1.end - i2.end;
        	}
        });
        
        queue.offer(intervals[0]);
        int result = 1;
        
        for (int i = 1; i < intervals.length; i++) {
        	while (!queue.isEmpty() && queue.peek().end <= intervals[i].start) {
        		queue.poll();
        	}
        	queue.offer(intervals[i]);
        	result = Math.max(result, queue.size());
        }
        	
        return result;
    }
    */
	
    // Solution 2: O(n * log n)  -- 类似LC731 732
    public static int minMeetingRooms(Interval[] intervals) {
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	
    	for (Interval interval: intervals) {
    		map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
    		map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
    	}
    	
    	int result = 0;
    	int overlap = 0;
    	
    	for (int value: map.values()) {
    		
    		overlap += value;
    		result = Math.max(result, overlap);
    	}
    	System.out.println(map);
    	return result;
    }
    
    public static void main(String[] args) {
		Interval i1 = new Interval(0, 30);
		Interval i2 = new Interval(5, 10);
		Interval i3 = new Interval(15, 20);
		
		Interval[] test = {i1, i2, i3};
		
		System.out.println(minMeetingRooms(test));
	}
}
