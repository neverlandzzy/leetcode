import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Solution {
	/*
	 * We are given a list schedule of employees, which represents the working time for each employee.
	 * 
	 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
	 * 
	 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
	 * 
	 * Example 1:
	 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
	 * Output: [[3,4]]
	 * Explanation:
	 * There are a total of three employees, and all common
	 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
	 * We discard any intervals that contain inf as they aren't finite.
	 * 
	 * Example 2:
	 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
	 * Output: [[5,6],[7,9]]
	 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. 
	 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
	 * 
	 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
	 * 
	 * Note:
	 * 1. schedule and schedule[i] are lists with lengths in range [1, 50].
	 * 2. 0 <= schedule[i].start < schedule[i].end <= 10^8.
	 */
	
	// LC56
	private static void mergeIntervals(List<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>(){
			public int compare(Interval o1, Interval o2) {	
				return o1.start - o2.start;
			}
		});
		
		for (int i = 0; i < intervals.size() - 1; i++) {
			if (intervals.get(i + 1).start <= intervals.get(i).end) {
				intervals.get(i).end = Math.max(intervals.get(i + 1).end, intervals.get(i).end);
				intervals.remove(i + 1);
				i--;
			}
		}
	}
	
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    	List<Interval> intervals = new ArrayList<>();
    	
    	for (List<Interval> list: schedule) {
    		for (Interval interval: list) {
    			intervals.add(interval);
    		}
    	}
    	
    	mergeIntervals(intervals);
    	
    	List<Interval> result = new ArrayList<>();
    	
    	for (int i = 0; i < intervals.size() - 1; i++) {
    		Interval free = new Interval(intervals.get(i).end, intervals.get(i + 1).start);
    		result.add(free);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		List<List<Interval>> list11 = new ArrayList<>();
		Interval interval1 = new Interval(1,2);
		Interval interval2 = new Interval(5,6);
		Interval interval3 = new Interval(1,3);
		Interval interval4 = new Interval(4,10);

		
		List<Interval> list1 = new ArrayList<Interval>();
		List<Interval> list2 = new ArrayList<Interval>();
		List<Interval> list3 = new ArrayList<Interval>();
		
		list1.add(interval1);
		list1.add(interval2);
		list2.add(interval3);
		list3.add(interval4);
		list11.add(list1);
		list11.add(list2);
		list11.add(list3);
		
		List<Interval> result = employeeFreeTime(list11);

		for (Interval tmp: result) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		
		
		List<List<Interval>> list22 = new ArrayList<>();
		Interval interval5 = new Interval(1,3);
		Interval interval6 = new Interval(6,7);
		Interval interval7 = new Interval(2, 4);
		Interval interval8 = new Interval(2, 5);
		Interval interval9 = new Interval(9, 12);
		
		List<Interval> list4 = new ArrayList<Interval>();
		List<Interval> list5 = new ArrayList<Interval>();
		List<Interval> list6 = new ArrayList<Interval>();
		
		list4.add(interval5);
		list4.add(interval6);
		list5.add(interval7);
		list6.add(interval8);
		list6.add(interval9);
		list22.add(list4);
		list22.add(list5);
		list22.add(list6);
		System.out.println();
		
		List<Interval> result2 = employeeFreeTime(list22);

		for (Interval tmp: result2) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}

		System.out.println();
	}
}
