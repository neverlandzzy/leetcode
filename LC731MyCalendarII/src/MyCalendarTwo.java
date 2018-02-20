import java.util.TreeMap;


public class MyCalendarTwo {
	/*
	 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
	 * 
	 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), 
	 * the range of real numbers x such that start <= x < end.
	 * 
	 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
	 * 
	 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple 
	 * booking. Otherwise, return false and do not add the event to the calendar.
	 * 
	 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
	 * 
	 * Example 1:
	 * MyCalendar();
	 * MyCalendar.book(10, 20); // returns true
	 * MyCalendar.book(50, 60); // returns true
	 * MyCalendar.book(10, 40); // returns true
	 * MyCalendar.book(5, 15); // returns false
	 * MyCalendar.book(5, 10); // returns true
	 * MyCalendar.book(25, 55); // returns true
	 * 
	 * Explanation: 
	 * The first two events can be booked.  The third event can be double booked.
	 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
	 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
	 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
	 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
	 * 
	 * Note:
	 * 1. The number of calls to MyCalendar.book per test case will be at most 1000.
	 * 2. In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
	 */
	
	// Solution 1: Brute Force:O(N ^ 2)
	/*
	List<int[]> calendar;
	List<int[]> overlaps;
    
	public MyCalendarTwo() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
    	for (int[] event: overlaps) {
    		if (start < event[1] && end > event[0]) {
    			return false;
    		}
    	}
        
    	for (int[] event: calendar) {
    		if (start < event[1] && end > event[0]) {
    			overlaps.add(new int[]{Math.max(start, event[0]), Math.min(end, event[1])});
    		}
    	}
    	
        calendar.add(new int[] {start, end});
        return true;
    }
    */
    
    // Solution 2: 扫描线，与MyCalendarIII解法类似 O(N ^ 2)
	// http://www.cnblogs.com/grandyang/p/7968035.html
	// 每加入一个新event，记录event的起点和终点，起点value + 1， 终点value - 1。 加入之后扫描整个treeMap，value正数表示当前的event是active的。因此value
	// 的和若大于等于3，则说明overlap大于等于3
	TreeMap<Integer, Integer> map;
	
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        
        int overlap = 0;
        
        for (int v: map.values()) {
        	overlap += v;
        	if (overlap >= 3) {
        		map.put(start, map.get(start) - 1);
        		map.put(end, map.get(end) + 1);
        		
        		return false;
        	}
        }
        return true;  		
    }
}
