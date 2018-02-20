import java.util.Arrays;
import java.util.Comparator;


public class Solution {
	/*
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person 
	 * could attend all meetings.
	 * 
	 * For example,
	 * Given [[0, 30],[5, 10],[15, 20]],
	 * return false.
	 */
	
    public static boolean canAttendMeetings(Interval[] intervals) {
        // 用lambda比传统方法写comparator慢
    	// Arrays.sort(intervals, (a, b) -> a.start - b.start);
        
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        
        for (int i = 0; i < intervals.length - 1; i++) {
        	if (intervals[i + 1].start < intervals[i].end) {
        		return false;
        	}
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		Interval i1 = new Interval(0, 30);
		Interval i2 = new Interval(5, 10);
		Interval i3 = new Interval(15, 20);
		
		Interval[] test = {i1, i2, i3};
		
		System.out.println(canAttendMeetings(test));
	}
}
