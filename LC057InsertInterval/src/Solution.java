import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a set of non-overlapping intervals, insert a new interval into the intervals 
	 * (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to their start times.
	 * 
	 * Example 1:
	 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	 * 
	 * Example 2:
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
	 * 
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 * 
	 */
	
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        for (int i = 0; i < intervals.size(); i++) {
            if (newInterval.start <= intervals.get(i).end && newInterval.end >= intervals.get(i).start) {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
                intervals.remove(i);
                i--;
            } else if (newInterval.end < intervals.get(i).start) {
                intervals.add(i, newInterval);
    			return intervals;
            }
        }
        
        intervals.add(newInterval);
    	return intervals;
    }
    
    /*
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
       
    	for (int i = 0; i < intervals.size();) {
    		if (newInterval.end < intervals.get(i).start) {
    			intervals.add(i, newInterval);
    			return intervals;
    		} else if (newInterval.start > intervals.get(i).end) {
    			i++;
    		} else {
    			newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
    			newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
    			intervals.remove(i);
    		}
        }
    	intervals.add(newInterval);
    	return intervals;
    }
    */
    public static void main(String[] args) {
    	
		Interval interval1 = new Interval(1,2);
		Interval interval2 = new Interval(3,5);
		Interval interval3 = new Interval(6,7);
		Interval interval4 = new Interval(8,10);
		Interval interval5 = new Interval(12,16);
		Interval newInterval = new Interval(16,19);

		List<Interval> list = new ArrayList<Interval>();
		
		list.add(interval1);
		list.add(interval2);
		list.add(interval3);
		list.add(interval4);
		list.add(interval5);

		
		for (Interval tmp: list) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		System.out.println();
		
		list = insert(list, newInterval);
		
		for (Interval tmp: list) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		
	}
}
